
import javafx.application.Application.launch
import network.NetworkManager
import views.MenuView

/**
 * Main. First function that get called
 */
fun main(args: Array<String>) {
    // Connect to the server
    NetworkManager.connect()
    // Show the menu view
    launch(MenuView::class.java)
}