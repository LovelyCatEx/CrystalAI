package com.lovelycatv.ai.crystal.dispatcher.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author lovelycat
 * @since 2025-03-03 01:27
 * @version 1.0
 */
data class ChatCompletionPayloads @JsonCreator constructor(
    @JsonProperty("model")
    val model: String,
    @JsonProperty("messages")
    val messages: List<ChatCompletionMessage>,
    @JsonProperty("stream")
    val stream: Boolean
)