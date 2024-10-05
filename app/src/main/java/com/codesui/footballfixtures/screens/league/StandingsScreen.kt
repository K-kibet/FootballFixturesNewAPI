package com.codesui.footballfixtures.screens.league

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.google.gson.JsonObject

@Composable
fun StandingsScreen(navController: NavController, leagueId: String){
    //Toast.makeText(LocalContext.current, leagueId, Toast.LENGTH_LONG ).show()

    val standings = remember { mutableStateOf<List<JsonObject>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    // Define your headers and query parameters
    val headers = mapOf("x-rapidapi-key" to stringResource(id = R.string.api_key), "x-rapidapi-host" to "v3.football.api-sports.io")
    val params = mapOf("league" to leagueId, "season" to "2024")

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getStandings(headers, params)
            val responseObject = response.getAsJsonArray("response").asJsonArray
                .first().asJsonObject.getAsJsonObject("league")
            val standingsArray = responseObject.getAsJsonArray("standings")
                .first().asJsonArray
            standings.value = standingsArray.map { it.asJsonObject }
        } catch (e: Exception) {
            error.value = e.message
        } finally {
            isLoading.value = false
        }
    }

    Column (
        modifier = Modifier.fillMaxSize()
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "Club",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4.5f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "MP",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "W",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "D",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "L",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "GD",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            Text(
                text = "PT",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
        }


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

            standings.value != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items = standings.value!!) { standing ->
                        //LeagueCard(league, navController)
                        TeamStats(standing, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TeamStats(team: JsonObject, navController: NavController) {

    val rank = team.get("rank").asString
    val teamLogo = team.getAsJsonObject("team").get("logo").asString
    val teamName = team.getAsJsonObject("team").get("name").asString
    val teamId = team.getAsJsonObject("team").get("id")?.asString
    val teamPoints = team.get("points").asString
    val goalDiff = team.get("goalsDiff").asString
    val gamesPlayed = team.getAsJsonObject("all").get("played").asString
    val teamWin = team.getAsJsonObject("all").get("win").asString
    val teamDraw = team.getAsJsonObject("all").get("draw").asString
    val teamLose = team.getAsJsonObject("all").get("lose").asString

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //todo
            }.drawBehind {
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
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(4.5f)
                .wrapContentHeight(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rank,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Visible,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .wrapContentHeight(Alignment.CenterVertically),
            )
            AsyncImage(
                model = teamLogo,
                contentDescription = stringResource(id = R.string.app_name),
                placeholder = painterResource(id = R.drawable.fball),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(2.dp)
                    .width(35.dp)
                    .height(35.dp)
                    .clip(CircleShape)
            )
            Text(
                text = teamName,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3.5f)
                    .wrapContentHeight(Alignment.CenterVertically),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            text = gamesPlayed,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
        Text(
            text = teamWin,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
        Text(
            text = teamDraw,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
        Text(
            text = teamLose,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
        Text(
            text = goalDiff,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
        Text(
            text = teamPoints,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .wrapContentHeight(Alignment.CenterVertically),
        )
    }
}