package snake.views;

import snake.gameLogic.GameStateListener
import snake.gameLogic.SnakeGame
import snake.gameLogic.SnakeGame.BOARD_SIZE
import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import tornadofx.View
import tornadofx.pane

class SnakeUI() : View("Snake-Multiplayer"), GameStateListener {

    var grid = arrayOf<Array<Rectangle>>()
    val rectangleSize = 20.0

    override val root = pane {
    }

    /*Initializes the GameBoard Grid, with multiple Rectangles, each Rectangle represents 1 Field of the Array*/

    init {
        for (i in 0..BOARD_SIZE) {
            var array = arrayOf<Rectangle>()
            for (j in 0..BOARD_SIZE) {
                var rect = Rectangle(i * rectangleSize, j * rectangleSize, rectangleSize, rectangleSize);
                array += rect
                root.add(rect)
            }
            grid += array
        }

        SnakeGame.subscribeGameState(this)

        onGameStateChanged()
    }

    override fun onGameStateChanged() {
        // update UI
        for (i in 0..BOARD_SIZE) {
            for (j in 0..BOARD_SIZE) {
                grid[i][j].fill = Paint.valueOf("#000000")
            }
        }


        SnakeGame.snakes.forEach {
            it.allSnakes.forEach { part, color ->
                grid[part.posX][part.posY].fill = Paint.valueOf(color)
            }
        }
    }
}