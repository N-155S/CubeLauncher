package com.frzn.cube

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.frzn.cube.ui.CubeLauncherApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // SplashScreen 必须在 super.onCreate() 之前调用
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        // 状态栏 + 导航栏沉浸式
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContent {
            CubeLauncherApp()
        }
        // 内容就绪后移除 splash
        splash.setKeepOnScreenCondition { false }
    }
}
