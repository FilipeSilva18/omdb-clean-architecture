package br.com.filipe.omdb.di

import br.com.filipe.presentation.movie.MovieRecyclerAdapter
import br.com.filipe.presentation.movie.MovieListViewModel
import br.com.filipe.presentation.movie.detail.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { MovieListViewModel(get()) }

    viewModel { MovieDetailViewModel(get()) }

    factory { MovieRecyclerAdapter() }

}