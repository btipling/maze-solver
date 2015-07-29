package dijkstra

import dijkstra.dialogs.DialogContainer
import java.awt.BorderLayout
import java.awt.Container
import java.awt.Dimension
import java.awt.Label
import java.util.logging.Logger
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
    val graphContainer = DialogUI()
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val myLabel: Label = Label("Sup")
    val container: Container = frame.getContentPane()
    container.add(myLabel, BorderLayout.CENTER)
    container.add(graphContainer.getGraphContainer(), BorderLayout.CENTER)
    frame.setMinimumSize(Dimension(500, 200))
    val board = Board(graphContainer)
    frame.pack()
    frame.setVisible(true)
}
