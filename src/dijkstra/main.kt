package dijkstra

import dijkstra.dialogs.DialogContainer
import dijkstra.objects.ui.Dialog
import dijkstra.objects.State
import java.awt.*
import java.util.logging.Logger
import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    println("Hello World!");
    SwingUtilities.invokeLater{run()};
}

private val logger = Logger.getLogger("DialogUI")

public fun run () {
    val frame: JFrame = JFrame("FrameDemo")
    val state = State(logger)
    val dialog = Dialog(state, logger)
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val myLabel: Label = Label("Sup")
    val container: Container = frame.getContentPane()
    container.add(myLabel, BorderLayout.CENTER)
    container.add(dialog.getGraphContainer(), BorderLayout.CENTER)
    frame.setMinimumSize(Dimension(500, 200))
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
}
