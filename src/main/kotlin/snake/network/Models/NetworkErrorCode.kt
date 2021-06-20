package snake.network.Models

enum class NetworkErrorCode(val code: Int, val description: String = "") {
    LOBBY_ALREADY_FULL(30001, "Could not join lobby. Lobby is full."),
    LOBBY_IS_ALREADY_RUNNING(30002, "Could not join lobby. Game is running.")
}