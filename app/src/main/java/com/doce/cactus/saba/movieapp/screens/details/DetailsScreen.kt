package com.doce.cactus.saba.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.doce.cactus.saba.movieapp.model.Movie
import com.doce.cactus.saba.movieapp.model.getMovies
import com.doce.cactus.saba.movieapp.widgets.MovieRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@Composable
fun DetailsScreen(navController: NavController, movieId: String?){

    val movie = getMovies().filter { movie: Movie ->
        movie.id == movieId
    }[0]

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }.scale(2F).padding(horizontal = 20.dp)
                    )
                    Text(text = movie.title, style = TextStyle(fontSize = 30.sp ,fontWeight = FontWeight.Bold))


                }
            }
        }
    ) {
        Detail(navController, movie)
    }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Detail(navController: NavController, movie: Movie){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
// Display 10 items

            MovieRow(movie)
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)

            HorizontalPager(count = movie.images.size) { page ->
                Image(painter = rememberImagePainter(data = movie.images[page]),
                    contentDescription = "Movie Poster",

                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop)
            }

        }

    }
}