package com.corpus.carousal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar() {
    // val clickedNavDrawer = remember { mutableStateOf(false) }
   // val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onBackground)
          //  .shadow(elevation = 5.dp, shape = RectangleShape)
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black,
                scrolledContainerColor = Color.Black
            ),
            title = {
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Notifications",
                            Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            tint = Color.White
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(2.dp),
                        tint = Color.White
                    )
                }
            }
        )
    }

}


@Preview
@Composable
fun CustomToolBarPreview() {
    CustomToolbar()
}