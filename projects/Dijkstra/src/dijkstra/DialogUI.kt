package dijkstra

import dijkstra.dialogs.DialogContainer
import java.awt.Color
import javax.swing.BoxLayout
import javax.swing.JPanel
import javax.swing.border.LineBorder

public class DialogUI : DialogContainer {

    constructor() {
        logger.info("Init graph container")
        graphPanelContainer.setBorder(LineBorder(Color.BLACK, 1))
        graphPanelContainer.setLayout(BoxLayout(graphPanelContainer, BoxLayout.LINE_AXIS))
    }

    public fun getGraphContainer(): JPanel {
        return mainContainer
    }

    public fun getBoardContainer(): JPanel {
        return graphPanelContainer;
    }

    public fun refresh() {
        mainContainer.invalidate()
    }

}