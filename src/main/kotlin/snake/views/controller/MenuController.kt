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
    var lobbyCodeProperty = stringProperty("")
    var playerNameProperty = stringProperty("")

    fun createLobby() {
        println("MenuView.joinLobby: Creating...")
        // TODO: Make playerCount and field size editable by the user
        NetworkGameBridge.createLobby(CreateLobbyModel(1, FieldSize(30,30)), callback = {
            println("MenuView.createLobby: Created")
            Platform.runLater {
                Game.playerName = playerNameProperty.value
                // TODO: Show lobby waiting screen?
                MenuView.current?.replaceWith<SnakeUI>()
            }
        })
    }

    /**
     * Joins a lobby
     */
    fun joinLobby() {
        println("MenuView.joinLobby: Joining ${lobbyCodeProperty.value} with name ${playerNameProperty.value}...")
        NetworkGameBridge.joinLobby(JoinLobbyModel(playerNameProperty.value, lobbyCodeProperty.value), callback = {
            println("MenuView.joinLobby: Joined")
            Platform.runLater {
                // TODO: Show lobby waiting screen?
                Game.playerName = playerNameProperty.value
                MenuView.current?.replaceWith<SnakeUI>()
            }
        })
    }

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