package network

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket


/**
 * Class that manage the network connection to the server
 */
object NetworkManager {

    /**
     * Connects to the game server to send and receive messages
     */
    fun connect() {
        println("Connecting to server")
        var options = IO.Options()
        options.auth = null
        options.transports = arrayOf(WebSocket.NAME) // Set the transfer to 'websocket' instead of 'polling'

        val socket = IO.socket("http://niklaseckert.ddns.net:3000", options)
        socket.connect()

        socket.on(Socket.EVENT_CONNECT) {
            socket.emit("client:test", "This is a Benni certified test message: LOL OLO")
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) {
            println("Could not connect to server")
        }
        socket.on(Socket.EVENT_DISCONNECT) {
            println("Disconnected from server")
        }

        // TODO: Delete this later
        socket.on(Event.TEST.code) {
            println("Received message from server")
        }
    }
}