package dijkstra.objects.ui

import dijkstra.objects.Grid
import dijkstra.objects.State
import java.awt.*
import java.awt.event.*
import java.util.logging.Logger
import javax.swing.*

class Canvas(state: State, logger: Logger) : JPanel() {
    val state = state
    var over: Point? = null
    val popupMenu = JPopupMenu()
    val logger = logger
    var lastLoc: EventLocation? = null
    var dragMarker: Grid.Marker? = null

    class EventLocation(e: MouseEvent, state: State) {
        private val state = state
        private val clicked = eventToPoint(e)
        public val x: Int = clicked.x / state.boxSize
        public val y: Int = clicked.y / state.boxSize

        private fun eventToPoint(e: MouseEvent): Point {
            val s = state.boxSize
            val x = (e.getX() / s) * s
            val y = (e.getY() / s) * s
            return Point(x, y)
        }

        fun isValid(): Boolean {
            if (x < 0 || y < 0) {
                return false
            }
            if (x >= state.grid.columns || y >= state.grid.rows) {
                return false
            }
            return true
        }
    }

    init {
        val self = this
        setMinimumSize(state.canvasDimension)
        setMaximumSize(state.canvasDimension)
        setPreferredSize(state.canvasDimension)
        setSize(state.canvasDimension)
        setLayout(BoxLayout(this, BoxLayout.LINE_AXIS))
        addMouseMotionListener(object: MouseMotionListener {
            override fun mouseDragged(e: MouseEvent) {
                if (dragMarker == null) {
                    return
                }
                val dM = dragMarker!!
                val eventLocation = EventLocation(e, state)
                state.grid.set(eventLocation.x, eventLocation.y, dM)
                repaint()
            }

            override fun mouseMoved(e: MouseEvent) {
                val eventLocation = EventLocation(e, state)
                over = Point(eventLocation.x, eventLocation.y)
                repaint()
            }
        })
        addMouseListener(object : MouseListener {

            override fun mouseClicked(e: MouseEvent) {
                val loc = EventLocation(e, state)
                lastLoc = loc
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(self, e.getX(), e.getY())
                    return
                }
                if (!loc.isValid()) {
                    return;
                }
                if (state.grid.get(loc.x, loc.y).equals(Grid.Marker.WALL)) {
                    state.grid.set(loc.x, loc.y, Grid.Marker.DEFAULT)
                } else {
                    state.grid.set(loc.x, loc.y, Grid.Marker.WALL)
                }
                repaint()
            }

            override fun mousePressed(e: MouseEvent) {
                val eventLocation = EventLocation(e, state)
                dragMarker = Grid.Marker.WALL
                if (state.grid.get(eventLocation.x, eventLocation.y).equals(Grid.Marker.WALL)) {
                    dragMarker = Grid.Marker.DEFAULT
                }
                over = null
            }

            override fun mouseReleased(e: MouseEvent) {
                dragMarker = null
            }

            override fun mouseEntered(e: MouseEvent) {
            }

            override fun mouseExited(e: MouseEvent) {
                over = null
                repaint()
            }
        })
        var menuItem = JMenuItem("Set to start")
        fun setMarker(marker: Grid.Marker) {
            if (lastLoc == null) {
                return
            }
            state.grid.set(lastLoc!!.x, lastLoc!!.y, marker)
        }
        menuItem.addActionListener({e: ActionEvent -> run {
            logger.info("Setting to start")
            setMarker(Grid.Marker.START)
            repaint()
        }})
        popupMenu.add(menuItem)
        menuItem = JMenuItem("Set to end")
        menuItem.addActionListener({e: ActionEvent -> run {
            logger.info("Setting to end")
            setMarker(Grid.Marker.END)
            repaint()
        }})
        popupMenu.add(menuItem)
        self.setComponentPopupMenu(popupMenu)
    }


    public override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        doDrawing(g)
    }

    private fun doDrawing(g: Graphics) {
        val g2d = g as Graphics2D
        val size = getSize()
        g2d.setColor(state.canvasBG)
        val width = size.width;
        val height = size.height;
        g2d.fillRect(0, 0, width, height)
        val columnWidth = state.canvasDimension.width/state.grid.columns
        val columnHeight = state.canvasDimension.height/state.grid.rows
        g2d.setColor(state.lineColor)
        for (x: Int in 1..state.grid.columns) {
            val xPos = x * columnWidth
            g2d.drawLine(xPos, 0, xPos, state.canvasDimension.height)
        }
        for (y: Int in 1..state.grid.rows) {
            val yPos = y * columnHeight
            g2d.drawLine(0, yPos, state.canvasDimension.width, yPos)
        }
        for (x: Int in 0..state.grid.columns-1) {
            for (y: Int in 0..state.grid.rows-1) {
                if (!state.grid.get(x, y).equals(Grid.Marker.DEFAULT)) {
                    paintGrid(x, y, g2d)
                }
            }
        }
        if (over != null) {
            val o = over!!
            g2d.setColor(state.overColor)
            val s = state.boxSize
            g2d.fillRect(o.x * s, o.y * s, s, s)
        }
    }

    private fun paintGrid(x: Int, y: Int, g2d: Graphics2D) {
        when (state.grid.get(x, y)) {
            Grid.Marker.WALL -> g2d.setColor(state.wallColor)
            Grid.Marker.OVER -> g2d.setColor(state.overColor)
            Grid.Marker.START -> g2d.setColor(state.startColor)
            Grid.Marker.END -> g2d.setColor(state.endColor)
            Grid.Marker.PATH -> g2d.setColor(state.pathColor)
        }
        val s = state.boxSize
        g2d.fillRect(x * s, y * s, s, s)
    }
}