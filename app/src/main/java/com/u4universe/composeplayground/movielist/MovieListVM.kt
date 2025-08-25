package com.u4universe.composeplayground.movielist

import androidx.lifecycle.ViewModel
import com.u4universe.composeplayground.data.Movie
import com.u4universe.composeplayground.data.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieListVM : ViewModel() {

    private val _moviesList: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val moviesLIst = _moviesList.asStateFlow()

    init {
        _moviesList.value = MovieRepository.getSampleMovies()
    }

    fun updateFavorite(movieId: Int) {
        val currentMovies = _moviesList.value.toMutableList()

        val updatesMovies = currentMovies.map { movie ->
            if (movie.id == movieId)
                movie.copy(isFavorite = !movie.isFavorite)
            else
                movie
        }

        _moviesList.value = updatesMovies
    }
}