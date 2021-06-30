package snake.views;

import gamelogic.SnakeDirection
import javafx.scene.Node
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import snake.gameLogic.GameStateListener
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text
import snake.gameLogic.Game
import snake.gameLogic.Game.changePlayerDirection
import snake.gameLogic.GameState
import tornadofx.View
import tornadofx.colorpicker
import tornadofx.pane
import tornadofx.text

class SnakeUI() : View("Snake-Multiplayer"), GameStateListener {

    // MARK: - Constants
    private val RECTANGLE_SIZE = 20.0
    private val BOARD_SIZE = 30

    // MARK: - Vars
    private var grid = arrayOf<Array<Rectangle>>()

    // MARK: - UI-Elements
    override val root = pane {
        addEventFilter(KeyEvent.KEY_PRESSED) { event ->
            println("pressed: " + event.code)
            when(event.code) {
                KeyCode.UP, KeyCode.W -> changePlayerDirection("DUMMY NAME HELLO?????", SnakeDirection.NORTH)
                KeyCode.DOWN, KeyCode.S -> changePlayerDirection("DUMMY NAME HELLO?????", SnakeDirection.SOUTH)
                KeyCode.LEFT, KeyCode.A -> changePlayerDirection("DUMMY NAME HELLO?????", SnakeDirection.WEST)
                KeyCode.RIGHT, KeyCode.D -> changePlayerDirection("DUMMY NAME HELLO?????", SnakeDirection.EAST)
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
                root.add(rect)
            }
            grid += array
        }

        Game.gameStateListener = this
        if (Game.gameStates.isEmpty() == false) {
            Game.gameStates.lastOrNull()?.let {
                onGameStateChanged(it)
            }
        }
    }

    override fun onDock() {
        root.requestFocus()
    }


    override fun onGameStateChanged(gameState: GameState) {
        println("SnakeUI.onGameStateChanged: Drawing field for new state")
        // update UI
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                grid[i][j].fill = Paint.valueOf("#FFFFFF")
            }
        }

        /**
         * Draws all snakes in their colors
         *
         */
        gameState.snakes.forEach {
            println("SnakeUI.onGameStateChanged: Drawing head at position x${it.snakeHead.posX} y${it.snakeHead.posY} color:${it.snakeColor}")
            grid[it.snakeHead.posX][it.snakeHead.posY].fill = Paint.valueOf(it.snakeColor)

            it.snakeParts.forEach { part ->
//                println("SnakeUI.onGameStateChanged: Drawing part at position x${part.posX} y${part.posY}")
                grid[part.posX][part.posY].fill = Paint.valueOf(it.snakeColor)
            }
        }

    }
}