package com.example.movieappmad24.dependencyinjection

import android.content.Context
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.viewmodels.MoviesViewModelFactory

object Injector{
    private fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository.getInstance(MovieDatabase.getDatabase(context.applicationContext).movieDao())
    }

    fun provideMoviesViewModelFactory(context: Context): MoviesViewModelFactory {
        val movieRepository = getMovieRepository(context)
        return MoviesViewModelFactory(repository = movieRepository)
    }

}