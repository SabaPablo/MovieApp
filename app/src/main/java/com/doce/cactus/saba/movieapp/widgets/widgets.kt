package com.doce.cactus.saba.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.doce.cactus.saba.movieapp.model.Movie
import com.doce.cactus.saba.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick:(Movie)-> Unit = {}){

    val expanded = remember{
        mutableStateOf(false)
    }

    Card(modifier =
    Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        .clickable {
            onItemClick.invoke(movie)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start){
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp) {
                Image(painter = rememberImagePainter(data = movie.images[0],
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }), contentDescription = "Movie Poster")

            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6)
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption)
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption)
                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 13.sp)
                            ){
                                append("Plot: ")
                                withStyle(style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )){
                                    append(movie.plot)
                                }
                            }

                        }, modifier = Modifier.padding(6.dp))
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)


                    }
                }
                Icon(
                    imageVector = if(expanded.value){Icons.Filled.KeyboardArrowUp}else {Icons.Filled.KeyboardArrowDown},
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        },
                    tint = Color.DarkGray
                )
            }

        }

    }
}
