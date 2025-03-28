package com.lovelycatv.crystal.rag.api.repository

import com.lovelycatv.crystal.rag.data.VectorDocument
import com.lovelycatv.crystal.rag.data.VectorDocumentQuery

/**
 * @author lovelycat
 * @since 2025-03-26 14:59
 * @version 1.0
 */
interface VectorRepository {
    fun getRepositoryName(): String

    fun add(vararg documents: VectorDocument): Boolean

    fun remove(vararg documentIds: String): Boolean

    fun similaritySearch(query: VectorDocumentQuery): List<VectorDocument>

    fun similaritySearch(queryContent: String, topK: Int = 5): List<VectorDocument> {
        return this.similaritySearch(VectorDocumentQuery(
            queryContent = queryContent,
            topK = topK
        ))
    }

    fun isRepositoryExists(): Boolean
}