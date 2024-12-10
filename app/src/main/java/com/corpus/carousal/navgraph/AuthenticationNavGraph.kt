package com.corpus.carousal.navgraph

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.corpus.carousal.LoginScreen
import com.corpus.carousal.OTPVerificationScreen
import com.corpus.carousal.viewmodel.OTPViewModel

fun NavGraphBuilder.authenticationNavGraph(navController: NavHostController) {
    navigation(
        route = RootNavGraph.AUTH_GRAPH,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                onLoginClicked = { mobileNumber ->
                    navController.navigate(Screens.OTPScreen.route)
                   /* navController.popBackStack()
                    navController.navigate(RootNavGraph.HOME_GRAPH)*/
                }
            )
        }

        composable(route = Screens.OTPScreen.route) {
            val viewModel: OTPViewModel = viewModel()
            OTPVerificationScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}