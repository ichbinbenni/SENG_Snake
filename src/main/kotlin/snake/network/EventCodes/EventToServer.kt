package snake.network.EventCodes

enum class EventToServer(val code: String) {
    CREATE_LOBBY("client:createLobby"),
    JOIN_LOBBY("client:joinLobby"),
    CHANGE_DIRECTION("client:changeDirection")
}