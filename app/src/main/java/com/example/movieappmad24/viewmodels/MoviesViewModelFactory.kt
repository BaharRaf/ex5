package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.data.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository)
        modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository)
        modelClass.isAssignableFrom(WatchlistViewModel::class.java) -> WatchlistViewModel(repository)
        else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    } as T
}
