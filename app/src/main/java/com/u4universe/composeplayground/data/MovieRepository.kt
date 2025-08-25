package com.u4universe.composeplayground.data

object MovieRepository {

    private val moviesList = listOf(
        Movie(1, "The Shawshank Redemption", 1994, 9.3, true),
        Movie(2, "The Godfather", 1972, 9.2, isFavorite = true),
        Movie(3, "The Dark Knight", 2008, null),
        Movie(4, "Pulp Fiction", 1994, 8.9),
        Movie(5, "Fight Club", 1999, 8.8, isFavorite = true),
        Movie(6, "Inception", 2010, null),
        Movie(7, "The Matrix", 1999, 8.7, isFavorite = true),
        Movie(8, "Goodfellas", 1990, 8.7),
        Movie(9, "Interstellar", 2014, 8.6, isFavorite = true),
        Movie(10, "Parasite", 2019, null),
        Movie(11, "The Shawshank Redemption", 1994, 9.3, true),
        Movie(12, "The Godfather", 1972, 9.2, isFavorite = true),
        Movie(13, "The Dark Knight", 2008, 9.0),
        Movie(14, "Pulp Fiction", 1994, null),
        Movie(15, "Fight Club", 1999, 8.8, isFavorite = true),
        Movie(16, "Inception", 2010, 8.8),
        Movie(17, "The Matrix", 1999, 8.7, isFavorite = true),
        Movie(18, "Goodfellas", 1990, null),
        Movie(19, "Interstellar", 2014, 8.6, isFavorite = true),
        Movie(20, "Parasite", 2019, 8.6),
        Movie(21, "The Shawshank Redemption", 1994, 9.3, true),
        Movie(22, "The Godfather", 1972, 9.2, isFavorite = true),
        Movie(23, "The Dark Knight", 2008, null),
        Movie(24, "Pulp Fiction", 1994, 8.9),
        Movie(25, "Fight Club", 1999, 8.8, isFavorite = true),
        Movie(26, "Inception", 2010, null),
        Movie(27, "The Matrix", 1999, 8.7, isFavorite = true),
        Movie(28, "Goodfellas", 1990, 8.7),
        Movie(29, "Interstellar", 2014, 8.6, isFavorite = true),
        Movie(30, "Parasite", 2019, null),
        Movie(31, "The Shawshank Redemption", 1994, 9.3, true),
        Movie(32, "The Godfather", 1972, 9.2, isFavorite = true),
        Movie(33, "The Dark Knight", 2008, 9.0),
        Movie(34, "Pulp Fiction", 1994, null),
        Movie(35, "Fight Club", 1999, 8.8, isFavorite = true),
        Movie(36, "Inception", 2010, 8.8),
        Movie(37, "The Matrix", 1999, 8.7, isFavorite = true),
        Movie(38, "Goodfellas", 1990, null),
        Movie(39, "Interstellar", 2014, 8.6, isFavorite = true),
        Movie(40, "Parasite", 2019, 8.6),
    )

    fun getSampleMovies(): List<Movie> = moviesList
}
