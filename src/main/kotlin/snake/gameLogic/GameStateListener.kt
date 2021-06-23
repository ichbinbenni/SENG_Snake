package snake.gameLogic

/**
 * Interface that needs to be implemented by every class that needs to
 * subscribe to GamestateChanges
 *
 */

interface GameStateListener {
    fun onGameStateChanged(gameState: GameState)
}