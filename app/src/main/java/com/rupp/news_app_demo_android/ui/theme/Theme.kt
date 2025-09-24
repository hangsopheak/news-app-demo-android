package com.rupp.news_app_demo_android.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    background = Background,
    onBackground = onBackground,
    surface = Surface,
    onSurface = OnSurface
)

@Composable
fun NewsappdemoandroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        Starting from Android 12, Google introduced Material, which allows personalized UI themes by extracting colors from the user’s wallpaper.
//        This affects:
//                System UI (notifications, quick settings, etc.)
//                Apps that support dynamic colors via dynamicDarkColorScheme and dynamicLightColorScheme.
//                Without this, your app would not align with the system-wide personalization, making it look inconsistent.
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context).copy(
                    background = Color.White,
                    surface = Color.White
                )
            }
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme


    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}