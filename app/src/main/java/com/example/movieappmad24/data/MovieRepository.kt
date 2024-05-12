package com.example.movieappmad24.data

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDAO) {
    suspend fun addMovie(movie: Movie) = movieDao.addMovie(movie)
    suspend fun addMovieImages(movieImage: MovieImage) = movieDao.addImages(movieImage)
    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)
    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)
    fun getAllMovies(): Flow<List<MovieWithImages>> = movieDao.getAll()
    fun getFavoriteMovies(): Flow<List<MovieWithImages>> = movieDao.getFavorites()
    fun getById(id: Long): Flow<MovieWithImages> = movieDao.get(id)

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(dao: MovieDAO): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(dao).also { instance = it }
            }
    }
}
