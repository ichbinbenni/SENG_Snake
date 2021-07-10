package snake.views

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.control.TextField
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
        var lobbyCodeField: TextField? = null
        var playerNameField: TextField? = null

        hbox {
            button {
                text = " < "
                font = Font.font(20.0)
                action {
                    replaceWith(MenuView())
                    lobbyCodeField?.text = ""
                    playerNameField?.text = ""
                }
                padding = Insets(4.0)
            }

            label {
                text = "Joining a Lobby"
                font = Font.font(20.0)
                padding = Insets(8.0, 8.0, 8.0, 50.0)
            }
        }

        hbox {
            label {
                text = "Enter a Lobby code"
                padding = Insets(8.0, 8.0, 20.0, 8.0)
            }

            lobbyCodeField = textfield {
                promptText = "Lobby code"
                textProperty().bindBidirectional(controller.lobbyCodeProperty)
                padding = Insets(8.0)
            }
        }

        hbox {
            label {
                text = "Enter your Name"
                padding = Insets(8.0, 20.0, 15.0, 8.0)
            }

            playerNameField = textfield {
                promptText = "Player name"
                textProperty().bindBidirectional(controller.playerNameProperty)
                padding = Insets(8.0)
            }
        }

        hbox {
            rectangle {
                width = 185.0
                padding = Insets(8.0)
            }

            button {
                text = "Join Lobby"
                action {
                    controller.joinLobby()
                }
                padding = Insets(8.0)
                disableProperty().bind(playerNameField?.textProperty()?.isEmpty?.or(lobbyCodeField?.textProperty()?.isEmpty))
            }
        }

        minWidth = 300.0
        minHeight = 300.0
    }
}