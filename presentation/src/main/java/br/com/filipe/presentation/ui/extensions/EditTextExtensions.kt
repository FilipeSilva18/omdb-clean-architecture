package br.com.filipe.presentation.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

fun EditText.rxRealtime(
    ignoreEmpty: Boolean = true,
    filter: (String) -> Unit
): Disposable {
    return this.observe()
        .subscribeOn(Schedulers.io())
        .filter {
            if (ignoreEmpty) it.isNotEmpty() else true
        }
        .distinctUntilChanged()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { filter.invoke(it) }
}

fun EditText.observe(): Observable<String> {
    val subject = PublishSubject.create<String>()

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {
            subject.onNext(text?.toString() ?: "")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })

    setOnEditorActionListener { _, actionId, _ ->
        if ((actionId == EditorInfo.IME_ACTION_DONE) or (actionId == EditorInfo.IME_ACTION_NEXT)) {
            subject.onNext(this.text.toString())
        }
        return@setOnEditorActionListener false
    }

    return subject
}