package mazesolver.objects.algorithms

import mazesolver.objects.Grid
import mazesolver.objects.Point
import mazesolver.objects.State
import java.util.logging.Logger

public class BStar : SearchAlgorithm {

    override fun execute(state: State): List<Point> {
        throw UnsupportedOperationException()
    }

    override fun getName(): String {
        return "B*"
    }
}


