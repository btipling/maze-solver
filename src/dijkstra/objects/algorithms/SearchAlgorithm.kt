package dijkstra.objects.algorithms

import dijkstra.objects.Grid
import dijkstra.objects.Point


public interface SearchAlgorithm {
    fun execute(grid: Grid): List<Point>
}