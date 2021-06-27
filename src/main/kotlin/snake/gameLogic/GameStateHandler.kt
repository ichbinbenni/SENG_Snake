package snake.gameLogic

import gamelogic.SnakeDirection

interface GameStateHandler {

    fun changePlayerDirection(playerName: String,direction:SnakeDirection);
}
