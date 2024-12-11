package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.data.viewmodel.AboutViewModel

@Composable
fun AboutScreen(navController: NavHostController, innerPaddingValues: PaddingValues,onLogoutClicked:() -> Unit) {

    val viewModel: AboutViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color.Red, shape = RoundedCornerShape(10.dp)),
            onClick = {
                viewModel.deleteUserLogin()
                onLogoutClicked.invoke()
            }
        ) {
            Text(
                text = "Logout",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun PreviewAboutScreen() {
    AboutScreen(navController = rememberNavController(), innerPaddingValues = PaddingValues(), onLogoutClicked = {})
}