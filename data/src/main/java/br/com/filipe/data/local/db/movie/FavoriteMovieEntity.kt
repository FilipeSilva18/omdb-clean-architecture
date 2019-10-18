package br.com.filipe.data.local.db.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.filipe.domain.model.Movie

@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val image: String,
    val year: String
) {
    fun toMovie() = Movie(
        id = id.toString(),
        title = title,
        description = description,
        imageUrl = image,
        year = year
    )
}