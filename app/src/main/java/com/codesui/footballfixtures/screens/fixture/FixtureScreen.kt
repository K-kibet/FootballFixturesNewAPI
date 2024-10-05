package com.codesui.footballfixtures.screens.fixture

import androidx.compose.animation.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.codesui.footballfixtures.R
import com.codesui.footballfixtures.resources.Topbar
import com.codesui.footballfixtures.screens.league.StandingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FixtureScreen (navController : NavController, fixtureId: String){
    val tabItems = listOf("Stats", "Table", "Lineup")
    val pagerState = rememberPagerState{
        tabItems.size
    }
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Topbar(title = "English Premier League") {
            
        }

        Card (
            modifier = Modifier
                .fillMaxWidth(.9f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(5))
                .clickable {
                },
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp),
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
        Card(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentHeight()
                .wrapContentHeight()
                .clickable {

                },
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Watch Live",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                )
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.live),
                    contentDescription = "league Image"
                )
            }
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = Color.Transparent,
            modifier = Modifier
                .padding(all = 3.dp)
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .clip(RoundedCornerShape(30.dp)),
            indicator = {
                tabPositions ->  TabRowDefaults.Indicator(
                         modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                         height = 0.dp,
                         color = MaterialTheme.colorScheme.primary
                )
            }

        ) {
            tabItems.forEachIndexed { index, tabItem ->
                val color = remember{
                    Animatable(Color.Gray)
                }

                LaunchedEffect(pagerState.currentPage == index){
                    color.animateTo(
                        if(pagerState.currentPage == index) Color.Gray else Color.White
                    )
                }
                Tab(
                    selected = pagerState.currentPage == index,
                    modifier = Modifier.background(
                        color = color.value,
                        shape = RoundedCornerShape(30.dp)
                    ),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = tabItem,
                            style = if(pagerState.currentPage == index) TextStyle(
                                color = Color.White,
                                fontSize = 16.sp
                            ) else TextStyle(
                                color = Color.Black,
                                fontSize =  14.sp
                            )
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = Color.Black
                )

            }
        }
        HorizontalPager(
            state = pagerState,
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {index ->
            when(index) {
                0 -> StatScreen()
                1 -> StandingsScreen(navController, "39")
                2 -> LineupScreen(navController, fixtureId)
            }
        }
    }
}