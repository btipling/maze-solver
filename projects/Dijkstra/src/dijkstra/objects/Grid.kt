package dijkstra.objects


class Grid(columns: Int, rows: Int) {
    public val columns: Int = columns
    public val rows: Int = rows
    private val grid = Array<Array<Marker>>(columns, {Array<Marker>(rows, {Marker.DEFAULT}) })

    public enum class Marker {
        DEFAULT, WALL, OVER, START, END
    }

    public fun get(x: Int, y: Int) : Marker {
        return grid[x][y]
    }
    public fun set(x: Int, y: Int, value: Marker) {
        grid[x][y] = value
    }
}