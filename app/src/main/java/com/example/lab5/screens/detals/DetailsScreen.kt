package com.example.lab5.screens.detals

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.lab5.widegts.MovieRow
import com.example.lab5.model.Movie
import com.example.lab5.model.getMovies


@SuppressLint("UnusedTransitionTargetStateParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: Int?) {
    val newMovieList = getMovies().filter { movie -> movie.id == movieId }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Movies")
                }
            }
        }
    ) {
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                MovieRow(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizontalScrollableImagesView(newMovieList)
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImagesView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images.size) { index ->
            Card(modifier = Modifier.padding(12.dp).size(240.dp), elevation = 5.dp) {
                val painter = rememberAsyncImagePainter(newMovieList[0].images[index])
                Image(
                    painter = painter,
                    contentDescription = "Movie Image",
                )
            }
        }
    }
}