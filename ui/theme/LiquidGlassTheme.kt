package com.yourname.aichat.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * 液态玻璃风格颜色定义
 */
object LiquidGlassColors {
    // 主色调 - 半透明蓝紫色
    val Primary = Color(0x806200EA) // 50% 透明度
    val PrimaryContainer = Color(0x406200EA) // 25% 透明度
    
    // 背景色 - 深色渐变基底
    val BackgroundDark = Color(0xFF121212)
    val SurfaceDark = Color(0x601E1E1E) // 半透明表面
    
    // 文字颜色
    val OnPrimary = Color.White
    val OnBackground = Color(0xFFE0E0E0)
    
    // 玻璃效果边框
    val GlassBorder = Color(0x30FFFFFF)
}

/**
 * 液态玻璃形状
 */
object LiquidGlassShapes {
    val Small = RoundedCornerShape(12.dp)
    val Medium = RoundedCornerShape(20.dp)
    val Large = RoundedCornerShape(28.dp)
}

/**
 * 深色主题配置
 */
val DarkLiquidGlassTheme = darkColorScheme(
    primary = LiquidGlassColors.Primary,
    primaryContainer = LiquidGlassColors.PrimaryContainer,
    background = LiquidGlassColors.BackgroundDark,
    surface = LiquidGlassColors.SurfaceDark,
    onPrimary = LiquidGlassColors.OnPrimary,
    onBackground = LiquidGlassColors.OnBackground
)

/**
 * 浅色主题配置 (可选)
 */
val LightLiquidGlassTheme = lightColorScheme(
    primary = Color(0x806200EA),
    primaryContainer = Color(0x406200EA),
    background = Color(0xFFF5F5F5),
    surface = Color(0x60FFFFFF),
    onPrimary = Color.White,
    onBackground = Color(0xFF121212)
)