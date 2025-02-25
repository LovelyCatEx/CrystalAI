package com.lovelycatv.ai.crystal.common

object GlobalConstants {
    object ApiVersionControl {
        const val API_PREFIX_VERSION_1 = "/api/v1"
        const val API_PREFIX_VERSION_2 = "/api/v2"

        const val API_PREFIX_FOR_DISPATCHER = API_PREFIX_VERSION_1
        const val API_PREFIX_FOR_NODE = API_PREFIX_VERSION_1
    }

    /**
     * All apis in this project should be declared here.
     */
    object Api {
        object Dispatcher {
            object NodeController {
                const val MAPPING = "/node"
                const val NODE_REGISTER = "/register"
                const val NODE_UNREGISTER = "/unregister"
                const val NODE_CHECK = "/check"
            }
        }

        object Node {
            object ProbeController {
                const val MAPPING = "/probe"
                const val NODE_INFO = "/info"
            }
        }
    }

}