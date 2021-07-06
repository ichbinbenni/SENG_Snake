package snake.views.controller

import javafx.application.Platform
import snake.gameLogic.Game
import snake.network.Models.JoinLobbyModel
import snake.network.NetworkGameBridge
import snake.views.MenuView
import snake.views.SnakeUI
import tornadofx.Controller
import tornadofx.stringProperty

class JoinLobbyController: Controller() {

    var lobbyCodeProperty = stringProperty("")
    var playerNameProperty = stringProperty("")


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



}