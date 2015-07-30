package dijkstra.objects.ui

import dijkstra.objects.State
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Stroke
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
        val columnWidth = state.canvasDimension.width/state.grid.columns
        val columnHeight = state.canvasDimension.height/state.grid.rows
        g2d.setColor(state.lineColor)
        for (x: Int in 1..state.grid.columns) {
            val xPos = x * columnWidth
            g2d.drawLine(xPos, 0, xPos, state.canvasDimension.height)
        }
        for (y: Int in 1..state.grid.rows) {
            val yPos = y * columnHeight
            g2d.drawLine(0, yPos, state.canvasDimension.width, yPos)
        }
    }
}