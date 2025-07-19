package com.example.neumorphic_ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview(showBackground = true)
fun CircularProgressIndicatorSample1(){
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(
            progress = {
                animatedProgress
            },
            modifier = Modifier,
            color = Color.Magenta,
            strokeWidth = 4.dp,
            trackColor = Color.Blue,
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
            gapSize = 2.dp
        )
        Spacer(
            modifier = Modifier.requiredHeight(30.dp)
        )
        Text(
            text = "Set Progress"
        )
        Slider(
            modifier = Modifier.width(300.dp),
            value = progress,
            valueRange = 0f..1f,
            onValueChange = {
                progress=it
            },
            steps = 10,
            colors = SliderDefaults.colors(
                thumbColor = Color.Magenta,
                activeTickColor = Color.Cyan,
                inactiveTickColor = Color.Green,
                inactiveTrackColor = Color.Blue,
                activeTrackColor = Color.Yellow,
            )
        )
    }
}