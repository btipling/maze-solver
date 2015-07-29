package dijkstra.objects


class Grid(width: Int, height: Int) {
    public val width: Int = width
    public val height: Int = height
    private val grid = Array<Array<Int>>(width, {arrayOf<Int>(height) })
    public fun get(x: Int, y: Int) : Int {
        return grid[x][y]
    }
    public fun set(x: Int, y: Int, value: Int) {
        grid[x][y] = value
    }
}