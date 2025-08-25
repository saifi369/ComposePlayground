package com.u4universe.composeplayground.movielist.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.u4universe.composeplayground.R
import com.u4universe.composeplayground.data.Movie
import com.u4universe.composeplayground.movielist.MovieListVM
import com.u4universe.composeplayground.ui.theme.ComposePlaygroundTheme

class MainComposeActivity : ComponentActivity() {
    private val moviesListViewModel: MovieListVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val moviesList by moviesListViewModel.moviesLIst.collectAsStateWithLifecycle()
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        MoviesListUi(moviesList)
                    }
                }
            }
        }
    }
}

@Composable
fun MoviesListUi(moviesList: List<Movie>) {
    LazyColumn {
        items(moviesList) { movie ->
            MovieItemUi(movie)
        }
    }

}

@Composable
fun MovieItemUi(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                Modifier
                    .weight(1f)
            ) {
                Text(movie.title, style = MaterialTheme.typography.titleLarge)
                Row(
                    Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(movie.year.toString(), style = MaterialTheme.typography.bodyMedium)
                    movie.rating?.let {
                        MovieRatingBar(movie.rating)
                    }
                }
            }

            val favIcon = if (movie.isFavorite)
                R.drawable.ic_favorite_filled
            else
                R.drawable.ic_favorite_outlined

            Image(
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp),
                imageVector = ImageVector.vectorResource(favIcon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Red)
            )
        }
    }
}

@Composable
private fun MovieRatingBar(rating: Double) {
    Image(ImageVector.vectorResource(R.drawable.ic_star_rating), null)
    Text(
        rating.toString(),
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
    )
}

@Preview
@Composable
private fun MovieItemPreview() {
    ComposePlaygroundTheme {
        MovieItemUi(Movie(1, "The Shawshank Redemption", 1994, 9.3, false))
    }
}
