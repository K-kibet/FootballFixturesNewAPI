package com.codesui.footballfixtures.screens.fixture


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.resources.Routes
import com.google.gson.JsonObject

@Composable
fun Fixture(item:JsonObject,navController: NavController) {
    val id = item.getAsJsonObject("fixture").get("id").asString
    val homeTeam = item.getAsJsonObject("teams").getAsJsonObject("home").get("name").asString
    val awayTeam = item.getAsJsonObject("teams").getAsJsonObject("away").get("name").asString
    val homeTeamIcon = item.getAsJsonObject("teams").getAsJsonObject("home").get("logo").asString
    val awayTeamIcon = item.getAsJsonObject("teams").getAsJsonObject("away").get("logo").asString
    val date = item.getAsJsonObject("fixture").get("date").asString.split("T")[0]
    val time = item.getAsJsonObject("fixture").get("date").asString
        .split("T")[1].split("Z")[0]
        .split("+")[0]
    Card(
        modifier = Modifier
            .padding(3.dp)
            .width(340.dp)
            .wrapContentHeight()
            .padding(2.dp)
            .clickable {
                navController.navigate(
                    route = Routes.fixtureScreen + "/${id}"
                )
            },
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(3.dp)
                .height(90.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = homeTeamIcon,
                    contentDescription = stringResource(id = R.string.app_name),
                    placeholder = painterResource(id = R.drawable.fball),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )

                Text(
                    text = homeTeam,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Clip
                )

            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .weight(1.3f)
                    .padding(0.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = date,
                    fontSize = 18.sp,
                    fontWeight =  FontWeight.Bold,
                    modifier = Modifier
                        .border(
                            1.dp,
                            colorResource(id = R.color.purple_700),
                            CircleShape
                        ).padding(5.dp)
                )
                Text(
                    text = time,
                    fontSize = 16.sp,
                    fontWeight =  FontWeight.Bold
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = awayTeamIcon,
                    contentDescription = stringResource(id = R.string.app_name),
                    placeholder = painterResource(id = R.drawable.fball),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = awayTeam,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Clip
                )

            }
        }
    }
}
