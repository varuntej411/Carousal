package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AboutScreen(navController: NavHostController, innerPaddingValues: PaddingValues) {

}

@Preview
@Composable
fun PreviewAboutScreen() {
    AboutScreen(navController = rememberNavController(), innerPaddingValues = PaddingValues())
}