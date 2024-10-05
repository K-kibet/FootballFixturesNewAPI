package com.codesui.footballfixtures.fragments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.codesui.footballfixtures.resources.LivescoreCard
import com.codesui.footballfixtures.screens.league.TeamStats

@Composable
fun LivescoresScreen(navController: NavController, runAds: () -> Unit, rewardedAds: () -> Unit) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        var livematches = 20
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(livematches) {livematch ->
                LivescoreCard(navController)
            }
        }
    }
}