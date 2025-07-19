package com.example.neumorphic_ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicatorDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ContainedLoadingIndicator(){
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessVeryLow,
            visibilityThreshold = 1/1000f
        )
    )
    Column (
        modifier = Modifier.fillMaxSize().background(Color(0xFFDCDCDC)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ContainedLoadingIndicator(
            modifier = Modifier.neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 12.dp,
                shape = Pressed(RoundedCorner(500.dp)),
                lightSource = LightSource.RIGHT_BOTTOM
            ),
            indicatorColor = Color.Red,
            containerColor = Color(0xFFDCDCDC),
            containerShape = CircleShape,
            polygons = LoadingIndicatorDefaults.IndeterminateIndicatorPolygons
        )
        CircularWavyProgressIndicator(
            color = Color.Yellow,
            modifier = Modifier.neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 12.dp,
                shape = Pressed(RoundedCorner(500.dp)),
                lightSource = LightSource.RIGHT_BOTTOM
            ),
            trackColor = Color.Blue,
            stroke = WavyProgressIndicatorDefaults.circularTrackStroke,
            trackStroke = WavyProgressIndicatorDefaults.circularIndicatorStroke
        )

    }
}