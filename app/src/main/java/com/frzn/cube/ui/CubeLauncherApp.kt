@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.frzn.cube.ui.screen.OnboardingScreen
import com.frzn.cube.ui.screen.WelcomeSheetContent
import com.frzn.cube.ui.theme.CubeLauncherTheme
import kotlinx.coroutines.launch

/**
 * CubeLauncher 根 Composable。
 * 横屏 1280×800dp 基准。
 */
@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        val context = LocalContext.current
        var showWelcomeSheet by remember { mutableStateOf(true) }
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()

        // 首次进入自动弹出
        LaunchedEffect(Unit) {
            sheetState.show()
        }

        // 面板完全隐藏后关闭
        LaunchedEffect(sheetState.currentValue) {
            if (showWelcomeSheet && sheetState.currentValue == SheetValue.Hidden) {
                showWelcomeSheet = false
            }
        }

        // 预测性返回
        BackHandler(enabled = showWelcomeSheet) {
            scope.launch { sheetState.hide() }
        }

        // ── 背景层：引导界面（面板展开时模糊）──
        Box(modifier = Modifier.fillMaxSize()) {
            OnboardingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(if (showWelcomeSheet) 6.dp else 0.dp)
                    .alpha(if (showWelcomeSheet) 0.55f else 1f),
            )
        }

        // ── 顶部应用栏（64dp，surface 背景）──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { /* 退出登录 */ },
                modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Logout",
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(Modifier.width(16.dp))
            Text(
                text = "CubeLauncher",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        // ── 底部面板 ──
        if (showWelcomeSheet) {
            ModalBottomSheet(
                onDismissRequest = { scope.launch { sheetState.hide() } },
                sheetState = sheetState,
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp,
                ),
                scrimColor = Color.Black.copy(alpha = 0.32f),
                modifier = Modifier.width(616.dp),
            ) {
                WelcomeSheetContent(
                    onGithubClick = {
                        context.startActivity(
                            Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/N-155S/CubeLauncher")),
                        )
                    },
                    onDismiss = { scope.launch { sheetState.hide() } },
                )
            }
        }
    }
}
