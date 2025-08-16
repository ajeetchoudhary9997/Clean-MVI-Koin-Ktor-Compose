package com.example.composedemo.home.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composedemo.core.DetailsScreen
import com.example.composedemo.home.domain.model.Character
import com.example.composedemo.home.presentation.actions.HomeScreenActions
import com.example.composedemo.home.presentation.state.HomeScreenState
import com.example.composedemo.home.presentation.viewModel.HomeViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder

/**
 * Created by  Ajeet Singh on Date: 09/08/25.
 */
@Composable
fun HomeScreenCore(navController: NavController, innerPadding: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        HomeScreen(navController, viewModel.homeState, viewModel::handleActions)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, homeState: HomeScreenState, actions: (HomeScreenActions) -> Unit) {
    val hasLoaded = rememberSaveable { mutableStateOf(false) }
    //Currently calling multiple time
    LaunchedEffect(navController) {
        if (!hasLoaded.value) {
            actions(HomeScreenActions.ActionSearch())
            hasLoaded.value = true
        }
    }
    if (homeState.isLoading) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text("Loading")
            Spacer(modifier = Modifier.padding(10.dp))
            CircularProgressIndicator()
        }

    } else if (homeState.isError) {
            Column(modifier = Modifier.fillMaxSize().padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                Text(homeState.error, textAlign = TextAlign.Center)
                Spacer(Modifier.height(10.dp))
                ElevatedButton(onClick = {
                    actions(HomeScreenActions.ActionSearch())
                }) {
                    Text("Try Again")
                }
            }

    } else
        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(homeState.characters, { it.id }) {
                CharacterListItem(it, navController)
            }
        }
}

@Composable
fun CharacterListItem(listItem: Character, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        onClick = {
            val json = URLEncoder.encode(Json.encodeToString(listItem), "UTF-8")
            navController.navigate(DetailsScreen(json))
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(10.dp, 5.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(0.dp, 10.dp, 10.dp, 10.dp)) {
                AsyncImage(
                    ImageRequest.Builder(LocalContext.current)
                        .data(listItem.image)
                        .size(100)
                        .crossfade(true)
                        .build(),
                    null,
                    modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, shape = CircleShape)
                )
            }
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.weight(1f)) {
                Text(listItem.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text(listItem.species, fontSize = 14.sp, color = Color.DarkGray)
                    Spacer(modifier.width(5.dp))
                    Text(listItem.status, fontSize = 14.sp, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Text("Last Know Location: ", fontSize = 14.sp, color = Color.DarkGray)
                    Text(listItem.location, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 14.sp, color = Color.DarkGray)

                }
            }

        }
    }

}