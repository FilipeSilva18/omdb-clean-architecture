package br.com.filipe.presentation.ui.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.databinding.ObservableField

fun <T> LiveData<T>.observeNotNull(
    owner: LifecycleOwner,
    result: (T) -> Unit
) {
    this.observe(owner, Observer {
        it?.let(result)
    })
}

fun ObservableField<String>.getNotNull() = this.get() ?: ""