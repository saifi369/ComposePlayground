package com.u4universe.composeplayground.movielist.xml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.u4universe.composeplayground.databinding.ActivityMovieListBinding
import com.u4universe.composeplayground.movielist.MovieListVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {

    private val viewModel: MovieListVM by viewModels()
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

        moviesAdapter = MovieAdapter(emptyList()) { movieId ->
            viewModel.updateFavorite(movieId)
        }
        binding.rvMovies.adapter = moviesAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesLIst.collectLatest {
                    moviesAdapter.updateMoviesList(it)
                }
            }
        }
    }
}