package com.example.randommoviessuggestion.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.randommoviessuggestion.R
import com.example.randommoviessuggestion.model.Movie
import com.example.randommoviessuggestion.model.MovieSimple
import com.example.randommoviessuggestion.viewmodel.MovieUiState


// # # #   ESTRUCTURA COMPOSABLE PARA EL HOME SCREEN   # # #
@Composable
fun HomeScreen(
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier
){
    // Se modifica la apariencia de la vista dependiendo del estado de la variable movieUiState
    when( movieUiState){
        is MovieUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MovieUiState.Success -> SuccessScreen(movies = movieUiState.movie, apiResponse = movieUiState.apiResponse, modifier.fillMaxSize())
        is MovieUiState.Error -> ErrorScreen(modifier =  modifier.fillMaxSize())

    }
}

// # # #   ESTRUCTURA COMPOSE PARA LA SCREEN CUANDO EL GET FUE SUCCESS    # # #
@Composable
fun SuccessScreen(movies: MovieSimple, apiResponse: List<Movie>, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoviePosterCard(movie = movies, apiResponse = apiResponse)
    }
}

// # # # ESTRUCTURA COMPOSE PARA LA SCREEN DE CARGA    # # #
@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.loader),
            contentDescription = "Loading")
    }

}

// # # #   ESTRUCTURA COMPOSE PARA LA SCREEN DE ERROR    # # #
@Composable
fun ErrorScreen(modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.error_load)
            , contentDescription = "Error Loading" )
        Text(text = stringResource(R.string.problem_with_connection))
    }
}

