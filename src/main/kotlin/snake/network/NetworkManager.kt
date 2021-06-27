package snake.network

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import snake.gameLogic.Game
import snake.gameLogic.GameState
import snake.network.EventCodes.EventFromServer
import snake.network.EventCodes.EventToServer


/**
 * Class that manage the snake.network connection to the server
 */
object NetworkManager {

    /**
     * The url of the game server
     */
    private val SERVER_URL = "http://95.88.132.181:3000"

    /**
     * The current socket connection - if a current connection exists
     */
    var socket: Socket? = null
    var lobbyListener: ((String) -> Unit)? = null
    var connectionStatusListener: ((Boolean) -> Unit)? = null

    /**
     *
     * Connects to the game server to send and receive messages
     */
    fun connect() {
        println("Connecting to server")
        var options = IO.Options()
        options.auth = null
        options.transports = arrayOf(WebSocket.NAME)

        socket = IO.socket(SERVER_URL, options)
        socket?.connect()

        socket?.on(Socket.EVENT_CONNECT) {
            socket?.emit("client:test", "This is a Benni certified test message: LOL OLO")
            connectionStatusListener?.let { it1 -> it1(true) }
        }

        socket?.on(Socket.EVENT_CONNECT_ERROR) {
            println("Could not connect to server")
            connectionStatusListener?.let { it1 -> it1(false) }
            socket?.on(Socket.EVENT_DISCONNECT) {
                println("Disconnected from server")
            }

            socket?.on(EventFromServer.ERROR.code) { error ->
                println("Received error message: $error")
            }

            socket?.on(EventFromServer.LOBBY_CREATED.code) {
                val lobbyCode = it.first().toString()
                println("Received: Lobby created with code $lobbyCode")
                lobbyListener?.let { it1 -> it1(lobbyCode) }
                socket?.emit(EventToServer.JOIN_LOBBY.code, lobbyCode)
            }

            socket?.on(EventFromServer.USER_JOINED_LOBBY.code) {
                println("Received: Joined lobby")
            }

            socket?.on(EventFromServer.GAME_STATE.code) {
                println("Received: Game state")
                it.firstOrNull()?.let {
                    val gameState = Gson().fromJson(it.toString(), GameState::class.java)
                    Game.add(gameState)
                }
            }
        }
    }
}