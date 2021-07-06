package snake.views

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.text.Font
import snake.views.controller.CreateLobbyController
import snake.views.controller.JoinLobbyController
import tornadofx.*

class JoinLobbyView : View("Joining Lobby") {
    companion object {
        var current: JoinLobbyView? = null
    }

    private val controller: JoinLobbyController by inject()

    init {
        current = this
    }


    override val root: Parent = vbox {
        label {
            text = "Joining a Lobby"
            font = Font.font(20.0)
            padding = Insets(8.0)
        }

        label {
            text = "Enter a Lobby code"
            padding = Insets(8.0)
        }

        textfield {
            promptText = "Lobby code"
            textProperty().bindBidirectional(controller.lobbyCodeProperty)
            padding = Insets(8.0)
        }

        label {
            text = "Enter your Name"
            padding = Insets(8.0)
        }

        textfield {
            promptText = "Player name"
            textProperty().bindBidirectional(controller.playerNameProperty)
        }

        button {
            text = "Join Lobby"
            action {
                controller.joinLobby()
            }
            padding = Insets(8.0)
        }

        minWidth = 300.0
        minHeight = 300.0
    }
















}