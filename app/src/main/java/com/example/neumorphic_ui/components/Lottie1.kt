package com.example.neumorphic_ui.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
@Preview(showBackground = true)
fun LottieAnimationExample() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_time))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever, speed = 0.2f)
    LaunchedEffect(composition) {
        composition?.layers?.forEach{layer ->
            Log.d("tag","$layer")
        }
    }
    val dynamicProperties= rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                android.graphics.Color.RED,
                BlendModeCompat.SRC_OVER
            ),
            keyPath = arrayOf(
                "Shape-Copy-5",
                "**"
            )
        )
    )


    LottieAnimation(
        composition = composition,
        progress = { progress },
        dynamicProperties=dynamicProperties,
        modifier = Modifier
            .background(
                Color(0xFFDCDCDC)
            )
            .neu(
                lightShadowColor = Color(0xFFFFFFFF),
                darkShadowColor = Color(0xFFA8B5C7),
                shadowElevation = 12.dp,
                shape = Pressed(RoundedCorner(500.dp)),
                lightSource = LightSource.LEFT_BOTTOM
            )
    )

}
