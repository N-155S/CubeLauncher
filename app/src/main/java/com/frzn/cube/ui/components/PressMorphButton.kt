@file:OptIn(ExperimentalMaterial3Api::class)

package com.frzn.cube.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * M3E 风格按压圆角变换按钮。
 *
 * 按压时圆角从 [idleCorner] 弹簧动画到 [pressedCorner]，
 * 同时 scale 缩小到 [pressScale]；释放后弹簧回弹。
 * 弹簧参数（dampingRatio=0.7, stiffness=MediumLow）对齐
 * MotionScheme.expressive() 的弹簧物理。
 */
@Composable
fun PressMorphButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    idleCorner: Dp = 12.dp,
    pressedCorner: Dp = 24.dp,
    pressScale: Float = 0.95f,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val colors = ButtonDefaults.buttonColors()

    val corner = animateDpAsState(
        targetValue = if (isPressed) pressedCorner else idleCorner,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )

    val scale = animateFloatAsState(
        targetValue = if (isPressed) pressScale else 1f,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )

    Surface(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
            ),
        shape = RoundedCornerShape(corner.value),
        color = colors.containerColor,
        contentColor = colors.contentColor,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .clickable(
                    interactionSource = interactionSource,
                    enabled = enabled,
                    onClick = onClick,
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}

/**
 * M3E 风格 Tonal 圆角变换按钮。
 */
@Composable
fun PressMorphTonalButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    idleCorner: Dp = 12.dp,
    pressedCorner: Dp = 24.dp,
    pressScale: Float = 0.95f,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState().value

    val colors = ButtonDefaults.filledTonalButtonColors()

    val corner = animateDpAsState(
        targetValue = if (isPressed) pressedCorner else idleCorner,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )

    val scale = animateFloatAsState(
        targetValue = if (isPressed) pressScale else 1f,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = Spring.StiffnessMediumLow,
        ),
    )

    Surface(
        modifier = modifier
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
            ),
        shape = RoundedCornerShape(corner.value),
        color = colors.containerColor,
        contentColor = colors.contentColor,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .clickable(
                    interactionSource = interactionSource,
                    enabled = enabled,
                    onClick = onClick,
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()
        }
    }
}
