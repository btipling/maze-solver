package dijkstra.objects.ui

import dijkstra.objects.State
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.BoxLayout
import javax.swing.JPanel


class Canvas(state: State) : JPanel() {
    val state = state
    init {
        setMinimumSize(state.canvasDimension)
        setMaximumSize(state.canvasDimension)
        setPreferredSize(state.canvasDimension)
        setSize(state.canvasDimension)
        setLayout(BoxLayout(this, BoxLayout.LINE_AXIS))
    }
    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        doDrawing(g)
    }

    private fun doDrawing(g: Graphics) {
        val g2d = g as Graphics2D
        val size = getSize()
        g2d.setColor(state.canvasBG)
        val width = size.width;
        val height = size.height;
        g2d.fillRect(0, 0, width, height)

    }
}