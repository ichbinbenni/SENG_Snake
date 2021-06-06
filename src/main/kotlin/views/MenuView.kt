package views

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage


/**
 * Menu view that let the user decide if he/she/it wants to play singleplayer or multiplayer
 */
class MenuView: Application()
{
    override fun start(primaryStage: Stage)
    {
        var layout = VBox().apply {
            children.add(Label("Hello, Snake!"))
        }

        // Styling of the view
        primaryStage.minWidth = 400.0
        primaryStage.minHeight = 300.0

        primaryStage.run {
            scene = Scene(layout)
            show()
        }
    }
}