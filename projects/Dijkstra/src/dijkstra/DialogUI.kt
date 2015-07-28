package dijkstra

import dijkstra.dialogs.DialogContainer
import javax.swing.JPanel

public class DialogUI : DialogContainer {

    constructor() {
        logger.info("Init graph container")
    }

    public fun getGraphContainer(): JPanel {
        return mainContainer
    }

    public fun getBoardContainer(): JPanel {
        return graphPanelContainer;
    }

}