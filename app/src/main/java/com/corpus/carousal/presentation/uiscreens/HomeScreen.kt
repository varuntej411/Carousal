package com.corpus.carousal.presentation.uiscreens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.data.viewmodel.HomeViewModel
import com.corpus.carousal.utils.UiText

@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        contentColor = Color.White
    ) {
        val context:Context = LocalContext.current
        val uiState = viewModel.uiState.collectAsState().value

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (uiState.isLoading) {
                CustomProgressBar()
            }

            if (uiState.error !is UiText.Idle) {
                Box(
                    modifier = Modifier
                        .padding(innerPaddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error.getString(context = context),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPaddingValues)
                    )
                }
            }

            uiState.data?.let { list ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(list.content.size) {
                        Text(text = list.content.get(it).title,
                            color = Color.Black)
                    }
                }
            }
        }
    }
}


    @Composable
    fun CustomProgressBar() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Preview
    @Composable
    fun PreviewHomeScreen() {
        HomeScreen(navController = rememberNavController(), innerPaddingValues = PaddingValues())
    }
