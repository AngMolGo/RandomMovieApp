package com.example.randommoviessuggestion.network

import com.example.randommoviessuggestion.model.MovieImagesResponse
import com.example.randommoviessuggestion.model.MoviesDetailsResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/" // API DE PELICULAS

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiServiceId{
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTcyYTdmNTA2YzE1YWQ5NmNlYjhmODA3MmM2OThlYyIsIm5iZiI6MTc0MzcxMzgwNC42MjYsInN1YiI6IjY3ZWVmNjBjYjNlMDM1Mjg2Y2Q5MWE5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.M280u5vuMXZ0i7Oi_4nAqx3Hpv2aqUjqpo1B6-SM99E", "Accept: application/json")
    @GET("3/movie/top_rated?language=en-US")
    suspend fun getMovieDetails(
        @Query("page") page: Int
    ): MoviesDetailsResponse
}

interface MovieApiServicePoster{
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwNTcyYTdmNTA2YzE1YWQ5NmNlYjhmODA3MmM2OThlYyIsIm5iZiI6MTc0MzcxMzgwNC42MjYsInN1YiI6IjY3ZWVmNjBjYjNlMDM1Mjg2Y2Q5MWE5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.M280u5vuMXZ0i7Oi_4nAqx3Hpv2aqUjqpo1B6-SM99E", "Accept: application/json")
    @GET("3/movie/{movieId}/images?include_image_language=en")
    suspend fun getMoviePoster(
        @Path("movieId") movieId: Int
    ): MovieImagesResponse
}

object MovieApi {
    val retrofitService: MovieApiServiceId by lazy{
        retrofit.create(MovieApiServiceId::class.java)
    }
}
