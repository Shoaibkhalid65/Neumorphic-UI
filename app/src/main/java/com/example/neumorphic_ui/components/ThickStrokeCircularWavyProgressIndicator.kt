package com.example.neumorphic_ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ThickStrokeCircularWavyProgressIndicator(){
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = WavyProgressIndicatorDefaults.ProgressAnimationSpec
    )
    val strokeWidth = with(LocalDensity.current){8.dp.toPx() }
    val thickStroke = remember(strokeWidth) { Stroke(width = strokeWidth, cap = StrokeCap.Round) }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularWavyProgressIndicator(
            progress={
                animatedProgress
            },
            modifier = Modifier
                .size(52.dp)
                .neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 12.dp,
                shape = Pressed(RoundedCorner(500.dp)),
                lightSource = LightSource.LEFT_BOTTOM
                ),
            stroke = thickStroke,
            trackStroke = thickStroke
        )
        Spacer(modifier = Modifier.requiredHeight(30.dp))
        Text(
            text = "Set Progress"
        )
        Slider(
            modifier = Modifier
                .width(300.dp)
                .neu(
                    lightShadowColor = Color(0xFFFFFFFF),
                    darkShadowColor = Color(0xFFA8B5C7),
                    shadowElevation = 12.dp,
                    shape = Pressed(RoundedCorner(500.dp)),
                    lightSource = LightSource.LEFT_BOTTOM
                ),
            value = progress,
            onValueChange = {
                progress=it
            },
            valueRange = 0f .. 1f
        )

    }
}