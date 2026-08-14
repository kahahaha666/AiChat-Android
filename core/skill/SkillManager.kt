package com.yourname.aichat.core.skill

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json
import java.io.File

/**
 * Skill 管理器
 * 负责从本地存储加载 Skill，支持动态添加和删除
 */
class SkillManager(private val context: Context) {

    private val _skills = MutableStateFlow<List<SkillDefinition>>(emptyList())
    val skills: StateFlow<List<SkillDefinition>> = _skills

    private val skillsDir = File(context.filesDir, "skills")

    init {
        loadSkills()
    }

    /**
     * 从本地文件加载所有 Skill
     */
    private fun loadSkills() {
        if (!skillsDir.exists()) {
            skillsDir.mkdirs()
        }
        
        val skillFiles = skillsDir.listFiles { file -> file.extension == "json" } ?: emptyArray()
        val loadedSkills = mutableListOf<SkillDefinition>()
        
        for (file in skillFiles) {
            try {
                val jsonContent = file.readText()
                val skill = Json.decodeFromString<SkillDefinition>(jsonContent)
                loadedSkills.add(skill)
            } catch (e: Exception) {
                e.printStackTrace() // 记录错误日志
            }
        }
        
        _skills.value = loadedSkills
    }

    /**
     * 导入 Skill (从 JSON 字符串或文件)
     */
    fun importSkill(jsonContent: String): Result<SkillDefinition> {
        return try {
            val skill = Json.decodeFromString<SkillDefinition>(jsonContent)
            saveSkillToFile(skill)
            loadSkills() // 重新加载
            Result.success(skill)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * 保存 Skill 到本地文件
     */
    private fun saveSkillToFile(skill: SkillDefinition) {
        val file = File(skillsDir, "${skill.id}.json")
        val jsonContent = Json.encodeToString(SkillDefinition.serializer(), skill)
        file.writeText(jsonContent)
    }

    /**
     * 删除 Skill
     */
    fun deleteSkill(skillId: String) {
        val file = File(skillsDir, "$skillId.json")
        if (file.exists()) {
            file.delete()
            loadSkills()
        }
    }

    /**
     * 启用/禁用 Skill
     */
    fun toggleSkill(skillId: String, enabled: Boolean) {
        val currentSkills = _skills.value.toMutableList()
        val index = currentSkills.indexOfFirst { it.id == skillId }
        if (index != -1) {
            val updatedSkill = currentSkills[index].copy(isEnabled = enabled)
            currentSkills[index] = updatedSkill
            saveSkillToFile(updatedSkill)
            _skills.value = currentSkills
        }
    }

    /**
     * 执行 Skill
     */
    suspend fun executeSkill(skillId: String, input: Any?): SkillResult {
        val skill = _skills.value.find { it.id == skillId }
            ?: return SkillResult(false, "Skill not found")

        if (!skill.isEnabled) {
            return SkillResult(false, "Skill is disabled")
        }

        return when (skill.actionType) {
            ActionType.IMAGE_GEN -> executeImageGen(skill, input)
            ActionType.FILE_PARSE -> executeFileParse(skill, input)
            ActionType.MCP_TOOL -> executeMcpTool(skill, input)
            ActionType.SYSTEM_CMD -> executeSystemCmd(skill, input)
            ActionType.CUSTOM -> executeCustomScript(skill, input)
        }
    }

    private suspend fun executeImageGen(skill: SkillDefinition, input: Any?): SkillResult {
        // TODO: 实现生图逻辑
        return SkillResult(true, "Image generation started", data = null)
    }

    private suspend fun executeFileParse(skill: SkillDefinition, input: Any?): SkillResult {
        // TODO: 实现文件解析逻辑
        return SkillResult(true, "File parsed", data = null)
    }

    private suspend fun executeMcpTool(skill: SkillDefinition, input: Any?): SkillResult {
        // TODO: 实现 MCP 工具调用
        return SkillResult(true, "MCP tool executed", data = null)
    }

    private suspend fun executeSystemCmd(skill: SkillDefinition, input: Any?): SkillResult {
        // TODO: 实现系统命令执行
        return SkillResult(true, "Command executed", data = null)
    }

    private suspend fun executeCustomScript(skill: SkillDefinition, input: Any?): SkillResult {
        // TODO: 实现自定义脚本执行 (可能需要集成 Kotlin Scripting 或 JS Engine)
        return SkillResult(true, "Custom script executed", data = null)
    }
}