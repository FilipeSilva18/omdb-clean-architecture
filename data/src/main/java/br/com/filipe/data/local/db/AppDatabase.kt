package br.com.filipe.data.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.filipe.data.local.db.movie.FavoriteMovieEntity
import br.com.filipe.data.local.db.movie.MovieDao

@Database(entities = [FavoriteMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun createDatabase(application: Application, databaseName: String): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                databaseName
            ).allowMainThreadQueries().build()
        }
    }

    abstract fun movieDao(): MovieDao



}