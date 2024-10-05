package com.codesui.footballfixtures.screens.league

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.screens.player.PlayerCard
import com.google.gson.JsonObject

@Composable
fun TopScorersScreen(navController: NavController, leagueId: String) {


    val topScorers = remember { mutableStateOf<List<JsonObject>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    val headers = mapOf(
        "x-rapidapi-key" to stringResource(id = R.string.api_key),
        "x-rapidapi-host" to "v3.football.api-sports.io"
    )
    val params = mapOf("league" to leagueId, "season" to "2024")



    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getTopScorers(headers, params)
            val responseArray = response.getAsJsonArray("response")
            topScorers.value = responseArray.map { it.asJsonObject }
        } catch (e: Exception) {
            error.value = e.message
        } finally {
            isLoading.value = false
        }
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

        topScorers.value != null -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = topScorers.value!!) { player ->
                    PlayerCard(navController, player)
                }
            }
        }
    }
}