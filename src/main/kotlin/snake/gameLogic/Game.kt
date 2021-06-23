package snake.gameLogic

import java.util.*

object Game {
    var gameStateListener: GameStateListener? = null
    var gameStates = arrayListOf<GameState>()


    /**
     * Deletes the current game and resets all states
     */
    fun reset() {
        gameStates = arrayListOf<GameState>()
    }

    /**
     * Sets the latest game states and notifies the listener
     */
    fun add(gameState: GameState) {
        gameStates.add(gameState)
        gameStateListener?.onGameStateChanged(gameState)
    }

    fun DEBUG_startGame() {
        var i = 0

        fun runTimer() {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    gameStateListener?.onGameStateChanged(DummyData.gameStates[i])
                    i+=1
                    if (i > 4) {
                        i = 4
                    }
                    runTimer()
                }
            }, 3000)
        }
        runTimer()

    }
}