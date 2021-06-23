package snake.gameLogic

import gamelogic.Snake
import gamelogic.SnakeDirection
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.scheduleAtFixedRate

/**
 * Object that handles the entire Gamelogic
 * and every important Gamevalues
 * Timer should prob. be implemented by the Serverhost of the Game
 * here just for an example look
 */

object SnakeGame {
    const val BOARD_SIZE = 30

    private val gameStateListeners: MutableList<GameStateListener> = ArrayList()
    val snakes: MutableList<Snake> = ArrayList()

    init {
        startGame()
    }


    fun startGame() {
        // Start game

        for (i in 0..4) {
            // Snake für jeden spieler erstellen
            snakes.add(Snake("#FF0000", 3, 4, SnakeDirection.SOUTH, HashMap()))
            snakes.add(Snake("#FFFF00", 10, 12, SnakeDirection.WEST, HashMap()))
        }

        gameStateListeners.forEach {
            it.onGameStateChanged()
        }

        /**
         * Example Servertick Timer to simulate Gamechanges
         *
         *
         */

        val timer = Timer()
        timer.scheduleAtFixedRate(1000, 1000) {
            snakes.forEach { snake ->
                snake.moveForward()
                notifyGameState()
            }
        }
    }



    private fun notifyGameState() {
        gameStateListeners.forEach {
            it.onGameStateChanged()
        }
    }

    fun subscribeGameState(listener: GameStateListener) {
        gameStateListeners.add(listener)
    }
}