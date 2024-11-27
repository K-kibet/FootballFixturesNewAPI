package com.codesui.footballfixtures.screens.league

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.resources.EmptyScreen
import com.codesui.footballfixtures.resources.ErrorDialog
import com.codesui.footballfixtures.resources.IndeterminateCircularIndicator
import com.codesui.footballfixtures.resources.NoInternetDialog
import com.codesui.footballfixtures.resources.isInternetAvailable
import com.codesui.footballfixtures.screens.player.PlayerCard
import com.google.gson.JsonObject

@Composable
fun TopScorersScreen(navController: NavController, leagueId: String, runAds :() -> Unit) {


    val topScorers = remember { mutableStateOf<List<JsonObject>?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    val params = mapOf("action" to "get_topscorers", "league_id" to leagueId, "APIkey" to stringResource(id = R.string.api_key))
    var isButtonClicked by remember { mutableStateOf(true) }
    LaunchedEffect(isButtonClicked) {
        if (isButtonClicked) {
            try {
                val response = RetrofitClient.apiService.getTopScorers(params)
                topScorers.value = response.map { it.asJsonObject }
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
            isButtonClicked = false // Reset state after task
        }
    }

    when {
        isLoading.value -> {
            IndeterminateCircularIndicator()
        }
        error.value != null -> {
            when{
                !isInternetAvailable(LocalContext.current) -> {
                    NoInternetDialog {
                        isButtonClicked = true
                        isLoading.value = true
                        error.value = null
                    }
                }

                isInternetAvailable(LocalContext.current) -> {
                    ErrorDialog()
                }
            }
        }

        topScorers.value != null -> {
            if (topScorers.value!!.isEmpty()) {
                EmptyScreen("top scorers")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(items = topScorers.value!!) { player ->
                        PlayerCard(navController, player, runAds)
                    }
                }
            }
        }
    }
}