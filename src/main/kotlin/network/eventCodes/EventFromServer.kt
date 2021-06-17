package network.eventCodes

/**
 * Event codes that can be triggered by the server or the client
 */
enum class EventFromServer(val code: String) {
    ERROR("application_error"),
    USER_JOINED_LOBBY("lobby:joined"),
    LOBBY_CREATED("server:lobbyCreated")
}