package snake.views;

import javafx.scene.Node
import javafx.scene.paint.Color
import snake.gameLogic.GameStateListener
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.text.Text
import snake.gameLogic.Game
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
        Game.DEBUG_startGame()
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
            println("SnakeUI.onGameStateChanged: Drawing head at position x${it.snakeHead.posX} y${it.snakeHead.posY}")
            grid[it.snakeHead.posX][it.snakeHead.posY].fill = Paint.valueOf(it.color)

            it.snakeParts.forEach { part ->
                println("SnakeUI.onGameStateChanged: Drawing part at position x${part.posX} y${part.posY}")
                grid[part.posX][part.posY].fill = Paint.valueOf(it.color)

            }
        }
    }
}