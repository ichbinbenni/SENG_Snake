package snake.views.controller

import javafx.application.Platform
import snake.network.NetworkManager
import tornadofx.Controller
import tornadofx.booleanProperty
import tornadofx.stringProperty

class MenuController: Controller() {

    var isConnected = booleanProperty(false)
    var isConnectedText= stringProperty("Connecting to server...")

    fun createLobby() {

    }

    init {
        // Listen if client is connected to server
        NetworkManager.connectionStatusListener = {
            isConnected.set(it)
            Platform.runLater {
                if (it) {
                    isConnectedText.set("Connected to game server")
                } else {
                    isConnectedText.set("Could not connect to game server")
                }
            }
        }
    }
}