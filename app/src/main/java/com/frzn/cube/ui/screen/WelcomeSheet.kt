@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package com.frzn.cube.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.frzn.cube.ui.components.PressMorphButton

/**
 * 首次进入应用的底部面板内容。
 * 616×524dp，背景 surfaceContainerHighest。
 */
@Composable
fun WelcomeSheetContent(
    onDismiss: () -> Unit,
) {
    Column(
        modifier = Modifier
            .width(616.dp)
            .height(524.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 标题
        Text(
            text = "CubeLauncher",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.height(12.dp))

        // 中文描述
        Text(
            text = "这是一个专为Android™平台而生的《Minecraft Java Edition》启动器，希望您能喜欢！",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Spacer(Modifier.height(8.dp))

        // 英文描述
        Text(
            text = "This is a Minecraft Java Edition launcher for the Android™ platform. Hope you like it!",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Spacer(Modifier.height(24.dp))

        // 分割线
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp,
        )
        Spacer(Modifier.height(16.dp))

        // 作者卡片（浮起）
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "作者 Author · N-155 Studio",
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
                Text(
                    text = "GitHub",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.width(16.dp))
                PressMorphButton(
                    onClick = { /* 打开 GitHub */ },
                    idleCorner = 16.dp,
                    pressCorner = 6.dp,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "GitHub",
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        // 底部大按钮
        PressMorphButton(
            onClick = onDismiss,
            modifier = Modifier
                .fillMaxWidth()
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
