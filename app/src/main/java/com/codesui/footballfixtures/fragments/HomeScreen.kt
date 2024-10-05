package com.codesui.footballfixtures.fragments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.resources.FeaturedLive
import com.codesui.footballfixtures.screens.fixture.Fixture
import com.codesui.footballfixtures.screens.league.FeaturedLeague
import com.codesui.footballfixtures.screens.player.PlayerCard
import com.google.gson.JsonObject

@Composable
fun HomeScreen(navController: NavController, runAds: () -> Unit, rewardedAds: () -> Unit) {
    val leagues = remember { mutableStateOf<List<JsonObject>?>(null) }
    val topScorers = remember { mutableStateOf<List<JsonObject>?>(null) }
    val fixtures = remember { mutableStateOf<List<JsonObject>?>(null) }

    var leagueId by remember { mutableStateOf("61") }

    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }


    val headers = mapOf("x-rapidapi-key" to stringResource(id = R.string.api_key), "x-rapidapi-host" to "v3.football.api-sports.io")
    val params = mapOf("league" to leagueId, "season" to "2024")
    val fixturesParams = mapOf("live" to "all", "timezone" to "Africa/Addis_Ababa")

    LaunchedEffect(leagueId) {
        try {
            val response = RetrofitClient.apiService.getLeagues(headers)
            val responseArray = response.getAsJsonArray("response")
            leagues.value = responseArray.map { it.asJsonObject }

            val topScorersResponse = RetrofitClient.apiService.getTopScorers(headers, params)
            val responseTopScorersArray = topScorersResponse.getAsJsonArray("response")
            topScorers.value = responseTopScorersArray.map { it.asJsonObject }

            val fixturesResponse = RetrofitClient.apiService.getFixtures(headers, fixturesParams)
            val responseFixturesArray = fixturesResponse.getAsJsonArray("response")
            fixtures.value = responseFixturesArray.map { it.asJsonObject }
        } catch (e: Exception) {
            error.value = e.message
        } finally {
            isLoading.value = false
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        when {
            isLoading.value -> {
                // Display a loading message
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error.value != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error: ${error.value}")
                }
            }

            leagues.value != null -> {
                LazyRow(
                    contentPadding = PaddingValues(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items = leagues.value!!) { item ->
                        FeaturedLeague(item, leagueId, onMutableValueChange = {leagueId = it})
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Live Matches",
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Live Icon",
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
        }
        LazyRow(
            contentPadding = PaddingValues(5.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
             val item = 20
            items (item) { items ->
                FeaturedLive()
            }
        }

        Row(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fixtures",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {

                    },
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "see all",
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Live Icon",
                    modifier = Modifier.size(25.dp)
                )
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

            fixtures.value != null -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items = fixtures.value!!) { fixture ->
                        Fixture(fixture, navController)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Top Scorers",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {

                    },
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "see all",
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Live Icon",
                    modifier = Modifier.size(20.dp)
                )
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
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 5.dp, vertical = 0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items = topScorers.value!!) { player ->
                        PlayerCard(navController, player)
                    }
                }
            }
        }
    }
}
