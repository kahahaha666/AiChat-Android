package com.yourname.aichat.data.local

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class CharacterCard(
    val name: String,
    val description: String,
    val personality: String,
    val scenario: String = "",
    val firstMessage: String = "",
    val tags: List<String> = emptyList()
)

class CharacterManager(private val context: Context) {
    private val charactersDir = File(context.filesDir, "characters")

    init {
        if (!charactersDir.exists()) charactersDir.mkdirs()
    }

    fun importCharacter(jsonContent: String): Result<CharacterCard> {
        return try {
            val card = Json.decodeFromString<CharacterCard>(jsonContent)
            val file = File(charactersDir, "${card.name.replace(" ", "_")}.json")
            file.writeText(jsonContent)
            Result.success(card)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getAllCharacters(): List<CharacterCard> {
        val files = charactersDir.listFiles { f -> f.extension == "json" } ?: emptyArray()
        return files.mapNotNull { 
            try { Json.decodeFromString<CharacterCard>(it.readText()) } catch (e: Exception) { null }
        }
    }
}