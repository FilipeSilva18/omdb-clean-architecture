package br.com.filipe.data.repository

import br.com.filipe.data.local.db.movie.FavoriteMovieEntity
import br.com.filipe.data.local.db.movie.MovieDao
import br.com.filipe.data.remote.MovieService
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.MovieDetail
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Single<List<Movie>> {
        return service.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it.toListMovie() }
            .map {
                it
            }
            .toList()
            .doOnError {
                it.message
            }
    }

    override fun searchMovie(search: String): Single<List<Movie>> {
        return service.searchMovie(search)
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it.toListMovie() }
            .map {
                it
            }
            .toList()
            .doOnError {
                it.message
            }
    }

    override fun getMovieDetail(omdbId: String): Single<MovieDetail> {
        return service.getMovieDetail(omdbId)
            .subscribeOn(Schedulers.io())
            .map { it.toMovieDetail() }
            .doOnError {
                it.message
            }

    }

    override fun saveFavoriteMovie(movie: Movie): Completable {
        return Completable.create {
            dao.insertFavorite(
                FavoriteMovieEntity(
                    id = movie.id.toLong(),
                    description = movie.description,
                    title = movie.title,
                    image = movie.imageUrl,
                    year = movie.year
                )
            )
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    override fun deleteFavoriteMovie(id: Long): Completable {
        return Completable.create {
            dao.deleteFavorite(id)
            it.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    override fun getFavoritesMovie(): Single<List<Movie>> {
        return dao.findFavorites()
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it }
            .map {
                it.toMovie()
            }
            .toList()
            .map { it.filterNotNull() }
    }
}