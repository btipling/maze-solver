package mazesolver.objects.algorithms

import mazesolver.objects.Grid
import mazesolver.objects.Point
import mazesolver.objects.State
import java.util.*
import java.util.logging.Logger


public class Dijkstra : SearchAlgorithm {

    class Node(marker: Grid.Marker, x: Int, y: Int) : Comparable<Node> {
        public var totalDistance: Double = Double.POSITIVE_INFINITY
        public val marker: Grid.Marker = marker
        private val edges: ArrayList<Node> = ArrayList()
        public val x: Int = x
        public val y: Int = y

        override fun compareTo(other: Node): Int {
            if (other.totalDistance.equals(totalDistance)) {
                return 0
            }
            return if (totalDistance < other.totalDistance) -1 else 1
        }

        public fun add(node: Node) {
            edges.add(node)
            Collections.sort(edges)
        }

        public fun getEdges(): List<Node> {
            return ArrayList(edges)
        }
    }

    override fun execute(state: State): List<Point> {
        state.getLogger().info("Starting search")
        val nodes = createNodes(state.grid)
        val startNode = createNodeGraph(nodes, state.grid) ?: return ArrayList<Point>()
        val unvisited = ArrayList<Node>(nodes)
        return findPath(startNode, unvisited)
    }

    private fun createNodes(grid: Grid): ArrayList<Node> {
        val nodes = ArrayList<Node>()
        for (x in 0..(grid.columns - 1)) {
            for (y in 0..(grid.rows - 1)) {
                nodes.add(Node(grid.get(x, y), x, y))
            }
        }
        return nodes
    }

    private fun getIndexFromPoint(point: Point, grid: Grid): Int {
        return point.x * grid.rows + point.y
    }

    private fun createNodeGraph(nodes: ArrayList<Node>, grid: Grid): Node? {
        var start: Node? = null
        for (i in 0..nodes.size() - 1) {
            val node = nodes[i]
            if (node.x != 0) {
                node.add(nodes[getIndexFromPoint(Point(node.x - 1, node.y), grid)])
            }
            if (node.x < grid.columns - 1) {
                node.add(nodes[getIndexFromPoint(Point(node.x + 1, node.y), grid)])
            }
            if (node.y != 0) {
                node.add(nodes[getIndexFromPoint(Point(node.x, node.y - 1), grid)])
            }
            if (node.y < grid.rows - 1) {
                node.add(nodes[getIndexFromPoint(Point(node.x, node.y + 1), grid)])
            }
            if (node.marker.equals(Grid.Marker.START)) {
                start = node
            }
        }
        return start
    }

    private fun findPath(startNode: Node, unvisited: ArrayList<Node>): List<Point> {
        val path = ArrayList<Point>()
        startNode.totalDistance = 0.0
        Collections.sort(unvisited)
        while (!unvisited.isEmpty() && !unvisited.first().totalDistance.equals(Double.POSITIVE_INFINITY)) {
            val next = unvisited.first()
            visitNode(next)
            if (next.marker.equals(Grid.Marker.END)) {
                traceback(next, path)
                break
            }
            unvisited.remove(next)
            Collections.sort(unvisited)
        }
        return path
    }

    private fun traceback(node: Node, path: ArrayList<Point>) {
        if (node.marker.equals(Grid.Marker.START)) {
            return
        }
        var currentMinDistance = Double.POSITIVE_INFINITY
        var currentMinNode: Node? = null
        for (edge in node.getEdges()) {
            if (edge.totalDistance < currentMinDistance) {
                currentMinDistance = edge.totalDistance
                currentMinNode = edge
            }
        }
        if (currentMinNode == null) {
            throw(Throwable("currentMindNode should never be null!"))
        }
        if (!node.marker.equals(Grid.Marker.END)) {
            path.add(Point(node.x, node.y))
        }
        traceback(currentMinNode, path)
    }

    private fun visitNode(node: Node) {
        val currentDistance = node.totalDistance + 1
        for (edge in node.getEdges()) {
            if (edge.marker.equals(Grid.Marker.WALL)) {
                continue
            }
            if (edge.totalDistance > currentDistance) {
                edge.totalDistance = currentDistance
            }
        }
    }

    override fun getName(): String {
        return "Dijkstra"
    }
}