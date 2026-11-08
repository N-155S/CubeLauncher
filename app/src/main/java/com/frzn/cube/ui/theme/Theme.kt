@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

/**
 * CubeLauncher 全局主题入口。
 *
 * 所有 Composable 内容必须包在 [CubeLauncherTheme] 里，
 * 形变 / 挤压 / 弹簧动画由 [MaterialExpressiveTheme] + [expressiveMotion] 统一驱动。
 *
 * 颜色：标准 light / dark ColorScheme，M3E 自动接管形状与动效。
 */
@Composable
fun CubeLauncherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) {
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
    } else {
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

    MaterialExpressiveTheme(
        colorScheme = colorScheme,
        motionScheme = expressiveMotion(),
    ) {
        content()
    }
}
