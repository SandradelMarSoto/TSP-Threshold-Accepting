package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.L
import unam.ciencias.heuristicas.algorithm.Metrologist


/**
 * TODO
 *
 * @property solution
 * @property temperature
 * @property metrologist
 */
class Batch(
    private var solution: Solution,
    private val temperature: Double,
    private val metrologist: Metrologist
) {

    /**
     * TODO
     *
     * @return Pair whose first entry is the average of the accepted solutions, and the second
     *         entry contains the last solution found.
     */
    fun calculateBatch(): Pair<Double, Solution> {
        var c = 0
        var r = 0.0
        while (c < L) {
            // FIXME: No crear una nueva solución si no se va a ocupar.
            val neighbor = solution.generateNeighbor()
            if (metrologist.costFunction(neighbor) < metrologist.costFunction(solution) + temperature) {
                solution = neighbor
                c++
                r += metrologist.costFunction(neighbor)
            }
        }
        return Pair(r / L, solution)
    }

}