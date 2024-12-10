package com.corpus.carousal.presentation.uiscreens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.navgraph.BottomNavigationBar
import com.corpus.carousal.navgraph.HomeNavGraph
import com.corpus.carousal.navgraph.Screens
import com.corpus.carousal.navgraph.bottomBarItems
import com.corpus.carousal.presentation.components.CustomToolbar
import okhttp3.Route

@Composable
fun MainHomeNavigationScreen(
    navController: NavHostController = rememberNavController()
) {

    val navBottomItems = bottomBarItems
    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    var darkTheme by rememberSaveable {
        mutableStateOf(false)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Screens.HomeScreen.route -> 0
        Screens.AboutScreen.route -> 1
        else -> 0
    }

    //  val hideBottomBar = navBottomItems.any { it.title == backStackState?.destination?.route }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screens.HomeScreen.route
                || backStackState?.destination?.route == Screens.AboutScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CustomToolbar()
        },
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(
                    navItems = navBottomItems,
                    selectedItem = selectedItem,
                    onClickItem = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Screens.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Screens.AboutScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) { innerPaddingValues ->
        HomeNavGraph(navController = navController, innerPaddingValues = innerPaddingValues)
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavHostController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Screens.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavHostController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            // popUpTo(it)
            navController.popBackStack(
                route = Screens.MainHomeNavigationScreen.route,
                inclusive = true
            )
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavHostController) {
    //   navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Screens.LoginScreen.route
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainHomeNavigationScreen()
}