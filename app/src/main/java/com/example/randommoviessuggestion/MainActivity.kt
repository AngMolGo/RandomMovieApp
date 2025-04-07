package com.example.randommoviessuggestion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.randommoviessuggestion.ui.MovieApp
import com.example.randommoviessuggestion.ui.theme.RandomMoviesSuggestionTheme

// # # # ESTRUCTURA PRINCIPAL DEL PROGRAMA # # #

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RandomMoviesSuggestionTheme {
                MovieApp() // Estructura principal del programa
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomMoviesSuggestionTheme {
        Greeting("Android")
    }
}*/