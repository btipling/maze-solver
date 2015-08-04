package dijkstra.objects.algorithms

import dijkstra.objects.Grid
import dijkstra.objects.Point
import java.util.*


public class Dijkstra : SearchAlgorithm {
    class Node(marker: Grid.Marker) : Comparable<Node> {
        public var totalDistance: Double = Double.POSITIVE_INFINITY
        public val marker: Grid.Marker = marker
        public val edges: MutableSet<Node> = HashSet()

        override fun compareTo(other: Node): Int {
            if (other.totalDistance.equals(totalDistance)) {
                return 0
            }
            return if (totalDistance < other.totalDistance) -1 else 1
        }
    }
    override fun execute(grid: Grid): List<Point> {
        val nodes = createNodes(grid)
        val startNode = createNodeGraph(nodes, grid) ?: return ArrayList<Point>()
        val unvisited = nodesToSet(nodes)
        return findPath(startNode, unvisited, grid)
    }

    private fun createNodes(grid: Grid): ArrayList<Node> {
        val nodes = ArrayList<Node>()
        for (i in 0..(grid.columns-1)) {
            for (j in 0..(grid.rows-1)) {
                nodes.add(Node(grid.get(i, j)))
            }
        }
        return nodes
    }

    private fun getIndexFromPoint(point: Point, grid: Grid): Int {
        return point.y * grid.rows + point.x
    }

    private fun getPointFromIndex(index: Int, grid: Grid): Point {
        return Point(index/grid.rows, index % grid.rows)
    }

    private fun createNodeGraph(nodes: ArrayList<Node>, grid: Grid): Node? {
        var start: Node? = null
        for (i in 0..nodes.size()) {
            val node = nodes[i]
            val current = getPointFromIndex(i, grid)
            if (current.x != 0) {
                node.edges.add(nodes[getIndexFromPoint(Point(current.x - 1, current.y), grid)])
            }
            if (current.x < grid.columns - 1) {
                node.edges.add(nodes[getIndexFromPoint(Point(current.x + 1, current.y), grid)])
            }
            if (current.y != 0) {
                node.edges.add(nodes[getIndexFromPoint(Point(current.x, current.y - 1), grid)])
            }
            if (current.y < grid.rows - 1) {
                node.edges.add(nodes[getIndexFromPoint(Point(current.x, current.y + 1), grid)])
            }
            if (node.marker.equals(Grid.Marker.START)) {
                start = node
            }
        }
        return start
    }

    private fun nodesToSet(nodes: ArrayList<Node>): TreeSet<Node> {
        return TreeSet<Node>(nodes)
    }

    private fun findPath(startNode: Node, unvisited: TreeSet<Node>, grid: Grid): List<Point> {
        val path = ArrayList<Point>()
        return path
    }
}