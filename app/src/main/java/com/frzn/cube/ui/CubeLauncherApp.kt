@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frzn.cube.ui.theme.CubeLauncherTheme

/**
 * CubeLauncher 根 Composable。
 *
 * 当前阶段：M3E 组件可用性验证。
 * 所有按钮均使用 M3E 原生组件，由 MotionScheme.expressive() 驱动形变与挤压。
 */
@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* no-op */ },
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "CubeLauncher",
                    style = MaterialTheme.typography.displaySmall,
                )
                Spacer(Modifier.size(8.dp))
                Text(
                    text = "beta.0.0.1",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.size(32.dp))

                // 1. 标准按钮
                Button(onClick = { /* no-op */ }) {
                    Text("Standard Button")
                }
                Spacer(Modifier.size(16.dp))

                // 2. Tonal 按钮（M3E 组件）
                FilledTonalButton(onClick = { /* no-op */ }) {
                    Text("Filled Tonal Button")
                }
                Spacer(Modifier.size(16.dp))

                // 3. 组按钮（M3E 组件，slot 形式）
                ButtonGroup {
                    Button(onClick = { /* no-op */ }) { Text("A") }
                    Button(onClick = { /* no-op */ }) { Text("B") }
                    Button(onClick = { /* no-op */ }) { Text("C") }
                }
            }
        }
    }
}
