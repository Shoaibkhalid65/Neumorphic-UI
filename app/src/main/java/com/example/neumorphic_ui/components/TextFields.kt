package com.example.neumorphic_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.NeuAttrs
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TextFieldSample1(){
    var isLightTheme by remember { mutableStateOf(true) }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = getBackgroundColor(isLightTheme)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var password by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }

        val interactionSource1 = remember { MutableInteractionSource() }
        val interactionSource2 = remember { MutableInteractionSource() }

        val lightSource1 = rememberLightSource(interactionSource1)
        val lightSource2 = rememberLightSource(interactionSource2)

        val neuAttrs1= getNeuAttrs(lightSource = lightSource1.value,isLightTheme)
        val neuAttrs2= getNeuAttrs(lightSource = lightSource2.value,isLightTheme)

        Switch(
            checked = isLightTheme,
            onCheckedChange = {
                isLightTheme=it
            },
            thumbContent = {
                    Icon(
                        imageVector = if(isLightTheme)Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Red,
                checkedTrackColor = getBackgroundColor(isLightTheme),
                checkedBorderColor = getBackgroundColor(isLightTheme),
                checkedIconColor = Color.Blue,
                uncheckedThumbColor = Color.Yellow,
                uncheckedTrackColor = getBackgroundColor(isLightTheme),
                uncheckedBorderColor = getBackgroundColor(isLightTheme),
                uncheckedIconColor = Color.Cyan
            ),
            modifier = Modifier.neu(
                neuAttrs = neuAttrs1
            ).height(30.dp)
        )

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            modifier = Modifier.padding(top = 20.dp).neu(
                neuAttrs=neuAttrs1
            ),
            placeholder = {
                Text(
                    text = "Enter Username"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            colors = textFiledColors(isLightTheme),
            shape = RoundedCornerShape(16.dp),
            interactionSource=interactionSource1
        )
        TextField(
            value = password,
            onValueChange = {
                password=it
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .neu(neuAttrs = neuAttrs2),
            placeholder = {
                Text(
                    text = "Enter Password"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            colors = textFiledColors(isLightTheme),
            shape=RoundedCornerShape(16.dp),
            visualTransformation = PasswordVisualTransformation(),
            interactionSource=interactionSource2
        )
    }
}

fun getNeuAttrs(lightSource: LightSource,isLightTheme: Boolean=true): NeuAttrs{
    return if(isLightTheme){
        NeuAttrs(
            lightShadowColor = Color(0xFFFFFFFF),
            darkShadowColor = Color(0xFFA8B5C7),
            shadowElevation = 12.dp,
            shape = if(lightSource== LightSource.LEFT_BOTTOM)Pressed(RoundedCorner(12.dp))else Flat(RoundedCorner(12.dp)),
            lightSource = lightSource
        )
    }else{
        NeuAttrs(
            lightShadowColor =Color(0x66494949),
            darkShadowColor = Color(0x66000000),
            shadowElevation = 12.dp,
            shape = if(lightSource== LightSource.LEFT_BOTTOM)Pressed(RoundedCorner(12.dp))else Flat(RoundedCorner(12.dp)),
            lightSource = lightSource
        )
    }
}

fun getBackgroundColor(isLightTheme: Boolean=true): Color{
    return if(isLightTheme) Color(0xFFDCDCDC)
    else Color(0xFF303234)
}

@Composable
fun rememberLightSource(
    interactionSource: InteractionSource
): State<LightSource> {
    val lightSource = remember { mutableStateOf(LightSource.LEFT_BOTTOM) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collectLatest { interaction ->
            lightSource.value = when (interaction) {
                is FocusInteraction.Focus -> LightSource.LEFT_TOP
                is FocusInteraction.Unfocus -> LightSource.LEFT_BOTTOM
                else -> lightSource.value
            }
        }
    }
    return lightSource
}

@Composable
fun textFiledColors(isLightTheme: Boolean): TextFieldColors{
    val background =getBackgroundColor(isLightTheme)
    val leadingIconColor=leadingIconColor(isLightTheme)
    val textAndLabelColor=textAndLabelColor(isLightTheme)
    return TextFieldDefaults.colors(
        focusedContainerColor = background,
        unfocusedContainerColor = background,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = leadingIconColor,
        unfocusedLeadingIconColor = leadingIconColor,
        unfocusedTextColor = textAndLabelColor,
        focusedTextColor = textAndLabelColor,
        focusedPlaceholderColor = textAndLabelColor,
        unfocusedPlaceholderColor = textAndLabelColor
    )
}

fun leadingIconColor(isLightTheme: Boolean=true): Color{
    return if(isLightTheme){
        Color.Blue
    }else{
        Color.Cyan
    }
}

fun textAndLabelColor(isLightTheme: Boolean=true): Color{
    return if(isLightTheme){
        Color.Black
    }else{
        Color.White
    }
}



@Preview(showBackground = true)
@Composable
fun TextFieldSamplePreview(){
   TextFieldSample1()
}






