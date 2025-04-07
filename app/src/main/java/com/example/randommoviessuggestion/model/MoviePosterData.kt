package com.example.randommoviessuggestion.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieImagesResponse(
    @SerialName("id") val id: Int,
    val backdrops: List<Image>,
    val logos: List<Image>,
    val posters: List<Image>
)

@Serializable
data class Image(
    @SerialName("aspect_ratio") val aspectRatio: Double,
    @SerialName("file_path") val filePath: String,
    val height: Int,
    @SerialName("iso_639_1") val iso6391: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    val width: Int
)
