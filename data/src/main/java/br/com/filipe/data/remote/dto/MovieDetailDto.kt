package br.com.filipe.data.remote.dto

import br.com.filipe.domain.model.MovieDetail
import com.google.gson.annotations.SerializedName

class MovieDetailDto {
    data class MovieDetailResponse(
        @SerializedName("imdbID") val id: String,
        @SerializedName("Title") val title: String,
        @SerializedName("Year") val year: String,
        @SerializedName("Plot") val description: String,
        @SerializedName("Poster") val image: String

    ){
        fun toMovieDetail() = MovieDetail(
            id = id,
            title = title,
            description = description,
            year = year,
            imageUrl = image
        )
    }


}
