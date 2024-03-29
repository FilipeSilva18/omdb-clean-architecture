package br.com.filipe.presentation.movie

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ItemMovieBinding

class MovieRecyclerAdapter : RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>() {

    private val movies = mutableListOf<Movie>()

    var listener: OnMovieClickListener? = null

    interface OnMovieClickListener {
        fun onClickFavoriteMovie(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], listener)
    }

    fun getMovieByPosition(position: Int) = movies[position]

    fun notifyChanged(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, listener: OnMovieClickListener?) {
            binding.movie = movie

            binding.clRoot.setOnClickListener { listener?.onClickFavoriteMovie(movie) }

            binding.executePendingBindings()
        }
    }
}