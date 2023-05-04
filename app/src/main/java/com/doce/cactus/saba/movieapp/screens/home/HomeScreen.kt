package com.doce.cactus.saba.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.doce.cactus.saba.movieapp.model.Movie
import com.doce.cactus.saba.movieapp.model.getMovies
import com.doce.cactus.saba.movieapp.navigation.MovieScreens
import com.doce.cactus.saba.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Transparent,
            elevation = 0.dp) {
            Text(text = "Movies", style = TextStyle(fontSize = 30.sp ,fontWeight = FontWeight.Bold))
        }

    }) {
        MainContent(navController, getMovies())
    }
}

@Composable
fun MainContent(navController: NavController, movieList: List<Movie>){
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn{
            items(items = movieList){
                MovieRow(it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/${movie.id}")
                    Log.d("movie", movie.title)
                }

            }
        }



    }
}

