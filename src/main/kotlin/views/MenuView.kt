package views
//
import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import network.NetworkManager


/**
 * Menu view that let the user decide if he/she/it wants to play singleplayer or multiplayer
 */
class MenuView: Application() {
    var createLobbyButton = Button().apply {
        text = "Create Lobby"
        setOnAction {
            NetworkManager.createLobby(NetworkManager.CreateLobbyModel(3, NetworkManager.FieldSize(20, 20))) {
                Platform.runLater(Runnable {
                    lobbyCodeLabel.text = it
                })
            }
        }
    }

    var lobbyCodeLabel = Label()

    override fun start(primaryStage: Stage) {
        // Styling of the view
        primaryStage.minWidth = 400.0
        primaryStage.minHeight = 300.0

        var layout = VBox().apply {
            children.add(Label("Hello, Snake!"))
            children.add(createLobbyButton)
            children.add(lobbyCodeLabel)
        }

        primaryStage.run {
            scene = Scene(layout)
            show()
        }
    }
}