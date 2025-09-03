package com.u4universe.composeplayground.movielist.xml

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.u4universe.composeplayground.databinding.ActivityMovieListBinding
import com.u4universe.composeplayground.movielist.MovieListUiState
import com.u4universe.composeplayground.movielist.MoviesListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MoviesListViewModel by viewModels()
    private lateinit var moviesAdapter: MovieAdapter

    private val binding: ActivityMovieListBinding by lazy {
        ActivityMovieListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        moviesAdapter = MovieAdapter(
            emptyList(),
            onFavoriteClick = { movieId ->
                viewModel.updateFavorite(movieId)
            },
            onMovieItemClick = { movie ->
                viewModel.handleMovieClick(movie)
            })
        binding.rvMovies.adapter = moviesAdapter
        binding.btnRetry.setOnClickListener {
            viewModel.fetchMovies()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesListState.collectLatest {
                    updateMovies(it)
                }
            }
        }
    }

    private fun updateMovies(uiState: MovieListUiState) = when (uiState) {
        MovieListUiState.Loading -> {
            binding.loadingIndicator.show()
            binding.rvMovies.hide()
            binding.errorLayout.hide()
        }

        is MovieListUiState.Error -> {
            binding.loadingIndicator.hide()
            binding.rvMovies.hide()
            binding.tvError.text = uiState.message
            binding.errorLayout.show()
        }

        is MovieListUiState.Success -> {
            binding.loadingIndicator.hide()
            binding.errorLayout.hide()
            binding.rvMovies.show()
            moviesAdapter.updateMoviesList(uiState.movies)
        }
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
