@file:OptIn(ExperimentalMaterial3Api::class)

package com.frzn.cube.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * M3E 圆角变换按钮。
 *
 * idle 24dp → 按下凸到 4dp → 回弹 7dp → 松开过冲 26dp → 恢复 24dp。
 * 无 scale 下沉，ripple 在 Surface 容器上。
 */
@Composable
fun PressMorphButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    idleCorner: Dp = 24.dp,
    pressCorner: Dp = 7.dp,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val cornerAnimatable = remember { Animatable(24f) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            // 按下：强过冲 24dp → 4dp → 稳定 7dp
            cornerAnimatable.animateTo(
                targetValue = 7f,
                animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
            )
        } else {
            // 松开：温和过冲 7dp → 26dp → 稳定 24dp
            cornerAnimatable.animateTo(
                targetValue = 24f,
                animationSpec = spring(dampingRatio = 0.42f, stiffness = Spring.StiffnessMedium),
            )
        }
    }

    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(cornerAnimatable.value.dp),
        color = colors.containerColor,
        contentColor = colors.contentColor,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

@Composable
fun PressMorphTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    idleCorner: Dp = 24.dp,
    pressCorner: Dp = 7.dp,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val cornerAnimatable = remember { Animatable(24f) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            cornerAnimatable.animateTo(
                targetValue = 7f,
                animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
            )
        } else {
            cornerAnimatable.animateTo(
                targetValue = 24f,
                animationSpec = spring(dampingRatio = 0.42f, stiffness = Spring.StiffnessMedium),
            )
        }
    }

    Surface(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(cornerAnimatable.value.dp),
        color = colors.containerColor,
        contentColor = colors.contentColor,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

@Composable
fun PressMorphFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val cornerAnimatable = remember { Animatable(16f) }

    LaunchedEffect(isPressed) {
        if (isPressed) {
            cornerAnimatable.animateTo(
                targetValue = 4f,
                animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium),
            )
        } else {
            cornerAnimatable.animateTo(
                targetValue = 16f,
                animationSpec = spring(dampingRatio = 0.42f, stiffness = Spring.StiffnessMedium),
            )
        }
    }

    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(cornerAnimatable.value.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        interactionSource = interactionSource,
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
