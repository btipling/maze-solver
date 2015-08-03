package dijkstra.objects

import dijkstra.objects.algorithms.SearchAlgorithm
import java.awt.Color
import java.awt.Dimension
import java.util.*
import java.util.concurrent.Executors


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
    public val stateChangeListeners : ArrayList<Runnable> = ArrayList()
    var executor = Executors.newFixedThreadPool(1)


    fun run(al: SearchAlgorithm) {
        executor.submit({
            val solution = al.execute(this.grid)
            for (point in solution) {
                grid.set(point.x, point.y, Grid.Marker.PATH)
            }
            for (stateChangeListener in stateChangeListeners) {
                stateChangeListener.run()
            }
        })
    }

    fun addListener(runnable: Runnable) {
        stateChangeListeners.add(runnable)
    }
}