package com.yourname.aichat.core.skill

import kotlinx.serialization.Serializable

/**
 * Skill 定义数据类
 * 支持从 JSON 文件导入
 */
@Serializable
data class SkillDefinition(
    val id: String,              // 唯一标识，如 "image_gen_sd"
    val name: String,            // 显示名称，如 "Stable Diffusion 生图"
    val description: String,     // 功能描述
    val version: String = "1.0.0",
    val author: String = "",
    val category: String = "general", // 分类：image, tool, mcp, etc.
    val isEnabled: Boolean = true,
    val config: Map<String, String> = emptyMap(), // 配置项，如 API URL, Key
    val triggers: List<String> = emptyList(), // 触发关键词，如 ["/img", "生成图片"]
    val script: String? = null,  // 可选：Kotlin Script 或 JavaScript 逻辑
    val actionType: ActionType = ActionType.CUSTOM // 预定义动作类型
)

enum class ActionType {
    CUSTOM,          // 自定义脚本
    IMAGE_GEN,       // 生图
    FILE_PARSE,      // 文件解析
    MCP_TOOL,        // MCP 工具调用
    SYSTEM_CMD       // 系统命令
}

/**
 * Skill 执行结果
 */
data class SkillResult(
    val success: Boolean,
    val message: String,
    val data: Any? = null, // 返回的数据，如图片 URI、文本结果
    val error: String? = null
)