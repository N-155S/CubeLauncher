@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * CubeLauncher 全局主题入口。
 *
 * 颜色优先级：
 * 1. Android 12+ 且 [dynamicColor] 为 true → 系统动态取色（壁纸）
 * 2. 否则 → Blue 备用色板（[Color.kt](Color.kt)）
 */
@Composable
fun CubeLauncherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> {
            darkColorScheme(
                primary = BluePrimaryDark,
                onPrimary = BlueOnPrimaryDark,
                primaryContainer = BluePrimaryContainerDark,
                onPrimaryContainer = BlueOnPrimaryContainerDark,
                secondary = BlueSecondaryDark,
                onSecondary = BlueOnSecondaryDark,
                secondaryContainer = BlueSecondaryContainerDark,
                onSecondaryContainer = BlueOnSecondaryContainerDark,
                tertiaryContainer = BlueTertiaryContainerDark,
                onTertiaryContainer = BlueOnTertiaryContainerDark,
                surface = BlueSurfaceDark,
                onSurface = BlueOnSurfaceDark,
                surfaceVariant = BlueSurfaceVariantDark,
                onSurfaceVariant = BlueOnSurfaceVariantDark,
                outline = BlueOutlineDark,
                outlineVariant = BlueOutlineVariantDark,
                inverseSurface = BlueInverseSurfaceDark,
                inverseOnSurface = BlueInverseOnSurfaceDark,
                inversePrimary = BlueInversePrimaryDark,
                error = BlueErrorDark,
                onError = BlueOnErrorDark,
                errorContainer = BlueErrorContainerDark,
                onErrorContainer = BlueOnErrorContainerDark,
            )
        }
        else -> {
            lightColorScheme(
                primary = BluePrimary,
                onPrimary = BlueOnPrimary,
                primaryContainer = BluePrimaryContainer,
                onPrimaryContainer = BlueOnPrimaryContainer,
                secondary = BlueSecondary,
                onSecondary = BlueOnSecondary,
                secondaryContainer = BlueSecondaryContainer,
                onSecondaryContainer = BlueOnSecondaryContainer,
                tertiaryContainer = BlueTertiaryContainer,
                onTertiaryContainer = BlueOnTertiaryContainer,
                surface = BlueSurface,
                onSurface = BlueOnSurface,
                surfaceVariant = BlueSurfaceVariant,
                onSurfaceVariant = BlueOnSurfaceVariant,
                outline = BlueOutline,
                outlineVariant = BlueOutlineVariant,
                inverseSurface = BlueInverseSurface,
                inverseOnSurface = BlueInverseOnSurface,
                inversePrimary = BlueInversePrimary,
                error = BlueError,
                onError = BlueOnError,
                errorContainer = BlueErrorContainer,
                onErrorContainer = BlueOnErrorContainer,
            )
        }
    }

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        motionScheme = expressiveMotion(),
    ) {
        content()
    }
}
