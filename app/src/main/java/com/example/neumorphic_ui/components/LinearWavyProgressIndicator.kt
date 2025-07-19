package com.example.neumorphic_ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearWavyProgressIndicator
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
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LinearWavyProgressIndicatorSample1(){
    var progress by remember { mutableFloatStateOf(0.1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = WavyProgressIndicatorDefaults.ProgressAnimationSpec
    )
    val thickStrokeWidth = with(LocalDensity.current){8.dp.toPx()}
    val thickStroke =remember(thickStrokeWidth) { Stroke(width = thickStrokeWidth, cap = StrokeCap.Round) }
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFDCDCDC)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearWavyProgressIndicator(
            progress={
                animatedProgress
            },
            color = Color.Red,
            trackColor = Color.Magenta,
            modifier = Modifier.neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 2.dp,
                shape = Flat(RoundedCorner(500.dp)),
                lightSource = LightSource.RIGHT_BOTTOM
            ).background(Color(0xFFDCDCDC)),
            stroke = thickStroke,
            trackStroke = thickStroke
        )
        Spacer(
            modifier = Modifier.requiredHeight(30.dp)
        )
        Text(
            text = "Handle the Progress"
        )
        Slider(
            value = progress,
            onValueChange = {
                progress=it
            },
            modifier = Modifier
                .neu(
                    lightShadowColor = Color(0xFFFFFFFF),
                    darkShadowColor = Color(0xFFA8B5C7),
                    shadowElevation = 0.dp,
                    shape = Flat(RoundedCorner(500.dp)),
                    lightSource = LightSource.RIGHT_BOTTOM
                )
                .requiredWidth(300.dp)


        )
    }
}