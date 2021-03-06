package mazesolver.objects.ui

import mazesolver.dialogs.DialogContainer
import mazesolver.objects.State
import mazesolver.objects.algorithms.AStar
import mazesolver.objects.algorithms.Dijkstra
import java.awt.Color
import java.awt.Dimension
import java.util.logging.Logger
import javax.swing.BoxLayout
import javax.swing.DefaultComboBoxModel
import javax.swing.JPanel
import javax.swing.border.LineBorder

public class Dialog(state: State, logger: Logger) : DialogContainer() {

    init {
        logger.info("Initializing dialog.")
        graphPanelContainer.setBorder(LineBorder(Color.DARK_GRAY, 1))
        graphPanelContainer.setLayout(BoxLayout(graphPanelContainer, BoxLayout.LINE_AXIS))
        graphPanelContainer.add(Canvas(state, logger));
        val picks = arrayOf("Dijkstra", "A*")
        algorithmPicker.setModel(DefaultComboBoxModel(picks))
        setStatus("Started.")
        findPathButton.addActionListener {
            val alg: String = algorithmPicker.getSelectedItem() as String
            setStatus("Finding solution.")
            when(alg) {
                "Dijkstra" -> state.run(Dijkstra())
                "A*" -> state.run(AStar())
            }
        }
        clearAllButton.addActionListener {
            state.grid.clearAll()
            refresh()
        }
        state.addChangeListener(Runnable({refresh()}))
        state.addStatusListener({status: String ->
            setStatus(status)
        })
    }

    public fun setStatus(status: String) {
        statusLabel.setText(status)
    }

    public fun getGraphContainer(): JPanel {
        return mainContainer
    }

    public fun refresh() {
        mainContainer.invalidate()
        mainContainer.repaint()
    }

}