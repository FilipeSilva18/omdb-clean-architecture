package br.com.filipe.data.remote.dto

import br.com.filipe.domain.model.Movie
import com.google.gson.annotations.SerializedName

sealed class MovieDto {

    data class Response(
        @SerializedName("Search") val movies: List<MovieResponse>
    ) {
        fun toListMovie(): List<Movie> {
            val list = arrayListOf<Movie>()
            movies.forEach {
                list.add(Movie(it.imdbId, it.title, it.year, it.image, ""))
            }
            return list
        }
    }

    data class MovieResponse(
        @SerializedName("Title") val title: String,
        @SerializedName("Year") val year: String,
        @SerializedName("imdbID") val imdbId: String,
        @SerializedName("Type") val type: String,
        @SerializedName("Poster") val image: String

    )
}