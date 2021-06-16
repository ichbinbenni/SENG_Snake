package gameLogic;

class SnakePart {
    constructor(posX: Int, posY: Int) {
        this.posX = posX;
        this.posY = posY;
    }

    fun getPosX(): Int {
        return this.posX;
    }

    fun getPosY(): Int {
        return this.posY;
    }

    fun setPosX(posX: Int) {
        this.posX = posX;
    }

    fun setPosY(posY: Int) {
        this.posY = posY;
    }

    private val posX: Int;
    private val posY: Int;
}