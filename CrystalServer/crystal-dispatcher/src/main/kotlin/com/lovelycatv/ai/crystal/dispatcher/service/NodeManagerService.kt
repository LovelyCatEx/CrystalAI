package com.lovelycatv.ai.crystal.dispatcher.service

import com.lovelycatv.ai.crystal.common.response.dispatcher.NodeRegisterResult
import com.lovelycatv.ai.crystal.dispatcher.data.node.RegisteredNode

/**
 * @author lovelycat
 * @since 2025-02-09 22:58
 * @version 1.0
 */
interface NodeManagerService {
    fun registerNode(nodeHost: String, nodePort: Int, ssl: Boolean): NodeRegisterResult

    fun unregisterNode(nodeHost: String, nodePort: Int): Boolean

    fun isNodeRegistered(nodeUUID: String): Boolean

    /**
     * List all registered nodes
     *
     * @return List of all [RegisteredNode]
     */
    fun listAllNodes(): List<RegisteredNode>
}