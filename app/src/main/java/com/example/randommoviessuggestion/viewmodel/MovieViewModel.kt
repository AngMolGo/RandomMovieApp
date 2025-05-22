package com.example.randommoviessuggestion.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randommoviessuggestion.model.Movie
import com.example.randommoviessuggestion.model.MovieImagesResponse
import com.example.randommoviessuggestion.model.MovieSimple
import com.example.randommoviessuggestion.model.MoviesDetailsResponse
import com.example.randommoviessuggestion.network.MovieApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.random.Random

sealed interface  MovieUiState{
    data class Success(val movie: MovieSimple, val apiResponse: List<Movie>) : MovieUiState
    object Error: MovieUiState
    object Loading: MovieUiState
}

class MovieViewModel: ViewModel(){

    var movieUiState:MovieUiState by mutableStateOf(MovieUiState.Loading)
        private set

    public var enableRefreshState:Boolean by mutableStateOf(true)
        private set

    private var currentJob: Job? = null

    init{
        getMoviePoster()
    }

    private fun getMoviePoster() {
        if (currentJob?.isActive == true) {
            currentJob?.cancel()
            enableRefreshState = true
        } else {
            currentJob = viewModelScope.launch {
                enableRefreshState = false
                movieUiState = try {
                    // Get Random Page of the database
                    val numeroRandomPage = Random.nextInt(0, 100) // Número random entre 0 y 99
                    val listMoviesResult =
                        MovieApi.retrofitService.getMovieDetails(numeroRandomPage).results

                    // Get Random Title and his Id
                    val numeroRandomMovie = Random.nextInt(0, listMoviesResult.size)
                    val titleMovie = listMoviesResult[numeroRandomMovie].title
                    val idMovie = listMoviesResult[numeroRandomMovie].id
                    val posterPathMovie = listMoviesResult[numeroRandomMovie].poster_path

                    // Data class a desplegar
                    val movie = MovieSimple(
                        id = idMovie,
                        title = titleMovie,
                        posterUrl = posterPathMovie ?: "/no-image.jpg"
                    )

                    println(movie)

                    // Return
                    MovieUiState.Success(movie, listMoviesResult)
                } catch (e: IOException) {
                    MovieUiState.Error
                } finally {
                    enableRefreshState = true
                }
            }
        }
    }

    public fun refreshMoviePoster(){
        getMoviePoster()
    }

}