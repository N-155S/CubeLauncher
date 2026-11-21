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
import com.frzn.cube.ui.components.PressMorphTonalButton
import com.frzn.cube.ui.screen.OnboardingScreen
import com.frzn.cube.ui.screen.WelcomeSheetContent
import com.frzn.cube.ui.theme.CubeLauncherTheme
import kotlinx.coroutines.launch

@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        val context = LocalContext.current
        var showSheet by remember { mutableStateOf(true) }
        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) { sheetState.show() }

        LaunchedEffect(sheetState.currentValue) {
            if (showSheet && sheetState.currentValue == SheetValue.Hidden) {
                showSheet = false
            }
        }

        BackHandler(enabled = showSheet) {
            scope.launch { sheetState.hide() }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            // 背景：引导界面（用 surface，比应用栏暗）
            OnboardingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(if (showSheet) 8.dp else 0.dp)
                    .alpha(if (showSheet) 0.5f else 1f),
            )
        }

        // 顶部应用栏 64dp（背景 surfaceContainer，与背景 surface 区分 → 修复问题 2）
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .statusBarsPadding()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 退出按钮：PressMorphTonalButton（有背景 + 圆角变换 + 点击退出 → 修复问题 3）
            PressMorphTonalButton(
                onClick = {
                    (context as? android.app.Activity)?.finishAffinity()
                },
                modifier = Modifier
                    .width(56.dp)
                    .height(40.dp),
                idleCorner = 20.dp,
                pressCorner = 8.dp,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Logout",
                    modifier = Modifier.size(20.dp),
                )
            }

            Spacer(Modifier.width(12.dp))

            Text(
                text = "CubeLauncher",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        // 底部面板
        if (showSheet) {
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
