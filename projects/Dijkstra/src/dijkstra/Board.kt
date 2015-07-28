package dijkstra

import java.awt.Canvas


public class Board () {
    val canvas: Canvas = Canvas()
    public constructor(dialog: DialogUI) : this() {
        dialog.getBoardContainer().add(canvas);
    }
}