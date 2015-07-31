package dijkstra.objects.ui

import dijkstra.dialogs.DialogContainer
import dijkstra.objects.State
import java.awt.Color
import java.awt.Dimension
import java.util.logging.Logger
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.border.LineBorder

public class Dialog(state: State, logger: Logger) : DialogContainer() {

    init {
        logger.info("Initializing dialog.")
        graphPanelContainer.setBorder(LineBorder(Color.DARK_GRAY, 1))
        graphPanelContainer.setLayout(BoxLayout(graphPanelContainer, BoxLayout.LINE_AXIS))
        graphPanelContainer.add(Canvas(state, logger));
    }

    public fun getGraphContainer(): JPanel {
        return mainContainer
    }

    public fun refresh() {
        mainContainer.invalidate()
        mainContainer.repaint()
    }

}