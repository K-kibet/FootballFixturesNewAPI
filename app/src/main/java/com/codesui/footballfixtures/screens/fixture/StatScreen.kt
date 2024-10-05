package com.codesui.footballfixtures.screens.fixture

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.codesui.footballfixtures.R
import com.codesui.powerkingtips.ads.AdmobBanner

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StatScreen (){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "10%",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Possession",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "10%",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Color.LightGray,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "10",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Shots on Goal",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "8",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "2",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Shots off Goal",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Color.LightGray,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "4",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier.padding(5.dp),
                text = "Total Shots",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "6",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "6",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Corner Kicks",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Color.LightGray,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "2",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Fouls",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "6",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Offsides",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Color.LightGray,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "2",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Ball Possession",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "6",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Yellow Cards",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .drawBehind {
                    drawRect(
                        color = Color.LightGray,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "2",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "Red Cards",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .padding(5.dp),
                text = "3",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        AdmobBanner()
    }
}