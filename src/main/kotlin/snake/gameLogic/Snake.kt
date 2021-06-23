package gamelogic

import snake.gameLogic.SnakeHead

/**
 * Class to control Snake and add SnakeParts
 */
class Snake (
    val snakeID: String,
    val color: String,
    val snakeHead: SnakeHead,
    val snakeParts: List<SnakePart>,
    playerLost: Boolean)