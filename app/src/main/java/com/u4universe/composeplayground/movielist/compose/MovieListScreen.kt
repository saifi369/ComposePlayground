package com.u4universe.composeplayground.movielist.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.u4universe.composeplayground.R
import com.u4universe.composeplayground.data.Movie
import com.u4universe.composeplayground.movielist.MovieListUiState
import com.u4universe.composeplayground.ui.theme.ComposePlaygroundTheme

@Composable
fun MoviesListScreen(
    uiState: MovieListUiState,
    onFavoriteClick: (Movie) -> Unit,
    onErrorClick: () -> Unit,
    onMovieItemClick: (Movie) -> Unit
) {
    when (uiState) {
        MovieListUiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.size(48.dp))
        }

        is MovieListUiState.Error -> {
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(uiState.message, style = MaterialTheme.typography.titleMedium)

                TextButton(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    onClick = onErrorClick
                ) {
                    Text("Retry")
                }
            }
        }

        is MovieListUiState.Success -> {
            MoviesList(uiState.movies, onFavoriteClick, onMovieItemClick)
        }
    }
}

@Composable
fun MoviesList(
    moviesList: List<Movie>,
    onFavoriteClick: (Movie) -> Unit,
    onMovieClick: (Movie) -> Unit
) {
    LazyColumn {
        items(moviesList) { movie ->
            MovieItem(movie, onFavoriteClick, onMovieClick)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onFavoriteClick: (Movie) -> Unit, onMovieClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        onClick = { onMovieClick(movie) },
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

            IconButton(
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp),
                onClick = { onFavoriteClick(movie) },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(favIcon),
                    contentDescription = null,
                    tint = Color.Red
                )

            }
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
        MovieItem(Movie(1, "The Shawshank Redemption", 1994, 9.3, false), {}, {})
    }
}
