package br.com.filipe.domain.model

data class Paging<T>(
    val results: List<T>,
    val pageCount: Int
)