package gameLogic;

import java.util.*;

class SnakeUI: Application {
    constructor(allSnakes: HashMap<SnakePart, String>) {
        this.allSnakes = allSnakes;
    }

    private val allSnakes: HashMap<SnakePart, String>;
}