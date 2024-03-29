package snake.gameLogic

import gamelogic.SnakeDirection
import snake.network.NetworkGameBridge
import java.util.*

object Game: GameStateHandler {
    var gameStateListener: GameStateListener? = null
    var gameStates = arrayListOf<GameState>()
    var playerName: String = "Need to be set"
    var lobbyCode: String = "Need to be set"
    // gamesize
    //


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
                    if (i > 5) {
                        i = 5
                    }
                    runTimer()
                }
            }, 1000)
        }
        runTimer()

    }

    override fun changePlayerDirection(playerName: String, direction: SnakeDirection) {
        NetworkGameBridge.changeDirection(direction)
    }
}