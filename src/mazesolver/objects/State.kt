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
    public val squaredSize: Int = 600;
    public val squaredGridCount: Int = 30;
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
    public val statusChangeListeners : ArrayList<((String) -> Unit)> = ArrayList()
    var executor = Executors.newFixedThreadPool(1)


    fun run(al: SearchAlgorithm) {
        val self = this
        val startTime: Long = System.currentTimeMillis()
        var distance = 0
        executor.submit({
            grid.clearPath()
            try {
                val solution = al.execute(self)
                for (point in solution) {
                    grid.set(point.x, point.y, Grid.Marker.PATH)
                }
                distance = solution.size()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            SwingUtilities.invokeLater({
                for (stateChangeListener in stateChangeListeners) {
                    stateChangeListener.run()
                }
                val endTime: Long = System.currentTimeMillis()
                for (statusListener in statusChangeListeners) {
                    statusListener(java.lang.String.format("Time: %d ms. Distance: %d. Algorithm: %s",
                            endTime - startTime, distance, al.getName()))
                }
            })
        })
    }

    public fun getLogger(): Logger {
        return logger
    }

    fun addChangeListener(runnable: Runnable) {
        stateChangeListeners.add(runnable)
    }

    fun addStatusListener(statusListener: (String) -> Unit) {
        statusChangeListeners.add(statusListener)
    }
}