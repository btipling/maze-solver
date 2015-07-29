package dijkstra.objects

import java.awt.Color
import java.awt.Dimension


class State {
    public val canvasDimension: Dimension = Dimension(500, 500)
    public val canvasBG: Color = Color.WHITE;
    public val grid: Grid = Grid(500, 500)
}