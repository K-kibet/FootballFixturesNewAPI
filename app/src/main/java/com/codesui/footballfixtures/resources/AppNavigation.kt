package com.codesui.footballfixtures.resources

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codesui.footballfixtures.screens.StartScreen
import com.codesui.footballfixtures.screens.fixture.FixtureScreen
import com.codesui.footballfixtures.screens.league.LeagueDetails
import com.codesui.footballfixtures.screens.player.PlayerScreen

@Composable
fun AppNavigation(runAds :() -> Unit, openAds: () -> Unit, rewardedAds : () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.startScreen, builder ={
        composable(Routes.startScreen) {
            StartScreen(navController, runAds, openAds)
        }
        composable(Routes.mainScreen) {
            //AboutScreen(navController, runAds)
            NavDrawer(navController, runAds, openAds, rewardedAds)
        }

        composable(Routes.leagueScreen+ "/{id}") {
            val id = it.arguments?.getString("id")
            LeagueDetails(navController, id?: "39")
        }

        composable(Routes.playerScreen+ "/{id}") {
            val playerId = it.arguments?.getString("id")
            PlayerScreen(playerId?: "276")
        }

        composable(Routes.fixtureScreen+ "/{id}") {
            val id = it.arguments?.getString("id")
            FixtureScreen(navController, id?:"592872")
        }
    } )
}