package com.codesui.footballfixtures.fragments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.resources.IndeterminateCircularIndicator
import com.codesui.footballfixtures.screens.fixture.Fixture
import com.codesui.powerkingtips.ads.AdmobBanner
import com.google.gson.JsonObject

@Composable
fun FixturesScreen(navController: NavController, runAds: () -> Unit, rewardedAds: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize()){
        val fixtures = remember { mutableStateOf<List<JsonObject>?>(null) }
        val isLoading = remember { mutableStateOf(true) }
        val error = remember { mutableStateOf<String?>(null) }

        val headers = mapOf("x-rapidapi-key" to stringResource(id = R.string.api_key), "x-rapidapi-host" to "v3.football.api-sports.io")
        val params = mapOf("live" to "all", "timezone" to "Africa/Addis_Ababa")
        LaunchedEffect(Unit) {
            try {
                val response = RetrofitClient.apiService.getFixtures(headers, params)
                val responseArray = response.getAsJsonArray("response")
                fixtures.value = responseArray.map { it.asJsonObject }
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
        }


        when {
            isLoading.value -> {
                IndeterminateCircularIndicator()
            }
            error.value != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${error.value}")
                }
            }

            fixtures.value != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = fixtures.value!!) { fixture ->
                        Fixture(fixture, navController)
                    }
                }
                AdmobBanner()
            }
        }
    }
}