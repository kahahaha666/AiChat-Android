package com.yourname.aichat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yourname.aichat.core.security.BiometricAuth
import com.yourname.aichat.ui.theme.DarkLiquidGlassTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val biometricAuth: BiometricAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        var isAuthenticated by mutableStateOf(false)

        setContent {
            MaterialTheme(colorScheme = DarkLiquidGlassTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    if (isAuthenticated) {
                        // TODO: 进入主聊天界面
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            androidx.compose.material3.Text("欢迎回来", style = MaterialTheme.typography.headlineMedium)
                        }
                    } else {
                        LaunchedEffect(Unit) {
                            biometricAuth.authenticate(
                                activity = this@MainActivity,
                                onSuccess = { isAuthenticated = true },
                                onError = { 
                                    // 认证失败处理，可以显示密码输入框或退出
                                    finishAffinity() 
                                }
                            )
                        }
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}