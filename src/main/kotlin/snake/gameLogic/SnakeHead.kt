package snake.gameLogic

import gamelogic.SnakeDirection
import gamelogic.SnakePart

class SnakeHead(val direction: SnakeDirection, posX: Int, posY: Int): SnakePart(posX, posY) {
}