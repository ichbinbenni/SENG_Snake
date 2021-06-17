import network.NetworkManager
import tornadofx.App
import tornadofx.launch
import views.MenuView
import views.SnakeUI

/**
 * Main. First function that get called
 */
fun main(args: Array<String>) {
    // Connect to the server
    NetworkManager.connect()
    // Show the menu view
    launch<SnakeApp>(args)
}

class SnakeApp: App(SnakeUI::class)