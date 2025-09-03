package com.u4universe.composeplayground.movielist.xml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.u4universe.composeplayground.R
import com.u4universe.composeplayground.data.Movie
import com.u4universe.composeplayground.databinding.MovieItemBinding

class MovieAdapter(
    private var moviesList: List<Movie>,
    private val onFavoriteClick: (Int) -> Unit,
    private val onMovieItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movie = moviesList[position]
        holder.bind(
            movie,
            onFavoriteClick,
            onMovieItemClick
        )
    }

    override fun getItemCount() = moviesList.size

    fun updateMoviesList(newMoviesList: List<Movie>) {
        moviesList = newMoviesList
        notifyDataSetChanged()
    }
}

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, onFavoriteToggle: (Int) -> Unit, onMovieItemClick: (Movie) -> Unit) {
        with(binding) {
            movieTitle.text = movie.title
            movieYear.text = movie.year.toString()

            if (movie.rating != null) {
                movieRating.text = movie.rating.toString()
                ivRating.visibility = View.VISIBLE
                movieRating.visibility = View.VISIBLE
            } else {
                movieRating.visibility = View.GONE
                ivRating.visibility = View.GONE
            }
            updateFavoriteIcon(isFavorite = movie.isFavorite)
            favoriteButton.setOnClickListener {
                onFavoriteToggle(movie.id)
            }
            root.setOnClickListener {
                onMovieItemClick(movie)
            }
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        val iconRes =
            if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outlined
        val colorRes = if (isFavorite) R.color.red else R.color.gray
        with(binding.favoriteButton) {
            setImageResource(iconRes)
            setColorFilter(ContextCompat.getColor(context, colorRes))
        }
    }
}
