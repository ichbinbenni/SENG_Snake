package gamelogic

class Snake (private var color: String,
             startX: Int,
             startY: Int,
             //private var direction: SnakeDirection,
             private var direction: String,
             private var allSnakes: HashMap<SnakePart, String>) {

    // var dead = false
    var head: SnakePart = SnakePart(startX, startY)
    private var parts = arrayListOf(this.head)

    init {
        allSnakes[head] = color
        for(i in 0..2) {
            addPart()
        }
    }

    fun pressedW() {
        //if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
        if(direction == "north" || direction == "south") {
            return
        }
        moveTail()
        head.posY -= 1
        allSnakes[head] = color

        //direction = SnakeDirection.NORTH
        direction = "north"
    }

    fun pressedS() {
        //if(direction == SnakeDirection.NORTH || direction == SnakeDirection.SOUTH) {
        if(direction == "north" || direction == "south") {
            return
        }

        moveTail()
        head.posY +=  1
        allSnakes[head] = color
        
        //direction = SnakeDirection.SOUTH
        direction = "south"
    }

    fun pressedA() {
        //if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
        if(direction == "east" || direction == "west") {
            return
        }

        moveTail()
        head.posX -= 1
        allSnakes[head] = color
        
        //direction = SnakeDirection.WEST
        direction = "west"
    }

    fun pressedD() {
        //if(direction == SnakeDirection.WEST || direction == SnakeDirection.EAST) {
        if(direction == "east" || direction == "west") {
            return
        }

        moveTail()
        head.posX += 1
        allSnakes[head] = color
        
        //direction = SnakeDirection.EAST
        direction = "east"
    }

    fun moveForward() {
        moveTail()

        when(direction) {
            //SnakeDirection.NORTH -> {
            "north" -> {
                head.posY -= 1
            }
            //SnakeDirection.SOUTH -> {
            "south" -> {
                head.posY += 1
            }
            //SnakeDirection.EAST -> {
            "east" -> {
                head.posX += 1
            }
            //SnakeDirection.WEST -> {
            "west" -> {
                head.posX -= 1
            }
        }

        allSnakes[head] = color
    }

    fun addPart() {
        var newPart = SnakePart(0, 0)

        when(direction) {
            "north" -> {
                newPart = SnakePart(parts[parts.size-1].posX, parts[parts.size-1].posY+1)
            }
            "south" -> {
                newPart = SnakePart(parts[parts.size-1].posX, parts[parts.size-1].posY-1)
            }
            "east" -> {
                newPart = SnakePart(parts[parts.size-1].posX-1, parts[parts.size-1].posY)
            }
            "west" -> {
                newPart = SnakePart(parts[parts.size-1].posX+1, parts[parts.size-1].posY)
            }
        }
        parts.add(newPart)
        allSnakes[newPart] = color
    }

    private fun moveTail() {
        var current: SnakePart
        var predecessor: SnakePart

        allSnakes.remove(parts[parts.size-1])  // funktioniert eventuell nicht, wegen Suche nach Referenz
        for(i in (parts.size-1)..1) {
            current = parts[i]
            predecessor = parts[i-1]
            current.posX = predecessor.posX
            current.posY = predecessor.posY
        }
    }
}