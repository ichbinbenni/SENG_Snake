package snake.network

import com.google.gson.Gson
import snake.network.EventCodes.EventToServer
import snake.network.Models.CreateLobbyModel
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
     * @param callback: Get called when the join was successfully with the lobby code. Will contain lobby information or an error why you could not join
     */
    fun joinLobby(code: String, callback: (String, NetworkErrorCode?) -> Unit) {
        // TODO: Needs to be implemented, currently just a test callback
        callback("", NetworkErrorCode.LOBBY_ALREADY_FULL)
    }
}