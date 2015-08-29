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
    public val canvasBG: Color = Color.WHITE
    public val lineColor: Color = Color(213, 222, 217)
    public val wallColor: Color = Color(85, 98, 112)
    public val startColor: Color = Color(255, 107, 107)
    public val endColor: Color = Color(78, 205, 196)
    public val overColor: Color = Color(194, 200, 193)
    public val pathColor: Color = Color(199, 244, 100)
    public val visitedColor: Color = Color(255, 255, 208)
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
                    statusListener("Time: %d ms. Distance: %d. Algorithm: %s".format(
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