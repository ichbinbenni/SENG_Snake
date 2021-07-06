package snake.views

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.text.Font
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

    //TODO: Layout
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
                MenuView.current?.replaceWith<JoinLobbyView>()
            }
            disableProperty().bind(!controller.isConnected)
        }
        button {
            text = "Create lobby"
            action {
                MenuView.current?.replaceWith<CreateLobbyView>()
            }
            padding = Insets(8.0)
            //disableProperty().bind(!controller.isConnected)
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