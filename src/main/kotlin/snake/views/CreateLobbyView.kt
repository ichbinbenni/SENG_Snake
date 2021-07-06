package snake.views

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.text.Font
import snake.views.controller.CreateLobbyController
import snake.views.controller.MenuController
import tornadofx.*

class CreateLobbyView : View("Create Lobby") {
    companion object {
        var current: CreateLobbyView? = null
    }
    private val controller: CreateLobbyController by inject()

    init {
        current = this
    }


    override val root: Parent = vbox {
        label {
            text = "Create a Lobby"
            font = Font.font(20.0)
            padding = Insets(8.0)
        }

        label {
            text = "Lobby Code"
            padding = Insets(8.0)
        }

        textfield {
            promptText = "Lobby code"
            textProperty().bindBidirectional(controller.lobbyCodeProperty)
            padding = Insets(8.0)
        }

        label {
            text = "Player Name"
            padding = Insets(8.0)
        }

        textfield {
            promptText = "Player name"
            textProperty().bindBidirectional(controller.playerNameProperty)
            padding = Insets(8.0)
        }

        label {
            text = "Gamefield Size Property"
            padding = Insets(8.0)
        }

        val gameFieldSize = FXCollections.observableArrayList("Big",
            "Middle","Small")

        val selectedSize = SimpleStringProperty()
        selectedSize.bindBidirectional(controller.playFieldSize)

        combobox(selectedSize, gameFieldSize)

        label {
            text = "Game Mode"
            padding = Insets(8.0)
        }


        val playerMode = FXCollections.observableArrayList("1 Player",
            "2 Player")

        val modeChoosen = SimpleStringProperty()
        modeChoosen.bindBidirectional(controller.playerMode)

        combobox(modeChoosen, playerMode)
        padding = Insets(8.0)

        button {
            text = "Create lobby"
            action {
                controller.createLobby()
            }
            padding = Insets(8.0)
        }

        minWidth = 300.0
        minHeight = 300.0
    }











}