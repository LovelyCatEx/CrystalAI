package com.lovelycatv.ai.crystal.dispatcher.task.dispatcher

import com.lovelycatv.ai.crystal.common.data.message.MessageChainBuilder
import com.lovelycatv.ai.crystal.common.netty.sendMessage
import com.lovelycatv.ai.crystal.common.util.logger
import com.lovelycatv.ai.crystal.common.util.toJSONString
import com.lovelycatv.ai.crystal.dispatcher.manager.AbstractNodeManager
import com.lovelycatv.ai.crystal.dispatcher.task.AbstractTask
import com.lovelycatv.ai.crystal.dispatcher.task.OneTimeChatTask
import com.lovelycatv.ai.crystal.dispatcher.task.TaskPerformResult
import com.lovelycatv.ai.crystal.dispatcher.task.manager.TaskManager
import org.springframework.stereotype.Component

/**
 * @author lovelycat
 * @since 2025-02-27 00:17
 * @version 1.0
 */
@Component
class TaskDispatcher(
    nodeManager: AbstractNodeManager,
    taskManager: TaskManager
) : AbstractTaskDispatcher(nodeManager, taskManager) {
    private val logger = logger()

    override suspend fun performTask(task: AbstractTask): TaskPerformResult<String>? {
        val availableNode = requireAvailableNode(TaskDispatchStrategy.RANDOM)

        if (availableNode == null) {
            logger.error("No available node for task: ${task.toJSONString()}")
            return null
        }

        val taskId = task.taskId

        return if (task is OneTimeChatTask<*>) {
            val sessionId = requireSessionId()

            logger.info("Executing OneTimeChatTask-[${taskId}], allocated node: [${availableNode.nodeName}], sessionId: [${sessionId}], options: [${task.options.toJSONString()}]")

            val message = MessageChainBuilder {
                // Random sessionId
                this.sessionId(sessionId)
                // No streaming
                this.streamId(null)

                // Add OllamaChatOptions is non-null
                task.options?.let {
                    this.addMessage(it)
                }

                // Add all messages
                this.addMessages(task.prompts)
            }

            val result = availableNode.channel.sendMessage(message)

            if (result.success) {
                taskManager.pushSession(recipient = availableNode, messageChain = message, timeout = task.timeout)
                TaskPerformResult.success(
                    taskId = taskId,
                    data = sessionId
                )
            } else {
                logger.error("Task-[${taskId}] execution failed, reason: ${result.reason.name}", result.cause)
                TaskPerformResult.failed(
                    taskId = taskId,
                    data = "",
                    message = "Message send failed, reason: ${result.reason.name}",
                    cause = result.cause
                )
            }
        } else {
            TaskPerformResult.failed(
                taskId = taskId,
                data = "",
                message = "Task type [${task::class.qualifiedName}] is not supported currently"
            )
        }
    }


}