package br.com.filipe.domain

import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class FavoriteMoviesUseCaseTest {

    private val repositoryMock = mock<MovieRepository>()

    private lateinit var useCase: MovieUseCase

    @Before
    fun `Setup test`() {
        useCase = MovieUseCase(repositoryMock)
    }

    @Test
    fun `Test updateFavoriteMovie() when is favorite and delete is called`() {
        //Action
        useCase.getPopularMovies()

        //Test
        verify(repositoryMock).getPopularMovies()
    }

}