package com.example.randommoviessuggestion.model

import kotlinx.serialization.Serializable

/**
 * Clase que representa la respuesta completa de la API.
 */
@Serializable
data class MoviesDetailsResponse(
    val page: Int = 0,
    val results: List<Movie> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

/**
 * Clase que representa cada película dentro del array de resultados.
 */
@Serializable
data class Movie(
    val adult: Boolean = true,
    val backdrop_path: String? = null,
    val genre_ids: List<Int> = emptyList(),
    val id: Int = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String? = null,
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = true,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)
