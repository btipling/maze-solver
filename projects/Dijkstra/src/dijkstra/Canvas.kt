package dijkstra

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel


class Canvas: JPanel() {
    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        doDrawing(g)
    }

    private fun doDrawing(g: Graphics) {
        val g2d = g as Graphics2D
        val size = getSize()
        g2d.setBackground(Color.GREEN)
        g2d.setColor(Color.MAGENTA)
        val width = size.width;
        val height = size.height;
        g2d.fillRect(0, 0, width, height)

    }
}