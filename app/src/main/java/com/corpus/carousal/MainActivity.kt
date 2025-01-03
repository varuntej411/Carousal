package com.corpus.carousal

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.navgraph.SetUpRootNavGraph
import com.corpus.carousal.ui.theme.CarousalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CarousalTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentColor = Color.Black,
                    color = MaterialTheme.colorScheme.onSurface
                ) {
                    window.statusBarColor = application.getColor(R.color.black)
                    //  window.navigationBarColor = application.getColor(R.color.teal_700)
                    SetUpRootNavGraph(navController = navController)
                }
            }
        }
    }
}