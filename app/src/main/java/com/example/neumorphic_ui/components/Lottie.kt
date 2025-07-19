package com.example.neumorphic_ui.components

import android.util.Log
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.neumorphic_ui.R
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

@Composable
fun LottieSample(){
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFDCDCDC)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.clock_1))
        val progress by animateLottieCompositionAsState(
            composition=composition,
            isPlaying = true,
            speed = 2f

        )
        LaunchedEffect(composition) {
            composition?.layers?.forEach{layer ->
                Log.d("tag","$layer")
            }
        }
        val dynamicProperties= rememberLottieDynamicProperties (
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    android.graphics.Color.YELLOW,
                    BlendModeCompat.SRC_OVER,
                ),
                keyPath = arrayOf(
                    "Shape Layer 2",
                    "**"
                )
            ),
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    android.graphics.Color.BLUE,
                    BlendModeCompat.SRC_OVER,
                ),
                keyPath = arrayOf(
                    "Shape Layer 3",
                    "**"
                )
            ),
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    android.graphics.Color.GREEN,
                    BlendModeCompat.SRC_OVER,
                ),
                keyPath = arrayOf(
                    "Shape Layer 1",
                    "**"
                )
            ),
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    0xFFFFFFFF.toInt(),
                    BlendModeCompat.SRC_OVER,
                ),
                keyPath = arrayOf(
                    "Shape Layer 4",
                    "**"
                )
            ),
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR_FILTER,
                value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    android.graphics.Color.MAGENTA,
                    BlendModeCompat.SRC_OVER,
                ),
                keyPath = arrayOf(
                    "round",
                    "**"
                )
            ),



        )
        LottieAnimation(
            composition=composition,
            progress={progress},
//            dynamicProperties=dynamicProperties,
            modifier = Modifier
                .background(
                    Color(0xFFDCDCDC)
                )
                .size(300.dp)
                .neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 12.dp,
                shape = Pressed(RoundedCorner(500.dp)),
                lightSource = LightSource.LEFT_BOTTOM
            )
        )

    }
}

@Composable
@Preview(showBackground = true)
fun LottieSamplePreview(){
    LottieSample()
}