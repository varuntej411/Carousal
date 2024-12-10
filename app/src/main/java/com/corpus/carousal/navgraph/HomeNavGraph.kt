package com.corpus.carousal.navgraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.corpus.carousal.HomeScreen

@Composable
fun HomeNavGraph(navController: NavHostController, innerPaddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = RootNavGraph.HOME_GRAPH,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(navController = navController, innerPaddingValues = innerPaddingValues)
        }

        composable(route = Screens.AboutScreen.route) {
            HomeScreen(navController = navController, innerPaddingValues = innerPaddingValues)
        }

    }
}