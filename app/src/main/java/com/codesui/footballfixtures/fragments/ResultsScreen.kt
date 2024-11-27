package com.codesui.footballfixtures.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.resources.ErrorDialog
import com.codesui.footballfixtures.resources.IndeterminateCircularIndicator
import com.codesui.footballfixtures.resources.NoInternetDialog
import com.codesui.footballfixtures.resources.isInternetAvailable
import com.codesui.footballfixtures.screens.fixture.Fixture
import com.codesui.powerkingtips.ads.AdmobBanner
import com.google.gson.JsonObject
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun ResultsScreen(navController: NavController, runAds: () -> Unit, rewardedAds: () -> Unit) {
    Column (modifier = Modifier.fillMaxSize()){
        val fixtures = remember { mutableStateOf<List<JsonObject>?>(null) }
        val isLoading = remember { mutableStateOf(true) }
        val error = remember { mutableStateOf<String?>(null) }
        val params = mapOf("action" to "get_events", "from" to getCurrentDate(), "to" to getCurrentDate(),"match_live" to "0","timezone" to "Africa/Nairobi", "APIkey" to stringResource(id = R.string.api_key))
        var isButtonClicked by remember { mutableStateOf(true) }
        LaunchedEffect(isButtonClicked) {
            if (isButtonClicked) {
                try {
                    val response = RetrofitClient.apiService.getFixtures(params)
                    fixtures.value = response.map { it.asJsonObject }
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

            fixtures.value != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = fixtures.value!!) { fixture ->
                        //Fixture(fixture, navController, runAds)

                        when{
                            fixture.get("match_live").asString.equals("0") && fixture.get("match_status").asString.equals("Finished") -> {
                                Fixture(fixture, navController, runAds)
                            }
                        }
                    }
                }
                AdmobBanner()
            }
        }
    }
}
private fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = Date()
    return dateFormat.format(currentDate)
}