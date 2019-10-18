package br.com.filipe.presentation.movie.detail

import androidx.lifecycle.MutableLiveData
import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.ui.base.BaseViewModel

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {

    val presentation = MutableLiveData<Presentation>()
    val screen = MutableLiveData<Screen>()

    fun initPresentation() {
        presentation.value = getPresentation()
    }

    fun findMovieDetail(omdbId: String) {
        setLoading(true)
        subscribeSingle(
            observable = movieUseCase.getMovieDetailr(omdbId)
                .doOnSubscribe { showLoading.set(true) }
                .doFinally { showLoading.set(false) },
            success = {
                presentation.postValue(
                    getPresentation().copy(
                        id = it.id,
                        title = it.title,
                        year = it.year,
                        imageUrl = it.imageUrl,
                        description = it.description,
                        loading = false
                    )
                )
            },
            error = {
                setLoading(false)
            }
        )
    }

    fun trySaveMovie() {
        if (getPresentation().rating.isNotEmpty()) {
            saveMovie()
        } else screen.value = Screen.ShowErrorRating
    }

    fun setRating(rating: Int) {
        presentation.value = getPresentation().copy(
            rating = rating.toString()
        )
    }

    fun saveMovie() {
        subscribeCompletable(
            observable = movieUseCase.saveFavoriteMovie(toMovie()),
            complete = { screen.value = Screen.SuccessSaveMovie },
            error = { screen.value = Screen.ErrorSaveMovie }
        )
    }

    private fun setLoading(loading: Boolean) {
        presentation.postValue(
            getPresentation().copy(
                loading = loading
            )
        )
    }

    private fun toMovie() = Movie(
        id = getPresentation().id,
        year = getPresentation().year,
        imageUrl = getPresentation().imageUrl,
        description = getPresentation().description,
        title = getPresentation().title
    )

    private fun getPresentation() = presentation.value
        ?: Presentation(
            id = "",
            title = "",
            year = "",
            imageUrl = "",
            description = "",
            rating = "",
            loading = false
        )

    data class Presentation(
        val id: String,
        val title: String,
        val year: String,
        val imageUrl: String,
        val description: String,
        val loading: Boolean,
        val rating: String
    )

    sealed class Screen {
        object SuccessSaveMovie : Screen()
        object ErrorSaveMovie : Screen()
        object ShowErrorRating : Screen()
    }


}