package com.yourname.aichat.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yourname.aichat.ui.theme.LiquidGlassColors
import com.yourname.aichat.ui.theme.LiquidGlassShapes

/**
 * 液态玻璃效果卡片
 * @param content 卡片内容
 * @param cornerRadius 圆角半径
 * @param borderWidth 边框宽度
 * @param padding 内边距
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = LiquidGlassShapes.Medium.topStart,
    borderWidth: Dp = 1.dp,
    padding: Dp = 16.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(LiquidGlassShapes.Medium)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        LiquidGlassColors.SurfaceDark.copy(alpha = 0.3f),
                        LiquidGlassColors.SurfaceDark.copy(alpha = 0.1f)
                    )
                )
            )
            .border(
                width = borderWidth,
                brush = Brush.linearGradient(
                    colors = listOf(
                        LiquidGlassColors.GlassBorder,
                        Color.Transparent
                    )
                ),
                shape = LiquidGlassShapes.Medium
            )
            .padding(padding)
    ) {
        content()
    }
}