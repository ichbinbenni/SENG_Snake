package snake.views;

import gamelogic.SnakeDirection
import javafx.application.Platform
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import snake.gameLogic.Game
import snake.gameLogic.Game.changePlayerDirection
import snake.gameLogic.GameState
import snake.gameLogic.GameStateListener
import tornadofx.*

class SnakeUI() : View("Snake-Multiplayer"), GameStateListener {

    // MARK: - Constants
    private val RECTANGLE_SIZE = 20.0
    private val BOARD_SIZE = 30

    // MARK: - Vars
    private var grid = arrayOf<Array<Rectangle>>()

    // MARK: - UI-Elements

    private var topLabel: Label? = null

    override val root = vbox {

        topLabel = label {
            text = "Name: ${Game.playerName}"
        }

    }


    val gameBoard = pane {
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


    /*Initializes the GameBoard Grid, with multiple Rectangles, each Rectangle represents 1 Field of the Array*/

    // MARK: - Lifecycle
    init {
        for (i in 0..BOARD_SIZE) {
            var array = arrayOf<Rectangle>()
            for (j in 0..BOARD_SIZE) {
                var rect = Rectangle(i * RECTANGLE_SIZE, j * RECTANGLE_SIZE, RECTANGLE_SIZE, RECTANGLE_SIZE);
                array += rect
                gameBoard.add(rect)
            }
            grid += array
        }

        Game.gameStateListener = this
        if (Game.gameStates.isEmpty() == false) {
            Game.gameStates.lastOrNull()?.let {
                onGameStateChanged(it)
            }
        }
        setWindowMinSize(400, 400)
    }

    override fun onDock() {
        root.requestFocus()
    }


    fun gameEnded(gameState: GameState) {
        gameState.snakes.forEach {
            if (it.playerLost) {
                Platform.runLater {
                    imageview("/You Lose.jpg") {
                        scaleX = 1.0
                        scaleY = 1.0
                    }
                }
            } else if (!it.playerLost && !gameState.gameIsRunning) {
                Platform.runLater {
                    imageview("/youWin.jpg") {
                        scaleX = 1.0
                        scaleY = 1.0
                    }
                }
            }
        }
    }


    override fun onGameStateChanged(gameState: GameState) {
        gameEnded(gameState)
        println("SnakeUI.onGameStateChanged: Drawing field for new state")
        // Clear board  (draw all cells white)
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                grid[i][j].fill = Paint.valueOf("#FFFFFF")
            }
        }

        Platform.runLater() {
            topLabel?.text = "Name: ${Game.playerName}  LobbyID: "
            topLabel?.textFill = Paint.valueOf(gameState.snakes[0].snakeColor)
        }
        /**
         * Draws all snakes in their colors
         */
        gameState.snakes.forEach {
            if (it.playerLost == false) {
                println("SnakeUI.onGameStateChanged: Drawing head at position x${it.snakeHead.posX} y${it.snakeHead.posY} color:${it.snakeColor}")
                grid[it.snakeHead.posX][it.snakeHead.posY].fill = Paint.valueOf(it.snakeColor)

                it.snakeParts.forEach { part ->
//                println("SnakeUI.onGameStateChanged: Drawing part at position x${part.posX} y${part.posY}")
                    grid[part.posX][part.posY].fill = Paint.valueOf(it.snakeColor)
                }
            }
        }
    }
}