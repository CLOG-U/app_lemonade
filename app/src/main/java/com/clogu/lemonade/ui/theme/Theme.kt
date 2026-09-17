package com.clogu.lemonade.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = LeafGreen,
    secondary = FreshBlue,
    tertiary = LemonYellow,
    background = Cream,
    surface = Cream,
    onPrimary = Cream,
    onBackground = DarkGreen,
    onSurface = DarkGreen
)

private val DarkColors = darkColorScheme(
    primary = LemonYellow,
    secondary = FreshBlue,
    tertiary = LeafGreen,
    background = DarkGreen,
    surface = DarkGreen,
    onPrimary = DarkGreen,
    onBackground = Cream,
    onSurface = Cream
)

@Composable
fun LemonadeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current

    val activity = view.context as? Activity

    if (!view.isInEditMode && activity != null) {
        val window = activity.window
        window.statusBarColor = colors.background.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = LemonadeTypography,
        content = content
    )
}
