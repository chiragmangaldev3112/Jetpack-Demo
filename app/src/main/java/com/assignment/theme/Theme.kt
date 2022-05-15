package com.assignment.theme

import Dimensions
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import smallDimensions
import sw360Dimensions

// used to manage light theme
private val LightThemeColors = lightColors(
    primaryColor,
    primaryVariantColor,
    secondaryColor,
    secondaryVariantColor,
    background = primaryVariantColor


)

// used to manage dark theme for now its same as light theme
private val DarkThemeColors = darkColors(
    primaryColor,
    primaryVariantColor,
    secondaryColor,
    secondaryVariantColor,
    background = primaryVariantColor
)


private val LocalAppColors = staticCompositionLocalOf {
    LightThemeColors
}

private val LocalAppDimens = staticCompositionLocalOf {
    smallDimensions
}

private var LocalAppTypography =
    fun(dimensions: Dimensions, colors: Colors): ProvidableCompositionLocal<Typography> {

        return staticCompositionLocalOf {
            customTypography(dimensions, colors)
        }
    }

// help to provide typography
@Composable
fun ProvideTypography(
    colors: Colors,
    dimensions: Dimensions,
    typography: Typography,
    content: @Composable () -> Unit
) {
    val typographySet = remember { typography }
    CompositionLocalProvider(
        LocalAppTypography(dimensions, colors) provides typographySet,
        content = content
    )
}

// help to provide colors
@Composable
fun ProvideColors(
    colors: Colors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    CompositionLocalProvider(LocalAppColors provides colorPalette, content = content)
}

// help to provide dimens
@Composable
fun ProvideDimens(
    dimensions: Dimensions,
    content: @Composable () -> Unit
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

// help to provide app theme
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkThemeColors else LightThemeColors
    val configuration = LocalConfiguration.current
    val dimensions = if (configuration.screenWidthDp <= 360) smallDimensions else sw360Dimensions


    ProvideDimens(dimensions = dimensions) {
        ProvideColors(colors = colors) {
            ProvideTypography(
                dimensions = dimensions,
                typography = customTypography(dimensions, colors),
                colors = colors
            ) {
                MaterialTheme(
                    colors = colors,
                    shapes = Shapes,
                    typography = typography,
                    content = content,
                )
            }

        }
    }
}

// with the help of theme can get colors, dimensions and typography
object AppTheme {
    val colors: Colors
        @Composable
        get() = LocalAppColors.current

    val dimens: Dimensions
        @Composable
        get() = LocalAppDimens.current

    val typography: Typography
        @Composable
        get() = LocalAppTypography(dimens, colors).current


}

val Dimens: Dimensions
    @Composable
    get() = AppTheme.dimens