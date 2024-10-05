package com.codesui.footballfixtures.screens.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.Requests.RetrofitClient
import com.codesui.footballfixtures.resources.Topbar
import com.codesui.footballfixtures.screens.league.TeamStats
import com.google.gson.JsonObject

@Composable
fun PlayerScreen(playerId: String) {
    /*val player = remember { mutableStateOf<JsonObject?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val error = remember { mutableStateOf<String?>(null) }

    val headers = mapOf("x-rapidapi-key" to stringResource(id = R.string.api_key), "x-rapidapi-host" to "v3.football.api-sports.io")
    val params = mapOf("id" to playerId, "season" to "2024")

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getPlayer(headers, params)
            val responseObject = response.getAsJsonArray("response").asJsonArray
                .first().asJsonObject
            player.value = responseObject
        } catch (e: Exception) {
            error.value = e.message
        } finally {
            isLoading.value = false
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Topbar(title = "Player Screen") {
            //navController.popBackStack()
            //runAds.invoke()
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

            player.value != null -> {
                val item = player.value!!.getAsJsonObject("player")
                val age = item.get("age").asInt
                val firstName = item.get("firstname")?.asString ?: "null"
                val lastName = item.get("lastname")?.asString ?: "null"
                val photo = item.get("photo")?.asString ?: "null"
                //val injured = item.get("injured").asBoolean

                val stats = player.value!!.getAsJsonArray("statistics").first().asJsonObject
                val position = stats.getAsJsonObject("games").get("position")?.asString ?: "null"
                val clubLogo = stats.getAsJsonObject("team").get("logo")?.asString ?: "null"
                val matches = stats.getAsJsonObject("games").get("appearences")?.asInt ?: 0
                val goals = stats.getAsJsonObject("goals").get("total")?.asInt ?: 0
                val assists = stats.getAsJsonObject("goals").get("assists")?.asInt ?: 0
                //val saves = stats.getAsJsonObject("goals").get("saves")?.asInt ?: 0
                val form = stats.getAsJsonObject("games").get("rating")?.asString ?: "null"
                val minutes = stats.getAsJsonObject("games").get("minutes")?.asInt ?: 0
                val accuracy = stats.getAsJsonObject("passes").get("accuracy")?.asInt ?: 0


                val league = stats.getAsJsonObject("league").get("name")?.asString ?: "null"
                val yellowCards = stats.getAsJsonObject("cards").get("yellow")?.asInt ?: 0
                val redCards = stats.getAsJsonObject("cards").get("red")?.asInt ?: 0
                Column (modifier = Modifier.fillMaxSize()) {
                    //Text(text = player.value!!.getAsJsonArray("statistics").first().asJsonObject.toString())
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.3f)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.onPrimary
                                    )
                                )
                            ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .fillMaxHeight()
                                .weight(1f)
                                .padding(5.dp),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                age.toString() + "`",
                                fontWeight = FontWeight.Normal,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 20.sp,
                                textAlign = TextAlign.End,
                                color = Color.White,
                                fontStyle = FontStyle.Italic,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                verticalArrangement = Arrangement.SpaceAround,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = firstName,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    lastName,
                                    style = TextStyle(
                                        //color = Color.White,
                                        fontSize = 20.sp,
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Bold,
                                    ),
                                    modifier = Modifier.fillMaxWidth(.7f)
                                )
                            }
                        }
                        AsyncImage(
                            model = photo,
                            contentDescription = stringResource(id = R.string.app_name),
                            placeholder = painterResource(id = R.mipmap.ic_launcher_foreground),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .wrapContentHeight()
                                .padding(5.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                    Row (

                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = clubLogo,
                            contentDescription = stringResource(id = R.string.app_name),
                            placeholder = painterResource(id = R.mipmap.ic_launcher_foreground),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(5.dp)
                                .width(40.dp)
                                .height(40.dp)
                                .clip(CircleShape)
                        )
                        Text(
                            text = position.uppercase(),
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Start,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3.5f)
                                .wrapContentHeight(Alignment.CenterVertically),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Row (
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            text = "Matches",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = "Goals",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = "Assists",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = "Saves",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .drawBehind {
                                val strokeWidth = Stroke.DefaultMiter
                                val y = size.height // - strokeWidth

                                drawLine(
                                    Color.LightGray,
                                    Offset(0f, y),
                                    Offset(size.width, y),
                                    strokeWidth
                                )
                            }
                            .padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = matches.toString(),
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = goals.toString(),
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = assists.toString(),
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                        Text(
                            text = "saves.toString()",
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .wrapContentHeight(Alignment.CenterVertically),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Text(
                                text = "Form",
                            )

                            Text(
                                text = "Minutes",
                            )
                            Text(
                                text = "Accuracy",
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .drawBehind {
                                        drawCircle(
                                            color = Color.Gray,
                                            radius = this.size.maxDimension
                                        )
                                    },
                                text = form.substring(0,3),
                                color = Color.White
                            )

                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .drawBehind {
                                        drawCircle(
                                            color = Color.Gray,
                                            radius = this.size.maxDimension
                                        )
                                    },
                                text = minutes.toString(),
                                color = Color.White
                            )

                            Text(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .drawBehind {
                                        drawCircle(
                                            color = Color.Gray,
                                            radius = this.size.maxDimension
                                        )
                                    },
                                text = accuracy.toString(),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }*/
    Text(text = playerId)
}
