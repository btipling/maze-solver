package dijkstra

import java.awt.Color
import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.border.LineBorder


public class Board () {
    val canvas: Canvas = Canvas()
    public constructor(dialog: DialogUI) : this() {
        val d = Dimension(500, 500)
        canvas.setMinimumSize(d)
        canvas.setMaximumSize(d)
        canvas.setPreferredSize(d)
        canvas.setVisible(true)
        canvas.setSize(d)
        canvas.setLayout(BoxLayout(canvas, BoxLayout.LINE_AXIS))
        dialog.getBoardContainer().add(canvas);
        dialog.refresh()
    }
}