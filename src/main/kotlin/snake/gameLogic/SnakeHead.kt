package snake.gameLogic

import gamelogic.SnakeDirection
import gamelogic.SnakePart

class SnakeHead(private val direction: Int, posX: Int, posY: Int): SnakePart(posX, posY) {
    var directionEnum: SnakeDirection = SnakeDirection.values().firstOrNull { it.code == direction } ?: SnakeDirection.NORTH
}