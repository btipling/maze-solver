package mazesolver.objects.algorithms

import mazesolver.objects.Grid
import mazesolver.objects.Point
import mazesolver.objects.State
import java.util.logging.Logger


public interface SearchAlgorithm {
    fun execute(state: State): List<Point>
    fun getName(): String
}