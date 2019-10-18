package br.com.filipe.domain.interactor

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.MovieDetail
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieUseCase(
    private val movieRepository: MovieRepository
) {

    fun getPopularMovies(): Single<List<Movie>> {
        return (movieRepository.getPopularMovies())
    }

    fun searchMovie(search:String): Single<List<Movie>> {
        return movieRepository.searchMovie(search)
            .subscribeOn(Schedulers.io())
    }

    fun getMovieDetailr(omdbId:String): Single<MovieDetail> {
        return movieRepository.getMovieDetail(omdbId)
            .subscribeOn(Schedulers.io())
    }

    fun saveFavoriteMovie(movie: Movie): Completable {
        return movieRepository.saveFavoriteMovie(movie)
    }

    fun deleteFavoriteMovie(movie: Movie): Completable {
        return movieRepository.deleteFavoriteMovie(movie.id.toLong())
    }

    fun getFavoriteMovies(): Single<List<Movie>> {
        return movieRepository.getFavoritesMovie()
    }

}