package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieappmad24.dependencyinjection.Injector
import com.example.movieappmad24.viewmodels.HomeViewModel
import com.example.movieappmad24.widgets.MovieList
import com.example.movieappmad24.widgets.SimpleBottomAppBar
import com.example.movieappmad24.widgets.SimpleTopAppBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    val homeViewModel: HomeViewModel = viewModel(factory = Injector.provideMoviesViewModelFactory(context = LocalContext.current))
    Scaffold (
        topBar = {
            SimpleTopAppBar(title = "Movie App")
        },
        bottomBar = {
            SimpleBottomAppBar(
                navController = navController
            )
        }
    ){ innerPadding ->
        MovieList(
            movies = homeViewModel.movies.collectAsState().value,
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            viewModel = homeViewModel
        )
    }
}
