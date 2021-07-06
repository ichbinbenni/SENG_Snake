package snake.views.controller

import javafx.application.Platform
import snake.gameLogic.Game
import snake.network.Models.CreateLobbyModel
import snake.network.Models.FieldSize
import snake.network.Models.JoinLobbyModel
import snake.network.NetworkGameBridge
import snake.network.NetworkManager
import snake.views.MenuView
import snake.views.SnakeUI
import tornadofx.Controller
import tornadofx.booleanProperty
import tornadofx.stringProperty

class MenuController: Controller() {

    var isConnected = booleanProperty(false)
    var isConnectedText= stringProperty("Connecting to server...")







    init {
        // Set initial connection status - client can already be connected
        setConnectedStatus()
        // Listen if client is connected to server
        NetworkManager.connectionStatusListener = {
            setConnectedStatus()
        }
    }

    /**
     * Sets the current connection status. This will enable/disable buttons and sets the text so the user knows if he/shw/it is connected
     */
    private fun setConnectedStatus() {
        isConnected.set(NetworkManager.isConnected)
        Platform.runLater {
            if (NetworkManager.isConnected) {
                isConnectedText.set("Connected to game server")
            } else {
                isConnectedText.set("Could not connect to game server")
            }
        }
    }
}