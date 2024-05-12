package com.example.movieappmad24.interfaces

import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.StateFlow

interface MoviesViewModel : ViewModelFunctions {
    val movies: StateFlow<List<MovieWithImages>>
}
