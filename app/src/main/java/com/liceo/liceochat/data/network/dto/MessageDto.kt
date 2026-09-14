package com.liceo.liceochat.data.network.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class MessageDto(
    val id: String? = null,
    val sender: String? = null,
    val text: String? = null,
    // Use JsonElement to safely handle both String (ISO date) and Long (timestamp)
    val createdAt: JsonElement? = null
)

@Serializable
data class NewMessageDto(
    val sender: String,
    val text: String,
    val createdAt: Long
)
