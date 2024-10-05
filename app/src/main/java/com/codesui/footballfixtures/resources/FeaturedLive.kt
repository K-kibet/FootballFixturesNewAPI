package com.codesui.footballfixtures.resources

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codesui.footballfixtures.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FeaturedLive() {
    Card (
        modifier = Modifier
            .padding(2.dp)
            .width(320.dp)
            .wrapContentHeight()
            .border(
                1.dp,
                colorResource(id = R.color.purple_700),
                RoundedCornerShape(5)
            )
            .clickable {
            },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            //verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxHeight()
                        .wrapContentWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.live),
                    contentDescription = "league Image"
                )
                Text(
                    text = "UEFA Champions League",
                    modifier = Modifier
                        .weight(2.5f)
                        .fillMaxWidth(),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Clip
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "league Image"
                    )
                    Text(
                        text = "Real Madrid",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }



                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1.5f),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "0-3",
                        fontSize = 18.sp,
                        fontWeight =  FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                            .weight(1f)
                            .border(
                                1.dp,
                                colorResource(id = R.color.purple_700),
                                CircleShape
                            )
                            .padding(5.dp)
                    )
                    Text(
                        text = "115 min",
                        fontSize = 16.sp,
                        fontWeight =  FontWeight.Bold,
                        color = Color(0xFF4CAF50),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize()
                            .weight(1f)
                    )
                }




                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(2f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                        contentDescription = "league Image"
                    )
                    Text(
                        text = "Real Madrid",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Clip
                    )
                }
            }
        }
    }
}