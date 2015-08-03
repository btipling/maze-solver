package dijkstra.objects


class Grid(columns: Int, rows: Int) {
    public val columns: Int = columns
    public val rows: Int = rows
    private val grid = Array<Array<Marker>>(columns, {Array<Marker>(rows, {Marker.DEFAULT}) })
    private var startPos: MarkerPos? = null
    private var endPos: MarkerPos? = null

    public enum class Marker {
        DEFAULT, WALL, OVER, START, END, PATH
    }

    private class MarkerPos(x: Int, y: Int) {
        public val x: Int = x
        public val y: Int = y
    }

    init {
        set(0, 0, Marker.START)
        set(columns - 1, rows - 1, Marker.END)
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
        if (value == Marker.START) {
            if (startPos != null) {
                grid[startPos!!.x][startPos!!.y] = Marker.DEFAULT
            }
            startPos = MarkerPos(x, y)
        }
        if (value == Marker.END) {
            if (endPos != null) {
                grid[endPos!!.x][endPos!!.y] = Marker.DEFAULT
            }
            endPos = MarkerPos(x, y)
        }
        grid[x][y] = value
    }
}