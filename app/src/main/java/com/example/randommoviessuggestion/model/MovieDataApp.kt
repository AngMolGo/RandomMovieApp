package com.example.randommoviessuggestion.model

data class MovieSimple(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val base_url: String = "https://image.tmdb.org/t/p/w500/"
)
