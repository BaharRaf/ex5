package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.worker.DatabaseManager

@Database(entities = [Movie::class, MovieImage::class], version = 4, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(databaseCallback(context))
                    .build().also { instance = it }
            }

        private fun databaseCallback(context: Context): Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                DatabaseManager(context).seedDatabase()
            }
        }
    }
}
