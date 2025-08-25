package com.u4universe.composeplayground.data

data class Movie(
    val id: Int,
    val title: String,
    val year: Int,
    val rating: Double? = null,
    var isFavorite: Boolean = false
)