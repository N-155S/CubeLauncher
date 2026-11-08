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
 * 所有 Composable 内容必须包在 [CubeLauncherTheme] 里，
 * 形变 / 挤压 / 弹簧动画由 [MaterialExpressiveTheme] + [expressiveMotion] 统一驱动。
 *
 * 颜色优先级：
 * 1. Android 12+ 且 [dynamicColor] 为 true → 系统动态取色（壁纸）
 * 2. 否则 → 自定义明暗色板（[Color.kt](Color.kt)）
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
                primary = PrimaryDark,
                onPrimary = OnPrimaryDark,
                primaryContainer = PrimaryContainerDark,
                onPrimaryContainer = OnPrimaryContainerDark,
                secondary = SecondaryDark,
                onSecondary = OnSecondaryDark,
                secondaryContainer = SecondaryContainerDark,
                onSecondaryContainer = OnSecondaryContainerDark,
                tertiary = TertiaryDark,
                onTertiary = OnTertiaryDark,
                tertiaryContainer = TertiaryContainerDark,
                onTertiaryContainer = OnTertiaryContainerDark,
                error = ErrorDark,
                onError = OnErrorDark,
                errorContainer = ErrorContainerDark,
                onErrorContainer = OnErrorContainerDark,
            )
        }
        else -> {
            lightColorScheme(
                primary = Primary,
                onPrimary = OnPrimary,
                primaryContainer = PrimaryContainer,
                onPrimaryContainer = OnPrimaryContainer,
                secondary = Secondary,
                onSecondary = OnSecondary,
                secondaryContainer = SecondaryContainer,
                onSecondaryContainer = OnSecondaryContainer,
                tertiary = Tertiary,
                onTertiary = OnTertiary,
                tertiaryContainer = TertiaryContainer,
                onTertiaryContainer = OnTertiaryContainer,
                error = Error,
                onError = OnError,
                errorContainer = ErrorContainer,
                onErrorContainer = OnErrorContainer,
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
