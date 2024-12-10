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
import androidx.navigation.compose.rememberNavController
import com.corpus.carousal.navgraph.SetUpRootNavGraph
import com.corpus.carousal.ui.theme.CarousalTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  enableEdgeToEdge()
        setContent {
            CarousalTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    window.statusBarColor = application.getColor(R.color.teal_700)
                    //  window.navigationBarColor = application.getColor(R.color.teal_700)
                    SetUpRootNavGraph(navController = navController)
                }
            }
        }
    }
}