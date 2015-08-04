package mazesolver.objects.algorithms

import mazesolver.objects.Grid
import mazesolver.objects.Point
import java.util.logging.Logger


public interface SearchAlgorithm {
    fun execute(grid: Grid, logger: Logger): List<Point>
}