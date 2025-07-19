package com.example.neumorphic_ui.components

import androidx.compose.animation.core.animate
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalSlider
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VerticalSliderSample(){
    val state =rememberSliderState(
        steps = 9,
        valueRange = 0f..100f
    )
    val coroutineScope = rememberCoroutineScope()
    val snapAnimationSpec = MaterialTheme.motionScheme.fastEffectsSpec<Float>()
    var currentValue by rememberSaveable { mutableFloatStateOf(state.value) }
    var animateJob : Job? by remember { mutableStateOf(null) }
    state.shouldAutoSnap=false
    state.onValueChange ={newValue->
        currentValue=newValue
        if(state.isDragging){
            animateJob?.cancel()
            state.value=newValue
        }
    }
    state.onValueChangeFinished={
        animateJob= coroutineScope.launch {
            animate(
                initialValue = state.value,
                targetValue = currentValue,
                animationSpec = snapAnimationSpec
            ){value, _ ->
                state.value=value
            }
        }
    }
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            text = "%.2f".format(state.value)
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        VerticalSlider(
            state=state,
            modifier = Modifier
                .height(300.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .progressSemantics(
                    value =  currentValue,
                    valueRange = state.valueRange.start..state.valueRange.endInclusive,
                    steps = state.steps
                ),
            interactionSource=interactionSource,
            track = { SliderDefaults.CenteredTrack(sliderState = state)},
            reverseDirection = true
        )
    }

}