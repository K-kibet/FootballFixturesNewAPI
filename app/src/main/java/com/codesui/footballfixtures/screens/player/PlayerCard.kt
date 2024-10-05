package com.codesui.footballfixtures.screens.player

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
fun PlayerCard(navController: NavController, player: JsonObject){

    val details = player.getAsJsonObject("player")
    val photo = details.get("photo").asString
    val name = details.get("name").asString
    val playerId = details.get("id").asString
    val stats = player.getAsJsonArray("statistics").first().asJsonObject
    val clubLogo = stats.getAsJsonObject("team").get("logo").asString
    val goals = stats.getAsJsonObject("goals").get("total").asString
    val position = stats.getAsJsonObject("games").get("position").asString

    Card (
        modifier = Modifier
            .width(180.dp)
            .wrapContentHeight()
            .padding(2.dp)
            .clickable {
                navController.navigate(
                    route = Routes.playerScreen + "/${playerId}"
                )
            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)

    ) {
        Box (
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = clubLogo,
                contentDescription = stringResource(id = R.string.app_name),
                placeholder = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(

                            Color.LightGray,
                            Color.Transparent,
                            Color.Gray,
                            Color.DarkGray
                        ),
                        startY = 50f
                    )
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(
                    "${goals} goals",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .border(
                            1.dp,
                            Color.White,
                            CircleShape
                        )
                        .padding(5.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    position,
                    style = TextStyle(
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                )

                AsyncImage(
                    model = photo,
                    contentDescription = stringResource(id = R.string.app_name),
                    placeholder = painterResource(id = R.mipmap.ic_launcher_foreground),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth(.60f)
                        .fillMaxHeight(.6f)
                        .align(alignment = Alignment.TopEnd)
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
                Text(
                    name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}