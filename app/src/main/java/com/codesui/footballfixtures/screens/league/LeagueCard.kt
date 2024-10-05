package com.codesui.footballfixtures.screens.league

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.resources.Routes
import com.google.gson.JsonObject

@Composable
fun LeagueCard(item: JsonObject, navController: NavController){

    val league = item.getAsJsonObject("league")
    val leagueLogo = league.get("logo").asString
    val leagueName = league.get("name")?.asString ?: "Unknown League"
    val leagueCountry = item.getAsJsonObject("country").get("name")?.asString
    val leagueId = league.get("id").asString

    val start = item.getAsJsonArray("seasons").last().asJsonObject.get("start").asString
    val end = item.getAsJsonArray("seasons").last().asJsonObject.get("end").asString


    Card (
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                navController.navigate(
                    route = Routes.leagueScreen + "/${leagueId}"
                )
            },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = leagueLogo,
                contentDescription = stringResource(id = R.string.app_name),
                placeholder = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(70.dp)
                    .wrapContentHeight()
                    .clip(CircleShape),
            )
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = leagueCountry + " - " + leagueName,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = start + " -> " + end,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                )
            }
        }

    }
}