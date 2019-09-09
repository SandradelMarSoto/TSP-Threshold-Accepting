package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist


class Batch(
    private var solution: Solution,
    private var temperature: Double,
    private val metrologist: Metrologist
) {

    fun calculateBatch(): Pair<Double, Solution> {
        var c = 0
        var r = 0.0
        while (c < Constants.L) {
            val neighbor = solution.generateNeighbor()
            if (metrologist.costFunction(neighbor) < metrologist.costFunction(solution) + temperature) {
                solution = neighbor
                c++
                r += metrologist.costFunction(neighbor)
            }
        }
        return Pair(r / Constants.L, solution)
    }

}