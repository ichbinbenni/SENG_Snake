package gamelogic

/**
 * Enum to manage the directions of the Snake
 */
enum class SnakeDirection(val code: Int) {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3)
}