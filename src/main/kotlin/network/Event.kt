package network

/**
 * Event codes that can be triggered by the server or the client
 */
enum class Event(val code: String) {
    TEST("client:test")
}