package br.com.filipe.presentation.ui.extensions

fun Int.percentage(percent: Int) = times((percent / 100.0)).toInt()
