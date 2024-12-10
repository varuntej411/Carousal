package com.corpus.carousal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.corpus.carousal.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, innerPaddingValues: PaddingValues) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel()
    val data = viewModel.data

    Column(modifier = Modifier
        .padding(innerPaddingValues)
        .fillMaxSize()) {
        Text(text = "Data: ${data.value}")
        Button(onClick = {
            viewModel.loadUsersFromAssets(context) // Trigger data loading
        }) {
            Text("Fetch Data")
        }
    }
}