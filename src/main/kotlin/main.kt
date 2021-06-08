
import javafx.application.Application.launch
import network.NetworkManager
import views.MenuView

/**
 * Main. First function that get called
 */
fun main(args: Array<String>) {
    // Show the menu view
    launch(MenuView::class.java)
    NetworkManager.connect()
}