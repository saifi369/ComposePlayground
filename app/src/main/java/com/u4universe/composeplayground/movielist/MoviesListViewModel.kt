package com.u4universe.composeplayground.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u4universe.composeplayground.data.Movie
import com.u4universe.composeplayground.data.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoviesListViewModel : ViewModel() {

    private val _moviesListState: MutableStateFlow<MovieListUiState> =
        MutableStateFlow(MovieListUiState.Loading)
    val moviesListState = _moviesListState.asStateFlow()

    init {
        fetchMovies()
    }

    fun fetchMovies(simulateError: Boolean = false) {
        viewModelScope.launch {
            _moviesListState.value = MovieListUiState.Loading
            delay(1000)

            if (simulateError) {
                _moviesListState.value =
                    MovieListUiState.Error("Unable to load movies.")
            } else {
                val movies = MovieRepository.getSampleMovies()
                _moviesListState.value = MovieListUiState.Success(movies)
            }
        }
    }

    fun updateFavorite(movieId: Int) {
        val currentState = _moviesListState.value

        if (currentState is MovieListUiState.Success) {
            val updatedMovies = currentState.movies.map { movie ->
                if (movie.id == movieId) {
                    movie.copy(isFavorite = !movie.isFavorite)
                } else {
                    movie
                }
            }

            _moviesListState.value = MovieListUiState.Success(updatedMovies)
        }
    }

    fun handleMovieClick(movie: Movie) {
        if (Random.nextBoolean())
            fetchMovies(true)
    }
}

sealed interface MovieListUiState {
    object Loading : MovieListUiState
    data class Error(val message: String) : MovieListUiState
    data class Success(val movies: List<Movie>) : MovieListUiState
}