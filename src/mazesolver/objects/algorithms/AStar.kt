package mazesolver.objects.algorithms

import mazesolver.objects.Grid
import mazesolver.objects.Point
import mazesolver.objects.State
import java.util.*
import java.util.logging.Logger


public class AStar : Dijkstra() {
    private var endPoint: Point? = null

    inner class Node(marker: Grid.Marker, x: Int, y: Int) : Dijkstra.Node(marker, x, y) {
        public override var totalDistance: Double = Double.POSITIVE_INFINITY
        set (distance: Double) {
            if (endPoint == null) {
                throw Exception("There must be an endpoint!")
            }
            val ep = endPoint!!
            val manhattanDistance = Math.abs(this.x - ep.x) + Math.abs(this.y - ep.y)
            $totalDistance = distance + manhattanDistance
        }
    }

    protected override fun createNodes(grid: Grid): ArrayList<Dijkstra.Node> {
        val nodes = ArrayList<Dijkstra.Node>()
        for (x in 0..(grid.columns - 1)) {
            for (y in 0..(grid.rows - 1)) {
                val marker = grid.get(x, y)
                if (marker.equals(Grid.Marker.END)) {
                    endPoint = Point(x, y)
                }
                nodes.add(Node(grid.get(x, y), x, y))
            }
        }
        if (endPoint == null) {
            throw Exception("There must be an endpoint!")
        }
        return nodes
    }

    override fun getName(): String {
        return "A*"
    }
}