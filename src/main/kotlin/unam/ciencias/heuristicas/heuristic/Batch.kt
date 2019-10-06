package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.L
import unam.ciencias.heuristicas.Constants.Companion.MAX_ITERATIONS
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
        var i = 0
        while (c < L && i++ < MAX_ITERATIONS) {
            solution.initializeNeighborIndices()

            if (metrologist.costFunction(solution, true) < metrologist.costFunction(solution) + temperature) {
                solution = solution.generateNeighbor()
                c++
                r += metrologist.costFunction(solution)
            } else {
                solution.clearNeighborIndices()
            }
        }
        return Pair(r / L, solution)
    }

}