package snake.network

import com.google.gson.Gson
import gamelogic.SnakeDirection
import snake.network.EventCodes.EventFromServer
import snake.network.EventCodes.EventToServer
import snake.network.Models.CreateLobbyModel
import snake.network.Models.JoinLobbyModel
import snake.network.Models.NetworkErrorCode

object NetworkGameBridge {

    // MARK: - MENU

    /**
     * Creates a new lobby and joins it automatically when receiving the code
     * @param model: Model that contains for how many players the lobby is and how big the field is
     * @param callback: Get called when the lobby was created successfully and you joined it
     */
    fun createLobby(model: CreateLobbyModel, callback: ((NetworkErrorCode?) -> Unit)) {
        NetworkManager.socket?.on(EventFromServer.LOBBY_CREATED.code) {
            val lobbyCode = it.first().toString()
            println("NetworkGameBridge.createLobby: Lobby created with code $lobbyCode")
            NetworkManager.socket?.on(EventFromServer.USER_JOINED_LOBBY.code) { joinLobbyPayload ->
                callback(NetworkErrorCode.fromCode(joinLobbyPayload.firstOrNull()?.toString()?.toInt() ?: -1))
            }
            NetworkManager.socket?.emit(EventToServer.JOIN_LOBBY.code, lobbyCode)
        }
        NetworkManager.socket?.emit(EventToServer.CREATE_LOBBY.code, Gson().toJson(model))
    }

    /**
     * Joins the lobby
     * @param model: Information about the lobby you want to join. LobbyCode and your name.
     * @param callback: Get called when the server answers. If error code is null joining was successfull
     */
    fun joinLobby(model: JoinLobbyModel, callback: (NetworkErrorCode?) -> Unit) {

        NetworkManager.socket?.on(EventFromServer.USER_JOINED_LOBBY.code) {
            println("NetworkGameBridge.joibLobby: Received join event")
            callback(NetworkErrorCode.fromCode(it.firstOrNull()?.toString()?.toInt() ?: -1))
        }

        NetworkManager.socket?.emit(EventToServer.JOIN_LOBBY.code, Gson().toJson(model))
    }

    /**
     * Changes the direction if the snake
     */
    fun changeDirection(direction: SnakeDirection) {
        NetworkManager.socket?.emit(EventToServer.CHANGE_DIRECTION.code, direction.code)
    }
}