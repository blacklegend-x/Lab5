package com.example.lab5.widegts

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size


import com.example.lab5.model.Movie
import com.example.lab5.model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id.toString()) },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)), elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(modifier = Modifier.padding(12.dp).size(100.dp), shape = RectangleShape, elevation = 4.dp) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "Movie image")
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(Size.ORIGINAL)
                        .crossfade(false)
                        .build()
                )
                Image(painter = painter, contentDescription = "Movie image")
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Release: ${movie.year}", style = MaterialTheme.typography.caption)
                AnimatedVisibility(
                    visible = expanded,
                    enter = slideInVertically { with(density) { -40.dp.roundToPx() } } + expandVertically(expandFrom = Alignment.CenterVertically) + fadeIn(
                        initialAlpha = 0.4f
                    ),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut()
                ) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot: ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Magenta,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                ) {
                                    append(movie.plot)
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Blue,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("\n Mobile kurs4")
                                }
                            }, modifier = Modifier.padding(6.dp)
                        )
                    }
                }
                        Divider(modifier = Modifier.padding(3.dp))
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Down arrow",
                            modifier = Modifier.size(25.dp).clickable { expanded = !expanded },
                            tint = Color.LightGray
                        )


            }
        }
    }
}