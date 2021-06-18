package views;

import javafx.scene.paint.Paint
import javafx.scene.shape.Rectangle
import tornadofx.View
import tornadofx.pane

class SnakeUI : View("Snake-Multiplayer") {

    var grid = arrayOf<Array<Rectangle>>()

    override val root = pane{
    }

    init {
        for (i in 0..10) {
            var array = arrayOf<Rectangle>()
            for (j in 0..10) {
                var rect = Rectangle(i * 20.0, j * 20.0, 20.0, 20.0);
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