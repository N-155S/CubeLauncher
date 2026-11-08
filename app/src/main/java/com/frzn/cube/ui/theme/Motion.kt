@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package com.frzn.cube.ui.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MotionScheme

/**
 * CubeLauncher 全局动效方案。
 *
 * 所有 M3E 原生组件（Button / FilledTonalButton / FloatingActionButton /
 * ButtonGroup / ConnectedButtonGroup / TextField 等）共享同一套弹簧物理参数，
 * 保证按压形变、挤压和释放节奏完全统一，不会出现不同组件「打架」的情况。
 */
fun expressiveMotion(): MotionScheme = MotionScheme.expressive()
