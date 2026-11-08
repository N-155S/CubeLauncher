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
import com.frzn.cube.ui.components.PressMorphButton
import com.frzn.cube.ui.components.PressMorphTonalButton
import com.frzn.cube.ui.theme.CubeLauncherTheme

/**
 * CubeLauncher 根 Composable。
 *
 * M3E 组件 + 手写圆角变换按钮验证页。
 */
@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { /* no-op */ }) {
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

                // 1. M3E 原生 Button
                Button(onClick = { /* no-op */ }) {
                    Text("M3E Button")
                }
                Spacer(Modifier.size(16.dp))

                // 2. M3E 原生 Tonal 按钮
                FilledTonalButton(onClick = { /* no-op */ }) {
                    Text("M3E Tonal")
                }
                Spacer(Modifier.size(16.dp))

                // 3. 手写圆角变换按钮
                PressMorphButton(onClick = { /* no-op */ }) {
                    Text("Morph Button")
                }
                Spacer(Modifier.size(16.dp))

                // 4. 手写圆角变换 Tonal 按钮
                PressMorphTonalButton(onClick = { /* no-op */ }) {
                    Text("Morph Tonal")
                }
                Spacer(Modifier.size(16.dp))

                // 5. M3E ButtonGroup
                ButtonGroup {
                    Button(onClick = { /* no-op */ }) { Text("A") }
                    Button(onClick = { /* no-op */ }) { Text("B") }
                    Button(onClick = { /* no-op */ }) { Text("C") }
                }
            }
        }
    }
}
