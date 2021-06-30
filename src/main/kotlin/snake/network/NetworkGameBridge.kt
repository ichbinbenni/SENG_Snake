package snake.network

import com.google.gson.Gson
import snake.network.EventCodes.EventFromServer
import snake.network.EventCodes.EventToServer
import snake.network.Models.CreateLobbyModel
import snake.network.Models.JoinLobbyModel
import snake.network.Models.NetworkErrorCode

object NetworkGameBridge {

    // MARK: - MENU

    /**
     * Creates a new lobby.
     * @param model: Model that contains for how many players the lobby is and how big the field is
     * @param callback: Get called when the lobby was created successfully with the lobby code
     */
    fun createLobby(model: CreateLobbyModel, callback: ((String) -> Unit)) {
        NetworkManager.socket?.emit(EventToServer.CREATE_LOBBY.code, Gson().toJson(model))
        NetworkManager.lobbyListener = {
            callback(it)
        }
    }

    /**
     * Joins the lobby
     * @param model: Information about the lobby you want to join. LobbyCode and your name.
     * @param callback: Get called when the server answers. If error code is null joining was successfull
     */
    fun joinLobby(model: JoinLobbyModel, callback: (NetworkErrorCode?) -> Unit) {

        NetworkManager.socket?.on(EventFromServer.USER_JOINED_LOBBY.code) {
            callback(NetworkErrorCode.fromCode(it.firstOrNull()?.toString()?.toInt() ?: -1))
        }

        NetworkManager.socket?.emit(EventToServer.JOIN_LOBBY.code, Gson().toJson(model))
    }
}