package br.com.filipe.domain.exception

class DefaultException(
    val code: String = "",
    override val message: String = "Erro Inesperado"
) : Exception()