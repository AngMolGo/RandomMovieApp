@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.randommoviessuggestion.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.randommoviessuggestion.R
import com.example.randommoviessuggestion.ui.screens.HomeScreen
import com.example.randommoviessuggestion.viewmodel.MovieViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randommoviessuggestion.network.MovieApi
import com.example.randommoviessuggestion.viewmodel.MovieUiState
import kotlinx.coroutines.launch
import java.io.IOException


// # # # ESTRUCTURA COMPOSABLE PRINCIPAL DE LA APP # # #
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(modifier: Modifier = Modifier){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val movieViewModel:MovieViewModel = viewModel()
    // Se utiliza una estructura Scaffold para obtener una barra horizontal para alojar el título de la app.
    // Se puede editar para agregar un menu burguer y otras cosas.
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopAppTitle(scrollBehavior = scrollBehavior) }, // Barra horizontal para el título
        floatingActionButton = {
            FloatingActionButton(
                onClick = { movieViewModel.refreshMoviePoster() },
                containerColor = colorResource(R.color.RedMovies),
                shape = CircleShape
                //modifier = Modifier.size(100.dp)
            ) {
            Icon(Icons.Default.Refresh, contentDescription = "Refresh",
                modifier = Modifier.size(75.dp).padding(15.dp)
            )
        }
        }
    ) { innerPadding -> // ← Este "innerPadding" es importante para
        // Se agrega un Surface para dejar un lienzo/canva/surface disponible para albergar las vistas de la app.
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Como la vista de HomeScreen tiene objetos dinámicos se necesita un ViewModel para gestionar los cambios de datos y así.
            HomeScreen(movieUiState = movieViewModel.movieUiState) // Se le pasa como parámetro la instancia de ViewModel para que pueda cambiar
        }
    }
}


// # # # ESTRUCTURA COMPOSABLE PARA EL TÍTULO DE LA APP # # #
// Se puede modificar para
@Composable
fun TopAppTitle(scrollBehavior: TopAppBarScrollBehavior, modifier:Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarColors(
            containerColor = colorResource(R.color.RedMovies),
            scrolledContainerColor = Color.Transparent,
            navigationIconContentColor = Color.Transparent,
            titleContentColor = Color.White,
            actionIconContentColor = Color.Transparent
        )
    )
}


@Preview
@Composable
fun TopAppTitlePreview(){
    TopAppTitle(scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior())
}

