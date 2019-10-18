package br.com.filipe.data.remote

data class ResponseWrap<T>(val results: List<T> = arrayListOf())