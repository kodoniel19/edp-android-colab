package com.liceo.liceochat.data.network.dto

import com.liceo.liceochat.domain.Message
import kotlinx.serialization.json.longOrNull
import kotlinx.serialization.json.jsonPrimitive

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    // Safely try to get as Long, otherwise fallback to 0L
    createdAt = createdAt?.jsonPrimitive?.longOrNull ?: 0L
)

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
