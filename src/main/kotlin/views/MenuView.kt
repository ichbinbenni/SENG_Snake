package views

import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.text.Font
import tornadofx.*
import views.controller.MenuController

class MenuView : View("Menu") {

    val controller: MenuController by inject()

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
            text = "Create lobby"
            action {
                controller.createLobby()
            }
            padding = Insets(8.0)
            disableProperty().bind(!controller.isConnected)
        }

        label {
            textProperty().bind(controller.isConnectedText)
            font = Font.font(10.0)
            padding = Insets(8.0)
        }
    }
}