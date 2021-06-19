package views;

import gamelogic.Snake
import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import tornadofx.View
import tornadofx.pane

class SnakeUI(rectangleSize: Double, boardSize:Int, gameLogic: Snake) : View("Snake-Multiplayer") {

    var grid = arrayOf<Array<Rectangle>>()
    val rectangleSize = rectangleSize
    val boardSize = boardSize
    val gameLogic = gameLogic


    override val root = pane{
    }

    /*Initializes the GameBoard Grid, with multiple Rectangles, each Rectangle represents 1 Field of the Array*/

    init {
        for (i in 0..boardSize) {
            var array = arrayOf<Rectangle>()
            for (j in 0..boardSize) {
                var rect = Rectangle(i * rectangleSize, j * rectangleSize, rectangleSize, rectangleSize);
                array += rect
                root.add(rect)
            }
            grid += array
        }




        // Get HashMap
        grid[2][3].fill = Paint.valueOf("#FF0000");
        grid[2][4].fill = Paint.valueOf("#FF0000");
        grid[2][5].fill = Paint.valueOf("#FF0000");

        grid[7][6].fill = Paint.valueOf("#00FF00");
        grid[7][4].fill = Paint.valueOf("#00FF00");
        grid[7][5].fill = Paint.valueOf("#00FF00");
    }
}