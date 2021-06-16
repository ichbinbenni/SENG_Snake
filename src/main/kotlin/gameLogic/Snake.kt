package gamelogic

class Snake (private var color: String,
             startX: Int,
             startY: Int,
             private var direction: SnakeDirection,
             private var allSnakes: HashMap<SnakePart, String>) {

    var head: SnakePart = SnakePart(startX, startY)
    private var parts = arrayListOf(this.head)

    init {
        allSnakes[head] = color
        for(i in 0..2) {
            addPart()
        }
    }

    fun pressedW() {
        if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
            return
        }
        moveTail()
        head.posY -= 1
        allSnakes[head] = color

        direction = SnakeDirection.NORTH
    }

    fun pressedS() {
        if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
            return
        }

        moveTail()
        head.posY +=  1
        allSnakes[head] = color
        
        direction = SnakeDirection.SOUTH
    }

    fun pressedA() {
        if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
            return
        }

        moveTail()
        head.posX -= 1
        allSnakes[head] = color
        
        direction = SnakeDirection.WEST
    }

    fun pressedD() {
        if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
            return
        }

        moveTail()
        head.posX += 1
        allSnakes[head] = color
        
        direction = SnakeDirection.EAST
    }

    fun moveForward() {
        moveTail()

        when(direction) {
            SnakeDirection.NORTH -> {
                head.posY -= 1
            }
            SnakeDirection.SOUTH -> {
                head.posY += 1
            }
            SnakeDirection.EAST -> {
                head.posX += 1
            }
            SnakeDirection.WEST -> {
                head.posX -= 1
            }
        }

        allSnakes[head] = color
    }

    fun addPart() {
        var newPart = SnakePart(0, 0)

        when(direction) {
            SnakeDirection.NORTH -> {
                newPart = SnakePart(parts[parts.size-1].posX, parts[parts.size-1].posY+1)
            }
            SnakeDirection.SOUTH -> {
                newPart = SnakePart(parts[parts.size-1].posX, parts[parts.size-1].posY-1)
            }
            SnakeDirection.EAST -> {
                newPart = SnakePart(parts[parts.size-1].posX-1, parts[parts.size-1].posY)
            }
            SnakeDirection.WEST -> {
                newPart = SnakePart(parts[parts.size-1].posX+1, parts[parts.size-1].posY)
            }
        }
        parts.add(newPart)
        allSnakes[newPart] = color
    }

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