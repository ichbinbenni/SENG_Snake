package gameLogic;

import java.util.*;

class Snake (var color: String, startX: Int, startY: Int, val direction: String, val allSnakes: HashMap<SnakePart, String>, var gridSize: Int) {
    init {
        this.head = SnakePart(startX, startY);
        this.parts = arrayListOf(this.head);
        this.dead = false;
        allSnakes.put(head, color);
    }

    fun pressedW() {
        if(direction == "north" || direction == "south") {
            return;
        }
        val current: SnakePart;
        val predecessor: SnakePart;
        
        allSnakes.remove(parts.get(parts.size-1));  // funktioniert eventuell nicht, siehe Kommentar checkCollisions()
        for(val i: Int = parts.size-1; i > 0; i--) {
            current = parts.get(i);
            predecessor = parts.get(i-1);
            current.setPosX(predecessor.getPosX());
            current.setPosY(predecessor.getPosY());
        }
        head.setPosY(head.getPosY()-1);  // Zeile bei S, A und D ändern
        checkCollisions();
        allSnakes.put(head, color);

        direction = "north";
    }

    fun pressedS() {
        if(direction == "north" || direction == "south") {
            return;
        }
        
        
        direction = "south";
    }

    fun pressedA() {
        if(direction == "west" || direction == "east") {
            return;
        }
        
        
        direction = "west";
    }

    fun pressedD() {
        if(direction == "west" || direction == "east") {
            return;
        }
        
        
        direction = "east";
    }

    fun moveForward() {

    }

    fun addPart() {

    }

    fun checkCollisions() {
        // Check if Snake hitted the wall
        if(head.getPosX() < 0 || head.getPosX() >= gridSize || head.getPosY() < 0 || head.getPosY() >= gridSize) {
            dead = true;
        }
        // Check if Snake hitted it self or other Snake
        /* Funktioniert wahrscheinlich nicht, da auf referenz geprüft wird 
        -> allSnakes mit forEach durchlaufen und x- und y-Koordinaten prüfen */
        if(allSnakes.containsKey(head)) {
            dead = true;
        }
    }

    fun getDead(): Boolean {
        return this.dead;
    }

    private val dead: Boolean;
    private val color: String;
    private val head: SnakePart;
    private val parts: List<SnakePart>;
    private val direction: String;
    private val gridSize: Int;
    private val allSnakes: HashMap<SnakePart, String>;
}