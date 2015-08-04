package dijkstra.objects.algorithms

import dijkstra.objects.Grid
import dijkstra.objects.Point
import java.util.logging.Logger


public interface SearchAlgorithm {
    fun execute(grid: Grid, logger: Logger): List<Point>
}