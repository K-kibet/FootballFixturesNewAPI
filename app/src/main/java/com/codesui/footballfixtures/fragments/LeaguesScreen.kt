package com.codesui.footballfixtures.fragments

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.screens.league.LeagueCard
import com.google.gson.JsonObject

@Composable
fun LeaguesScreen(navController: NavController, runAds: () -> Unit, rewardedAds: () -> Unit){
    val leagues = remember { mutableStateOf<List<JsonObject>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    val headers = mapOf("x-rapidapi-key" to stringResource(id = R.string.api_key), "x-rapidapi-host" to "v3.football.api-sports.io")
    //val params = mapOf("league" to leagueId, "season" to "2024")

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getLeagues(headers)
            val responseArray = response.getAsJsonArray("response")
            leagues.value = responseArray.map { it.asJsonObject }
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

        leagues.value != null -> {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = leagues.value!!) { league ->
                    LeagueCard(league, navController)
                }
            }
        }
    }
}