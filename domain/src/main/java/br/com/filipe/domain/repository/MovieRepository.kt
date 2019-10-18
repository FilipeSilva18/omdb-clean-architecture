package br.com.filipe.domain.repository

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.MovieDetail
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Single<List<Movie>>

    fun searchMovie(search:String): Single<List<Movie>>

    fun getMovieDetail(omdbId:String): Single<MovieDetail>

    fun saveFavoriteMovie(movie: Movie): Completable

    fun deleteFavoriteMovie(id: Long): Completable

    fun getFavoritesMovie(): Single<List<Movie>>
}