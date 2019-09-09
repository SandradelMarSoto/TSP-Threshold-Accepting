package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist

//FIXME: TSP
class Batch<T>(var solution: Solution<T>?, val metrologist: Metrologist<T>?) {

    //FIXME: hay un error en la función objetivo
    fun calculateBatch(temperature: Temperature, solution: Solution<T>): Pair<Double, Solution<T>> {
        var c = 0
        var r = 0.0
        while (c < Constants.L) {
            val solutionNeighbor = solution.generateNeighbor()
            if (metrologist!!.costFunction(solutionNeighbor) < metrologist!!.costFunction(solution) + Constants.T) {
                this.solution = solutionNeighbor
                c++
                r += metrologist.costFunction(solutionNeighbor)
            }
        }
        return Pair(r / Constants.L, solution)
    }

    companion object {}
}