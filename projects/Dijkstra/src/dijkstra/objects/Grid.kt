package dijkstra.objects


class Grid(columns: Int, rows: Int) {
    public val columns: Int = columns
    public val rows: Int = rows
    private val grid = Array<Array<Int>>(columns, {arrayOf<Int>(rows) })
    public fun get(x: Int, y: Int) : Int {
        return grid[x][y]
    }
    public fun set(x: Int, y: Int, value: Int) {
        grid[x][y] = value
    }
}