package com.corpus.carousal.navgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.corpus.carousal.presentation.uiscreens.LoginScreen
import com.corpus.carousal.presentation.uiscreens.OTPVerificationScreen
import com.corpus.carousal.presentation.uiscreens.SplashScreen

fun NavGraphBuilder.authenticationNavGraph(navController: NavHostController) {
    navigation(
        route = RootNavGraph.AUTH_GRAPH,
        startDestination = Screens.SplashScreen.route
    ) {

        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screens.OTPScreen.route) {
            OTPVerificationScreen(
                navController = navController
            )
        }
    }
}