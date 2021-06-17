package views.controller

import javafx.application.Platform
import javafx.beans.property.BooleanProperty
import network.NetworkManager
import tornadofx.Controller
import tornadofx.booleanProperty
import tornadofx.stringProperty

class MenuController: Controller() {

    var isConnectedText= stringProperty("Connecting to server...")

    fun createLobby() {

    }

    init {
        // Listen if client is connected to server
        NetworkManager.connectionStatusListener = {
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