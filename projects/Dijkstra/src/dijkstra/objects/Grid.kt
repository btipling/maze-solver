package dijkstra.objects


class Grid(columns: Int, rows: Int) {
    public val columns: Int = columns
    public val rows: Int = rows
    private val grid = Array<Array<Marker>>(columns, {Array<Marker>(rows, {Marker.DEFAULT}) })

    public enum class Marker {
        DEFAULT, WALL, OVER, START, END
    }

    init {
        grid[0][0] = Marker.START
        val col = grid.last()
        col.set(col.size()-1, Marker.END)
    }

    public fun get(x: Int, y: Int) : Marker {
        return grid[x][y]
    }
    public fun set(x: Int, y: Int, value: Marker) {
        val currentValue = grid[x][y]
        if (value == Marker.DEFAULT || value == Marker.WALL) {
            if (currentValue == Marker.START || currentValue == Marker.END) {
                return
            }
        }
        grid[x][y] = value
    }
}