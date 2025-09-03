package com.u4universe.composeplayground.movielist.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.u4universe.composeplayground.movielist.MoviesListViewModel
import com.u4universe.composeplayground.ui.theme.ComposePlaygroundTheme

class MainComposeActivity : ComponentActivity() {
    private val moviesListViewModel: MoviesListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        val moviesListUiState by moviesListViewModel.moviesListState.collectAsStateWithLifecycle()
                        MoviesListScreen(
                            uiState = moviesListUiState,
                            onFavoriteClick = { movie -> moviesListViewModel.updateFavorite(movie.id) },
                            onErrorClick = moviesListViewModel::fetchMovies,
                            onMovieItemClick = moviesListViewModel::handleMovieClick
                        )
                    }
                }
            }
        }
    }
}
