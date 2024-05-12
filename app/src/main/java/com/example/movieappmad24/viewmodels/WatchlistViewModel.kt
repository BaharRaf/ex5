package com.example.movieappmad24.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.interfaces.MoviesViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistViewModel(private val repository: MovieRepository) : ViewModel(), MoviesViewModel {
    private val _movies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    override val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavoriteMovies().distinctUntilChanged().collect { updatedMovies ->
                _movies.value = updatedMovies
                Log.i("WatchlistViewModel", "Favorites updated")
            }
        }
    }

    override fun updateFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
            Log.i("WatchlistViewModel", "Favorite status toggled for ${movie.title}")
        }
    }
}
