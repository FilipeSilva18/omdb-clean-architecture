package br.com.filipe.presentation.movie.detail

import android.os.Bundle
import br.com.filipe.presentation.databinding.ActivityMovieDetailBinding
import br.com.filipe.presentation.ui.base.BaseActivity
import br.com.filipe.presentation.ui.extensions.observeNotNull
import com.francescocervone.openratingview.RatingView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun getLayoutRes() = br.com.filipe.presentation.R.layout.activity_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = intent?.extras?.getString("omdbId") as String
        binding.vm = viewModel
        binding.toolbar.setNavigationOnClickListener { finish() }
        observeData()
        viewModel.initPresentation()
        viewModel.findMovieDetail(movie)

    }

    private fun observeData() {
        viewModel.presentation.observeNotNull(this) {
            binding.presentation = it
        }

        viewModel.screen.observeNotNull(this) {
            when (it) {
                is MovieDetailViewModel.Screen.ShowErrorRating -> showErrorRating()
                is MovieDetailViewModel.Screen.SuccessSaveMovie -> finish()
            }
        }

        binding.ratingView.onStarClickListener = RatingView.OnStarClickListener { position ->
            viewModel.setRating(position)
        }

    }

    fun showErrorRating() {
        Snackbar.make(
            binding.clContainer, "Selecione a nota do filme para salva-lo",
            Snackbar.LENGTH_LONG
        ).show()
    }

}