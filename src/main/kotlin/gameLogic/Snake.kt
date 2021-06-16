package gameLogic;

class Snake (private var color: String,
             startX: Int,
             startY: Int,
             private var direction: String,
             val allSnakes: HashMap<SnakePart, String>,
             var gridSize: Int) {

    var dead: Boolean
    private var head: SnakePart = SnakePart(startX, startY)
    private var parts: List<SnakePart>

    init {
        this.parts = arrayListOf(this.head)
        this.dead = false
        allSnakes.put(head, color)
    }

    fun pressedW() {
        if(direction == "north" || direction == "south") {
            return;
        }
        val current: SnakePart
        val predecessor: SnakePart
        
        allSnakes.remove(parts.get(parts.size-1));  // funktioniert eventuell nicht, siehe Kommentar checkCollisions()
//        for(val i: Int = parts.size-1; i > 0; i--) {
//            current = parts.get(i);
//            predecessor = parts.get(i-1);
//            current.setPosX(predecessor.getPosX());
//            current.setPosY(predecessor.getPosY());
//        }
        head.posY = head.posY - 1  // Zeile bei S, A und D ändern
        allSnakes.put(head, color)

        direction = "north";
    }

    fun pressedS() {
        if(direction == "north" || direction == "south") {
            return
        }
        
        
        direction = "south"
    }

    fun pressedA() {
        if(direction == "west" || direction == "east") {
            return
        }
        
        
        direction = "west"
    }

    fun pressedD() {
        if(direction == "west" || direction == "east") {
            return
        }
        
        
        direction = "east"
    }

    fun moveForward() {

    }

    fun addPart() {

    }
}