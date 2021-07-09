package snake.views

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.text.Font
import javafx.stage.Modality
import tornadofx.*
import snake.views.controller.MenuController

class MenuView : View("Menu") {
    companion object {
        var current: MenuView? = null
    }
    private val controller: MenuController by inject()

    init {
        current = this
    }

    override val root: Parent = vbox {
        label {
            text = "Snake"
            padding = Insets(8.0)
            font = Font.font(50.0)
        }

        label {
            text = "Made by some guys"
            padding = Insets(-8.0, 8.0,8.0,8.0)
        }

        rectangle {
            height = 120.0
            padding = Insets(8.0)
        }

        button {
            text = "Join Lobby"
            action {
                replaceWith(JoinLobbyView())
            }
            padding = Insets(8.0, 18.0, 8.0, 8.0)
            disableProperty().bind(!controller.isConnected)
        }
        
        button {
            text = "Create lobby"
            action {
                replaceWith(CreateLobbyView())
            }
            padding = Insets(8.0)
            disableProperty().bind(!controller.isConnected)
        }

        label {
            textProperty().bind(controller.isConnectedText)
            font = Font.font(10.0)
            padding = Insets(8.0)
        }

        minWidth = 300.0
        minHeight = 300.0
    }
}