package com.corpus.carousal.presentation.uiscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.corpus.carousal.data.viewmodel.HomeViewModel
import com.corpus.carousal.domain.model.Content
import com.corpus.carousal.utils.RectangularImage
import com.corpus.carousal.utils.UiText

@Composable
fun HomeScreen(
    navController: NavHostController,
    innerPaddingValues: PaddingValues
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
                Text(text = uiState.error.getString())
            }
        }

        uiState.data?.content?.let { content ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(content.size) { item ->
                    Text(
                        text = content[item].title, modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            fontStyle = FontStyle.Normal,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    content[item].contentItem.forEach {
                        it.title?.let {
                            HorizontalRow(content[item].contentItem)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HorizontalRow(content: List<Content>) {

    val pageState = rememberPagerState(
        pageCount = { content.size }
    )
    HorizontalPager(
        pageSize = PageSize.Fill,
        pageSpacing = 1.dp,
        state = pageState,
        modifier = Modifier
            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
    ) { index ->
        RectangularImage(content[index].mobileCarouselImage.toString())
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
