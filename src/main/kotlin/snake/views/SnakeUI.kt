package snake.views;

import gamelogic.SnakeDirection
import javafx.application.Platform
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Paint
import snake.gameLogic.Game
import snake.gameLogic.Game.changePlayerDirection
import snake.gameLogic.GameState
import snake.gameLogic.GameStateListener
import snake.gameLogic.SnakeHead
import tornadofx.View
import tornadofx.label
import tornadofx.pane
import tornadofx.vbox

class SnakeUI() : View("Snake-Multiplayer"), GameStateListener {

    // MARK: - Constants
    private val RECTANGLE_SIZE = 20.0
    private val BOARD_SIZE = 30

    // MARK: - UI-Elements

    private var topLabel: Label = label {
        text = "Name: ${Game.playerName}"
    }

    private val gameBoard = pane {
        addEventFilter(KeyEvent.KEY_PRESSED) { event ->
            println("pressed: " + event.code)
            when (event.code) {
                KeyCode.UP, KeyCode.W -> changePlayerDirection(Game.playerName, SnakeDirection.NORTH)
                KeyCode.DOWN, KeyCode.S -> changePlayerDirection(Game.playerName, SnakeDirection.SOUTH)
                KeyCode.LEFT, KeyCode.A -> changePlayerDirection(Game.playerName, SnakeDirection.WEST)
                KeyCode.RIGHT, KeyCode.D -> changePlayerDirection(Game.playerName, SnakeDirection.EAST)
            }
        }
    }

    override val root = vbox {
    }

    private var canvas = Canvas(RECTANGLE_SIZE*BOARD_SIZE, RECTANGLE_SIZE*BOARD_SIZE)

    /*Initializes the GameBoard Grid, with multiple Rectangles, each Rectangle represents 1 Field of the Array*/

    // MARK: - Lifecycle
    init {
        root.children.add(topLabel)
        gameBoard.children.add(canvas)
        root.children.add(gameBoard)

        Game.gameStateListener = this
        if (Game.gameStates.isNotEmpty()) {
            Game.gameStates.lastOrNull()?.let {
                onGameStateChanged(it)
            }
        }
        setWindowMinSize(RECTANGLE_SIZE*BOARD_SIZE+16, RECTANGLE_SIZE*BOARD_SIZE + 55)
    }

    override fun onDock() {
        gameBoard.requestFocus()
    }


    fun gameEnded(gameState: GameState) {
        val graphicsContext2D = canvas.graphicsContext2D

        gameState.snakes.forEach {
            if (it.snakeID == Game.playerName) {
                if (it.playerLost) {
                    val image = Image("/You Lose.jpg")
                    graphicsContext2D.drawImage(
                        image,
                        canvas.width / 2 - image.width / 2,
                        canvas.height / 2 - image.height / 2
                    )
                } else if (!it.playerLost && !gameState.gameIsRunning) {
                    val image = Image("/youWin.jpg")
                    graphicsContext2D.drawImage(
                        image,
                        canvas.width / 2 - image.width / 2,
                        canvas.height / 2 - image.height / 2
                    )
                }
            }
        }
    }


    override fun onGameStateChanged(gameState: GameState) {
        println("SnakeUI.onGameStateChanged: Drawing field for new state")

        val graphicsContext2D = canvas.graphicsContext2D

        // Clear board  (draw all cells white)
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                graphicsContext2D.fill = Paint.valueOf("#FFFFFF")
                graphicsContext2D.fillRect(i * RECTANGLE_SIZE, j * RECTANGLE_SIZE, RECTANGLE_SIZE, RECTANGLE_SIZE)
            }
        }

        Platform.runLater() {
            topLabel.text = "Name: ${Game.playerName}  LobbyID: ${Game.lobbyCode}"
            topLabel.textFill = Paint.valueOf(gameState.snakes[0].snakeColor)
        }

        if(gameState.gameIsRunning == false) {
            gameEnded(gameState)
            return
        }

        /**
         * Draws all snakes in their colors
         */
        gameState.snakes.forEach {
            println("SnakeUI.onGameStateChanged: Drawing head at position x${it.snakeHead?.posX} y${it.snakeHead?.posY} color:${it.snakeColor}")
            graphicsContext2D.fill = Paint.valueOf(it.snakeColor)

            graphicsContext2D.fillOval(
                (it.snakeHead?.posX ?: 0) * RECTANGLE_SIZE,
                (it.snakeHead?.posY ?: 0) * RECTANGLE_SIZE,
                RECTANGLE_SIZE,
                RECTANGLE_SIZE
            )

            it.snakeParts?.drop(1)?.forEach { part ->
                graphicsContext2D.fillRect(
                    part.posX * RECTANGLE_SIZE,
                    part.posY * RECTANGLE_SIZE,
                    RECTANGLE_SIZE,
                    RECTANGLE_SIZE
                )
            }
        }
        gameEnded(gameState)
    }
}