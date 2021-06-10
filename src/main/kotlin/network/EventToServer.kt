package network

enum class EventToServer(val code: String) {
    CREATE_LOBBY("client:createLobby"),
    JOIN_LOBBY("client:joinLobby")
}