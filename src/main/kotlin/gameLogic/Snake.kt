package gamelogic

/**
 * Class to control Snake and add SnakeParts
 */
class Snake (private var color: String,
             startX: Int,
             startY: Int,
             private var direction: SnakeDirection,
             private var allSnakes: HashMap<SnakePart, String>) {

    var head: SnakePart = SnakePart(startX, startY)  // Head of Snake gets shifted 3 Blocks depending on starting direction
    private var parts = arrayListOf(this.head)

    init {
        allSnakes[head] = color
        for(i in 0..2) {  // Startlength is 3 SnakeParts + head
            addPart()
        }
    }

    /**
     * Gets called when player presses 'w'
     * @return when direction is not valid
     */
    fun pressedW() {
        if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
            return
        }
        moveTail()
        head.posY -= 1
        allSnakes[head] = color

        direction = SnakeDirection.NORTH
    }

    /**
     * Gets called when player presses 's'
     * @return when direction is not valid
     */
    fun pressedS() {
        if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
            return
        }

        moveTail()
        head.posY +=  1
        allSnakes[head] = color
        
        direction = SnakeDirection.SOUTH
    }

    /**
     * Gets called when player presses 'a'
     * @return when direction is not valid
     */
    fun pressedA() {
        if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
            return
        }

        moveTail()
        head.posX -= 1
        allSnakes[head] = color
        
        direction = SnakeDirection.WEST
    }

    /**
     * Gets called when player presses 'd'
     * @return when direction is not valid
     */
    fun pressedD() {
        if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
            return
        }

        moveTail()
        head.posX += 1
        allSnakes[head] = color
        
        direction = SnakeDirection.EAST
    }

    /**
     * Move Snake one Block in current Direction
     */
    fun moveForward() {
        moveTail()

        when(direction) {
            SnakeDirection.NORTH -> head.posY -= 1
            SnakeDirection.SOUTH -> head.posY += 1
            SnakeDirection.EAST -> head.posX += 1
            SnakeDirection.WEST -> head.posX -= 1
        }

        allSnakes[head] = color
    }

    /**
     * Move Snake one Block in current Direction and add SnakePart to tail
     */
    fun addPart() {
        val newPart = SnakePart(parts[parts.size-1].posX, parts[parts.size-1].posY)

        moveForward()
        parts.add(newPart)
        allSnakes[newPart] = color
    }

    /**
     * Give every SnakePart the coordinate of his predecessor
     */
    private fun moveTail() {
        var current: SnakePart
        var predecessor: SnakePart

        for(i in parts.size-1 downTo 1) {
            current = parts[i]
            predecessor = parts[i-1]
            current.posX = predecessor.posX
            current.posY = predecessor.posY
        }
    }
}