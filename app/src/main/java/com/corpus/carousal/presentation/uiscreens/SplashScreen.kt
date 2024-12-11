package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.data.viewmodel.SplashScreenViewModel
import com.corpus.carousal.navgraph.RootNavGraph
import com.corpus.carousal.navgraph.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val viewModel: SplashScreenViewModel = hiltViewModel()
    val loggedIn = viewModel.userLoggedIn.collectAsState().value

    LaunchedEffect(key1 = loggedIn) {
        delay(2000L)
        if (loggedIn) {
            navController.navigate(RootNavGraph.HOME_GRAPH)
        } else {
            navController.navigate(Screens.LoginScreen.route)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to MyApp!",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        )
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    SplashScreen(navController = rememberNavController())
}