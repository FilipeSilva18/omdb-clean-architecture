package br.com.filipe.omdb.di

import br.com.filipe.data.repository.MovieRepositoryImpl
import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.repository.MovieRepository
import org.koin.dsl.module

val domainModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    factory { MovieUseCase(get()) }
}