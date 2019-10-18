package br.com.filipe.presentation.ui.extensions

import androidx.appcompat.widget.AppCompatImageView
import br.com.filipe.presentation.R
import com.squareup.picasso.Picasso

fun AppCompatImageView.loadImageUrl(imageUrl: String) {
    try {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_close)
            .into(this)
    } catch (ex: Exception) {
    }
}