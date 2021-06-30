package snake.network

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import snake.gameLogic.Game
import snake.gameLogic.GameState
import snake.network.EventCodes.EventFromServer
import snake.network.EventCodes.EventToServer
import java.lang.Exception


/**
 * Class that manage the snake.network connection to the server
 */
object NetworkManager {

    /**
     * The url of the game server
     */
    private val SERVER_URL = "http://niklaseckert.ddns.net:3001"

    /**
     * The current socket connection - if a current connection exists
     */
    var socket: Socket? = null
    var isConnected: Boolean = false
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
            println("NetworkManager.connect: Connected")
            socket?.emit("client:test", "This is a Benni certified test message: LOL OLO")
            isConnected = true
            connectionStatusListener?.let { it1 -> it1(true) }
        }

        socket?.on(Socket.EVENT_CONNECT_ERROR) {
            println("NetworkManager.connect: Error connecting")
            println("${it.first().toString()}")
            isConnected = false
            connectionStatusListener?.let { it1 -> it1(false) }
        }

        socket?.on(Socket.EVENT_DISCONNECT) {
            println("NetworkManager.connect: Disconnect")
        }

        socket?.on(EventFromServer.ERROR.code) { error ->
            println("Received error message: $error")
        }

        socket?.on(EventFromServer.GAME_STATE.code) {
            println("NetworkManager.connect: Received game state")
            println(it.first().toString())
            it.firstOrNull()?.let { jsonString ->
                try {
                    val gameState = Gson().fromJson(jsonString.toString(), GameState::class.java)
                    println("NetworkManager.connect: Adding game state")
                    Game.add(gameState)
                } catch(e: Exception) {
                    println("Could not parse model: ${e.localizedMessage}")
                }
            }
        }
    }
}