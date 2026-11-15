@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.frzn.cube.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frzn.cube.ui.components.PressMorphButton

/**
 * 首次进入应用的底部面板内容。
 * 616×524dp，背景 surfaceContainerHighest，上方圆角 28dp，下方直角。
 */
@Composable
fun WelcomeSheetContent(
    onGithubClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // ── 上部靠左：粗体 "CubeLauncher"（45sp）──
        Text(
            text = "CubeLauncher",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.Start),
        )
        Spacer(Modifier.height(12.dp))

        // ── 上部：中文描述（28sp）──
        Text(
            text = "这是一个专为Android™平台而生的《Minecraft Java Edition》启动器，希望您能喜欢！",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        // ── 中部：英文描述（28sp）──
        Text(
            text = "This is a Minecraft Java Edition launcher for the Android™ platform. Hope you like it!",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(20.dp))

        // ── 分割线（1dp outlineVariant，左右 16dp）──
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))

        // ── 中部横向一行：浮起卡片（高 112dp，圆角 14dp）──
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 左侧：标题 + 正文
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "作者 Author  N-155 Studio",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "bilibili:@Freeazen155",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(Modifier.width(16.dp))

                // 中间：GitHub 文本（45sp）
                Text(
                    text = "GitHub",
                    fontSize = 45.sp,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.width(12.dp))

                // 右侧：无标签填充按钮（arrow_forward，宽 108dp）
                PressMorphButton(
                    onClick = onGithubClick,
                    modifier = Modifier.width(108.dp),
                    idleCorner = 28.dp,
                    pressCorner = 10.dp,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "GitHub",
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // ── 下部：无标签填充按钮（arrow_forward，宽 592dp，高 100dp）──
        PressMorphButton(
            onClick = onDismiss,
            modifier = Modifier
                .width(592.dp)
                .height(100.dp),
            idleCorner = 50.dp,
            pressCorner = 20.dp,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "开始",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}
