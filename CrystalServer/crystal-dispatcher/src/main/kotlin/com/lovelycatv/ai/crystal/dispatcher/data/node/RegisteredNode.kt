package com.lovelycatv.ai.crystal.dispatcher.data.node

import com.lovelycatv.ai.crystal.common.response.ollama.OllamaModelMeta

/**
 * @author lovelycat
 * @since 2025-02-08 01:37
 * @version 1.0
 */
data class RegisteredNode(
    val nodeId: String,
    val nodeName: String,
    val host: String,
    val port: Int,
    val ssl: Boolean,
    val isAlive: Boolean,
    val registeredTimestamp: Long,
    val lastAliveTimestamp: Long,
    val lastAliveCheckTimestamp: Long,
    val lastUpdateTimestamp: Long,
    val ollamaModels: List<OllamaModelMeta>
) {
    val requestUrl: String get() = "${if (ssl) "https" else "http"}://$host:$port"
}