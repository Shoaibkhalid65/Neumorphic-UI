package com.example.neumorphic_ui.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

sealed class ThemeColors(
    val background:Color,
    val lightShadow:Color,
    val darkShadow: Color,
    val textColor: Color
){
    object Light: ThemeColors(
        background = Color(0xFFDCDCDC),
        lightShadow = Color(0xFFFFFFFF),
        darkShadow =  Color(0xFFA8B5C7),
        textColor = Color.Black
    )
    object Dark: ThemeColors(
        background = Color(0xFF303234),
        lightShadow = Color(0x66494949),
        darkShadow = Color(0x66000000),
        textColor = Color.White
    )
}
