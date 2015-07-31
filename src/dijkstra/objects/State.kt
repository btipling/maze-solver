package dijkstra.objects

import java.awt.Color
import java.awt.Dimension


class State {
    public val squaredSize: Int = 500;
    public val squaredGridCount: Int = 25;
    public val boxSize: Int = squaredSize/squaredGridCount
    public val canvasDimension: Dimension = Dimension(squaredSize, squaredSize)
    public val canvasBG: Color = Color.WHITE;
    public val lineColor: Color = Color.DARK_GRAY;
    public val wallColor: Color = Color.DARK_GRAY;
    public val startColor: Color = Color.RED;
    public val endColor: Color = Color.BLUE;
    public val overColor: Color = Color.GRAY;
    public val grid: Grid = Grid(squaredGridCount, squaredGridCount)
}