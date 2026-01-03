package com.example.composedemo.core

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composedemo.core.ui.theme.ComposeDemoTheme
import com.example.composedemo.details.presentation.view.DetailsScreenCore
import com.example.composedemo.home.domain.model.Character
import com.example.composedemo.home.presentation.view.HomeScreenCore
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URLDecoder

@Serializable
object HomeScreen

@Serializable
data class DetailsScreen(val character: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var topBarTitle by remember { mutableStateOf("Home") }
            var isHome by remember { mutableStateOf(true) }
            ComposeDemoTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(), topBar = {
                        TopAppBarState(navController,topBarTitle,isHome)
                    }) { innerPadding ->
                    NavHost(
                        navController, startDestination = HomeScreen, modifier = Modifier.padding(innerPadding),
                        builder = {
                            composable<HomeScreen> {
                                topBarTitle="Home"
                                isHome=true
                                HomeScreenCore(navController)
                            }
                            composable<DetailsScreen> {
                                topBarTitle="Details"
                                isHome=false
                                val args = it.toRoute<DetailsScreen>()
                                val character = Json.decodeFromString<Character>(
                                    URLDecoder.decode(args.character, "UTF-8")
                                )
                                DetailsScreenCore(character)
                            }
                        },
                    )
                }
            }
        }
    }

    @Composable
    fun TopAppBarState(navHostController: NavHostController, topBarTitle: String, isHome: Boolean) {
        Row(
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .fillMaxWidth()
        ) {
            AnimatedVisibility(!isHome) {
                Image(Icons.Filled.ArrowBack, null,
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .clickable {
                            navHostController.popBackStack()
                        })
            }
            AnimatedVisibility(isHome) {
                Spacer(modifier = Modifier.width(10.dp))
            }

            Text(topBarTitle, fontSize = 20.sp)

        }
    }
}
