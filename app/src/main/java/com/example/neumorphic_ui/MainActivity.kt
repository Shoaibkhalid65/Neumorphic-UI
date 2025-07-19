package com.example.neumorphic_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.VerticalSlider
import androidx.compose.ui.Alignment
import com.example.neumorphic_ui.components.CircularProgressIndicatorSample1
import com.example.neumorphic_ui.components.CircularWavyProgressIndicatorSample
import com.example.neumorphic_ui.components.ContainedLoadingIndicator
import com.example.neumorphic_ui.components.IconButtonSample1
import com.example.neumorphic_ui.components.LinearWavyProgressIndicatorSample1
import com.example.neumorphic_ui.components.LottieAnimationExample
import com.example.neumorphic_ui.components.LottieSample
import com.example.neumorphic_ui.components.RefreshLoadingIndicatorSample
import com.example.neumorphic_ui.components.TextFieldSample1
import com.example.neumorphic_ui.components.ThickStrokeCircularWavyProgressIndicator
import com.example.neumorphic_ui.components.VerticalSliderSample

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottieAnimationExample()
        }
    }
}


