@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.frzn.cube.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
 * 616dp 宽，背景 surfaceContainerHighest，上方圆角 28dp。
 */
@Composable
fun WelcomeSheetContent(
    onGithubClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()) // 修复问题 5：内容超出时可滚动
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        // 上部靠左：粗体 "CubeLauncher"（45sp）
        Text(
            text = "CubeLauncher",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(8.dp))

        // 上部：中文描述（28sp，居中，统一行高 40sp → 修复问题 1）
        Text(
            text = "这是一个专为Android™平台而生的《Minecraft Java Edition》启动器，希望您能喜欢！",
            fontSize = 28.sp,
            lineHeight = 40.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        // 中部：英文描述（28sp，居中，统一行高 40sp → 修复问题 1）
        Text(
            text = "This is a Minecraft Java Edition launcher for the Android™ platform. Hope you like it!",
            fontSize = 28.sp,
            lineHeight = 40.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(12.dp))

        // 分割线（1dp outlineVariant）
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp,
        )
        Spacer(Modifier.height(12.dp))

        // 中部横向一行：浮起卡片 + GitHub 文本 + 小按钮
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // 浮起卡片（高 112dp，圆角 14dp）
            ElevatedCard(
                modifier = Modifier
                    .height(112.dp)
                    .weight(1f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
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
                }
            }

            Spacer(Modifier.width(12.dp))

            // "GitHub" 文本（45sp）
            Text(
                text = "GitHub",
                fontSize = 45.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.width(12.dp))

            // 无标签填充按钮（arrow_forward，宽 108dp，高 56dp，胶囊形）
            PressMorphButton(
                onClick = onGithubClick,
                modifier = Modifier
                    .width(108.dp)
                    .height(56.dp),
                idleCorner = 28.dp,
                pressCorner = 10.dp,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "GitHub",
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // 下部：无标签填充大按钮（arrow_forward，高 100dp，胶囊形）→ 修复问题 5
        PressMorphButton(
            onClick = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            idleCorner = 50.dp,
            pressCorner = 20.dp,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}
