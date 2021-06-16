package network

import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket


/**
 * Class that manage the network connection to the server
 */
object NetworkManager {

    /**
     * The url of the game server
     */
    private val SERVER_URL = "http://niklaseckert.ddns.net:3000"

    /**
     * The current socket connection - if a current connection exists
     */
    var socket: Socket? = null


    private var lobbyListener: ((String) -> Unit)? = null
    /**
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
        }

        socket?.on(Socket.EVENT_CONNECT_ERROR) {
            println("Could not connect to server")


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
        }
    }


    // MARK: - Public functions
    class FieldSize(val x: Int, val y: Int)
    class CreateLobbyModel(val playerCount: Int, val size: FieldSize)

    /**
     * Creates a new lobby.
     * @param model: Model that contains for how many players the lobby is and how big the field is
     * @param callback: Get called when the lobby was created successfully with the lobby code
     */
    fun createLobby(model: CreateLobbyModel, callback: ((String) -> Unit)) {
        socket?.emit(EventToServer.CREATE_LOBBY.code, Gson().toJson(model))
        lobbyListener = {
            callback(it)
        }
    }

    fun joinLobby(code: String, callback: (String) -> Unit) {

    }

    fun sendUsername(name: String) {

    }
}