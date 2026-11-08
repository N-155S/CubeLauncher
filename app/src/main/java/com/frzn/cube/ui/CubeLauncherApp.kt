@file:OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)

package com.frzn.cube.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.frzn.cube.ui.components.PressMorphFab
import com.frzn.cube.ui.components.PressMorphTonalButton
import com.frzn.cube.ui.theme.CubeLauncherTheme

/**
 * CubeLauncher 根 Composable。
 * 所有按钮使用 PressMorph 圆角变换动画（idle 24dp → press 10dp → release 24dp）。
 */
@Composable
fun CubeLauncherApp() {
    CubeLauncherTheme {
        Scaffold(
            floatingActionButton = {
                PressMorphFab(onClick = { /* no-op */ }) {
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

                PressMorphButton(onClick = { /* no-op */ }) {
                    Text("Standard Button")
                }
                Spacer(Modifier.size(16.dp))

                PressMorphTonalButton(onClick = { /* no-op */ }) {
                    Text("Tonal Button")
                }
                Spacer(Modifier.size(16.dp))

                // 组按钮（Row 布局 + PressMorph）
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    PressMorphButton(onClick = { /* no-op */ }) { Text("A") }
                    PressMorphButton(onClick = { /* no-op */ }) { Text("B") }
                    PressMorphButton(onClick = { /* no-op */ }) { Text("C") }
                }
            }
        }
    }
}
