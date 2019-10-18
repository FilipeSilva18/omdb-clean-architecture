package br.com.filipe.data.local.db.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("DELETE  FROM favorite_movie WHERE id = :id")
    fun deleteFavorite(id: Long)

    @Query("SELECT * FROM favorite_movie WHERE id = :id LIMIT 1")
    fun findFavorite(id: Long): FavoriteMovieEntity?

    @Query("SELECT * FROM favorite_movie")
    fun findFavorites(): Single<List<FavoriteMovieEntity>>

}