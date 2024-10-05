package com.codesui.footballfixtures.screens.fixture

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.screens.player.PlayerCard
import com.google.gson.JsonObject

@Composable
fun LineupScreen(navController: NavController, fixtureId: String) {
    val homeLineup = remember { mutableStateOf<List<JsonObject>?>(null) }
    val awayLineup = remember { mutableStateOf<List<JsonObject>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    val res = remember { mutableStateOf<String?>(null)}

    val headers = mapOf(
        "x-rapidapi-key" to stringResource(id = R.string.api_key),
        "x-rapidapi-host" to "v3.football.api-sports.io"
    )
    val params = mapOf("fixture" to fixtureId)


    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getLineups(headers, params)
            res.value = response.toString()
            val responseHome = response.getAsJsonArray("response")
                .first().asJsonObject.getAsJsonArray("startXI")
            homeLineup.value = responseHome.map { it.asJsonObject }

            val responseAway = response.getAsJsonArray("response")
                .last().asJsonObject.getAsJsonArray("startXI")
            awayLineup.value = responseAway.map { it.asJsonObject }
        } catch (e: Exception) {
            error.value = e.message
        } finally {
            isLoading.value = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            Text(text = res.value.toString())
            /*
            when {
                isLoading.value -> {
                    // Display a loading message
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                error.value != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error: ${error.value}")
                    }
                }

                homeLineup.value != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().weight(1f),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(items = homeLineup.value!!) { player ->
                            LineupCard(navController, player)
                        }
                    }
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                }
                awayLineup.value != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().weight(1f),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(items = awayLineup.value!!) { player ->
                            LineupCard(navController, player)
                        }
                    }
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                }
            }
             */
        }
    }
}


@Composable
fun LineupCard(navController: NavController, player: JsonObject) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = Stroke.DefaultMiter
                val y = size.height

                drawLine(
                    Color.LightGray,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.fball),
            contentDescription = "Artist image"
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(
                "Mateo Kovacic",
                fontSize = 17.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                "12",
                modifier = Modifier
                    .padding(2.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Gray,
                            radius = this.size.maxDimension / 2
                        )
                    }
                    .align(alignment = Alignment.End),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}