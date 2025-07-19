package com.example.neumorphic_ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.WavyProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CircularWavyProgressIndicatorSample(){
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = WavyProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
    CircularWavyProgressIndicator(
        progress={
            animatedProgress
        },
        modifier = Modifier.neu(
            lightShadowColor = Color(0xFFFFFFFF),
            darkShadowColor = Color(0xFFA8B5C7),
            shadowElevation = 12.dp,
            shape = Pressed(RoundedCorner(500.dp)),
            lightSource = LightSource.LEFT_BOTTOM
        ),
        color = Color.Green,
        trackColor = Color.Red,
        stroke = WavyProgressIndicatorDefaults.circularIndicatorStroke,
        trackStroke = WavyProgressIndicatorDefaults.circularIndicatorStroke,
        gapSize = WavyProgressIndicatorDefaults.CircularIndicatorTrackGapSize,
        amplitude = {2f},
        wavelength = WavyProgressIndicatorDefaults.LinearIndeterminateWavelength
    )
        Spacer(
            modifier = Modifier.requiredHeight(30.dp)
        )
        Slider(
            value = progress,
            onValueChange = {
                progress=it
            },
            valueRange = 0f..1f,

        )

    }
}