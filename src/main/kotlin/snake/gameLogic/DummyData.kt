package snake.gameLogic

import gamelogic.Snake
import gamelogic.SnakeDirection
import gamelogic.SnakePart

object DummyData {

    val positionsSnakeA = arrayListOf(
        arrayListOf(
            SnakePart(0,0),
            SnakePart(0,1),
            SnakePart(0,2)
        ),
        arrayListOf(
            SnakePart(0,1),
            SnakePart(0,2),
            SnakePart(1,2)
        ),
        arrayListOf(
            SnakePart(0,2),
            SnakePart(1,2),
            SnakePart(2,2)
        ),
        arrayListOf(
            SnakePart(1,2),
            SnakePart(2,2),
            SnakePart(3,2)
        ),
        arrayListOf(
            SnakePart(2,2),
            SnakePart(3,2),
            SnakePart(4,2)
        ),
        arrayListOf(
            SnakePart(3,2),
            SnakePart(4,2),
            SnakePart(5,2)
        )
    )
    val gameStates = arrayListOf(
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        1,
                        positionsSnakeA[0][2].posX,
                        positionsSnakeA[0][2].posY
                    ),
                    positionsSnakeA[0].subList(0, 2),
                    false
                )
            ),
            true
        ),
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        1,
                        positionsSnakeA[1][2].posX,
                        positionsSnakeA[1][2].posY
                    ),
                    positionsSnakeA[1].subList(0, 2),
                    false
                )
            ),
            true
        ),
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        2,
                        positionsSnakeA[2][2].posX,
                        positionsSnakeA[2][2].posY
                    ),
                    positionsSnakeA[2].subList(0, 2),
                    false
                )
            ),
            true
        ),
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        2,
                        positionsSnakeA[3][2].posX,
                        positionsSnakeA[3][2].posY
                    ),
                    positionsSnakeA[3].subList(0, 2),
                    false
                )
            ),
            true
        ),
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        2,
                        positionsSnakeA[4][2].posX,
                        positionsSnakeA[4][2].posY
                    ),
                    positionsSnakeA[4].subList(0, 2),
                    false
                )
            ),
            true
        ),
        GameState(
            listOf(
                Snake(
                    "id1", "#0000ff", SnakeHead(
                        2,
                        positionsSnakeA[5][2].posX,
                        positionsSnakeA[5][2].posY
                    ),
                    positionsSnakeA[5].subList(0, 2),
                    true
                )
            ),
            false
        )
    )
}