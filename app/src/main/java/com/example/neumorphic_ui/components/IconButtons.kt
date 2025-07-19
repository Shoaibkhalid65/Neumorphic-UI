package com.example.neumorphic_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.neumorphic_ui.ui.theme.AppTheme
import com.example.neumorphic_ui.ui.theme.NeumorphicUITheme
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner

@Composable
fun IconButtonSample1(){
    NeumorphicUITheme (darkTheme = true){
        Box(
            modifier = Modifier.fillMaxSize().background(AppTheme.customColors.background),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.neu(
                    lightShadowColor = AppTheme.customColors.lightShadow,
                    darkShadowColor = AppTheme.customColors.darkShadow,
                    shape = Pressed(RoundedCorner(24.dp)),
                    lightSource = LightSource.LEFT_TOP,
                    shadowElevation = 12.dp
                ),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = AppTheme.customColors.background
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}