@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.frzn.cube.ui.screen.WelcomeSheetContent
import com.frzn.cube.ui.theme.CubeLauncherTheme

/**
 * CubeLauncher 根 Composable。
 *
 * 首次进入：引导界面（背景，模糊）+ 弹出的 WelcomeSheet（底部面板）。
 */
@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        var showWelcomeSheet by remember { mutableStateOf(true) }
        val sheetState = rememberModalBottomSheetState()
        val coroutineScope = rememberCoroutineScope()

        // 首次进入自动弹出底部面板
        LaunchedEffect(Unit) {
            sheetState.show()
        }

        // 当 sheet 完全隐藏时关闭
        LaunchedEffect(sheetState.currentValue) {
            if (sheetState.currentValue == SheetValue.Hidden) {
                showWelcomeSheet = false
            }
        }

        // 预测性返回：面板展开时，返回键先收起面板
        BackHandler(enabled = showWelcomeSheet) {
            coroutineScope.launch { sheetState.hide() }
        }

        Scaffold(
            topBar = {
                if (!showWelcomeSheet) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(MaterialTheme.colorScheme.surface),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        IconButton(onClick = { /* logout */ }) {
                            Icon(
                                Icons.Filled.Logout,
                                contentDescription = "Logout",
                            )
                        }
                        Text(
                            text = "CubeLauncher",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Spacer(Modifier.width(48.dp))
                    }
                }
            },
            containerColor = Color.Transparent,
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                // 背景：引导界面（sheet 展开时模糊 + 降透明度）
                val isSheetOpen = showWelcomeSheet
                OnboardingBackground(
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(if (isSheetOpen) 8.dp else 0.dp)
                        .alpha(if (isSheetOpen) 0.6f else 1f),
                )

                // 底部面板
                if (showWelcomeSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            coroutineScope.launch { sheetState.hide() }
                        },
                        sheetState = sheetState,
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                        shape = RoundedCornerShape(
                            topStart = 28.dp,
                            topEnd = 28.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp,
                        ),
                    ) {
                        WelcomeSheetContent(
                            onDismiss = {
                                coroutineScope.launch { sheetState.hide() }
                            },
                        )
                    }
                }
            }
        }
    }
}

/**
 * 引导界面占位（背景层）。
 */
@Composable
private fun OnboardingBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "CubeLauncher",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Minecraft Java Edition Launcher for Android™",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
