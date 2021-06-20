package snake

import snake.network.NetworkManager
import snake.views.MenuView
import tornadofx.App
import tornadofx.launch
import snake.views.SnakeUI

/**
 * Main. First function that get called
 */
fun main(args: Array<String>) {
    // Connect to the server
    NetworkManager.connect()
    // Show the menu view
    launch<SnakeApp>(args)
}

class SnakeApp: App(MenuView::class)