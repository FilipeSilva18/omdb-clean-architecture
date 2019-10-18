package br.com.filipe.presentation.ui.binding

import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.AppCompatImageView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.filipe.presentation.ui.extensions.loadImageUrl

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("show")
    fun View.show(show: Boolean) {
        visibility = if (show) VISIBLE else GONE
    }

    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun AppCompatImageView.loadUrl(imageUrl: String) {
        this.loadImageUrl(imageUrl)
    }


}