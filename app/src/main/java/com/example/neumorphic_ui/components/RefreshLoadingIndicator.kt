package com.example.neumorphic_ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun RefreshLoadingIndicatorSample(){
    var numberOfItems by remember { mutableIntStateOf(15) }
    var isRefreshing by remember { mutableStateOf(false) }

    val loadingState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()

    val onRefresh:()-> Unit={
        isRefreshing=true
        coroutineScope.launch(Dispatchers.IO) {
            delay(5000)
            numberOfItems+=10
            isRefreshing=false
        }
    }
    val scaleFraction ={
        if(isRefreshing) 1f
        else LinearOutSlowInEasing.transform(loadingState.distanceFraction).coerceIn(0f,1f)
    }
    Scaffold(
        modifier = Modifier
            .pullToRefresh(
                isRefreshing=isRefreshing,
                state = loadingState,
                onRefresh=onRefresh,
            ),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Top bar title"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {onRefresh()}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "refresh icon"
                        )
                    }
                }
            )
        }
    ) { innerPadding->
        Box (modifier = Modifier.padding(innerPadding)){
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(numberOfItems) { index ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = "Item no $index"
                            )
                        }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(
                    alignment = Alignment.TopCenter
                    )
                    .graphicsLayer(
                        scaleX = scaleFraction(),
                        scaleY = scaleFraction()
                    )
            ){
                PullToRefreshDefaults.LoadingIndicator(
                    state = loadingState,
                    isRefreshing=isRefreshing
                )
            }
        }
    }
}