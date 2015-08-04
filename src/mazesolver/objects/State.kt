package mazesolver.objects

import mazesolver.objects.algorithms.SearchAlgorithm
import java.awt.Color
import java.awt.Dimension
import java.util.*
import java.util.concurrent.Executors
import java.util.logging.Logger
import javax.swing.SwingUtilities


class State(logger: Logger) {
    private val logger = logger
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
    public val pathColor: Color = Color.GREEN
    public val grid: Grid = Grid(squaredGridCount, squaredGridCount)
    public val stateChangeListeners : ArrayList<Runnable> = ArrayList()
    var executor = Executors.newFixedThreadPool(1)


    fun run(al: SearchAlgorithm) {
        executor.submit({
            grid.clearPath()
            try {
                val solution = al.execute(grid, logger)
                for (point in solution) {
                    grid.set(point.x, point.y, Grid.Marker.PATH)
                }
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            SwingUtilities.invokeLater({
                for (stateChangeListener in stateChangeListeners) {
                    stateChangeListener.run()
                }
            })
        })
    }

    fun addListener(runnable: Runnable) {
        stateChangeListeners.add(runnable)
    }
}