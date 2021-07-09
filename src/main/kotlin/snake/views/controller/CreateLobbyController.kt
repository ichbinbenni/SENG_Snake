package snake.views.controller

import javafx.application.Platform
import snake.gameLogic.Game
import snake.network.Models.CreateLobbyModel
import snake.network.Models.FieldSize
import snake.network.NetworkGameBridge
import snake.views.MenuView
import snake.views.SnakeUI
import tornadofx.Controller
import tornadofx.getProperty
import tornadofx.stringProperty

class CreateLobbyController: Controller() {

    var lobbyCodeProperty = stringProperty("")
    var playerNameProperty = stringProperty("")
    var playFieldSize = stringProperty("")
    var playerMode = stringProperty("")
    var size = 30;
    var playerCount = 1;

    fun createLobby() {
        println("MenuView.joinLobby: Creating...")
        var size = playFieldSize.value
        when (size) {
            "Big" -> this.size = 50
            "Middle" -> this.size = 30
            else ->  this.size = 20
        }
        if(playerMode.value == "1 Player"){
            playerCount = 1
        }else {
            playerCount = 2
        }

        println("MenuView.createLobby: Creating lobby ${playerNameProperty.value}")
        NetworkGameBridge.createLobby(CreateLobbyModel(playerCount, FieldSize(this.size,this.size), playerNameProperty.value), callback = {
            println("MenuView.createLobby: Created")
            Platform.runLater {
                Game.playerName = playerNameProperty.value
                SnakeUI().openWindow()
            }
        })
    }
}