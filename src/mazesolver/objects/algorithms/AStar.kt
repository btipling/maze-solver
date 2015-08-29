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
        public var projectedDistance: Double = Double.NEGATIVE_INFINITY
        get() {
            if (this.totalDistance.equals(Double.POSITIVE_INFINITY)) {
                return this.totalDistance;
            }
            if ($projectedDistance > Double.NEGATIVE_INFINITY) {
                return $projectedDistance
            }
            // Haven't calculated projected distance yet, doing it now, and only once.
            if (endPoint == null) {
                throw Exception("There must be an endpoint!")
            }
            val ep = endPoint!!
            val manhattanDistance = Math.abs(this.x - ep.x) + Math.abs(this.y - ep.y)
            $projectedDistance = totalDistance + manhattanDistance
            return $projectedDistance
        }

        override fun compareTo(other: Dijkstra.Node): Int {
            val n = other as Node
            if (n.projectedDistance.equals(this.projectedDistance)) {
                return 0
            }
            return if (this.projectedDistance < other.projectedDistance) -1 else 1
        }

        fun getNodeDistanceToEnd(node: Dijkstra.Node) : Double {
            if (endPoint == null) {
                throw Exception("There must be an endpoint!")
            }
            val ep = endPoint!!
            if (node.totalDistance.equals(Double.POSITIVE_INFINITY)) {
                return node.totalDistance;
            }
            val manhattanDistance = Math.abs(node.x - ep.x) + Math.abs(node.y - ep.y)
            return node.totalDistance + manhattanDistance
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