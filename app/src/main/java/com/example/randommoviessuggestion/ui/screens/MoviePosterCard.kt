package com.example.randommoviessuggestion.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.randommoviessuggestion.R
import com.example.randommoviessuggestion.model.Movie
import com.example.randommoviessuggestion.model.MovieSimple

// # # #   ESTRUCTURA COMPOSE PARA UNA ENTRADA DE LA ESTRUCTURA PARA VISUALIZAR EL POSTER DE LA PELÍCULA     # # #
@Composable
fun MoviePosterCard(movie: MovieSimple, apiResponse: List<Movie>, modifier: Modifier = Modifier){
    LazyColumn {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Unspecified,
                    disabledContentColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified
                )
            ) {
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(bottom = 20.dp, top = 20.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(movie.base_url + movie.posterUrl)
                            .crossfade(true )
                            .build(),
                        contentDescription = stringResource(R.string.poster_de_la_pelicula_obtenida),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .border(7.dp, Color.Red),
                        error = painterResource(R.drawable.error_load),
                        placeholder = painterResource(R.drawable.loader),
                        contentScale = ContentScale.Fit
                    )
                    //Text(text = apiResponse.toString(), modifier = Modifier.padding(top = 20.dp))
                }
            }
        }
    }


}