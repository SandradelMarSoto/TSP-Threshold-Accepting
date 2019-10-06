package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.Constants.Companion.ACCEPTED_NEIGHBORS
import unam.ciencias.heuristicas.algorithm.Metrologist
import kotlin.math.abs
import kotlin.random.Random

/**
 * Bunch of variables that are going to be stored and updated throughout the TSP heuristic.
 *
 * @property metrologist
 * @property random
 */
class System(private val metrologist: Metrologist, private val random: Random) {

    /** TODO */
    var solution = initialSolution()
    /** TODO */
    var temperature = initialTemperature(solution)

    /**
     * TODO
     *
     * @return
     */
    private fun initialSolution(): Solution {
        val cities = metrologist.inducedGraph.getNodes()
        val path = ArrayList(cities)
        path.shuffle(this.random)
        return Solution(path, this.random)
    }

    /**
     * TODO
     *
     * @param solution
     * @return
     */
    private fun initialTemperature(solution: Solution): Double {
        var p = acceptedPercentage(solution, temperature)
        var temperature = Constants.T
        val probability = Constants.P

        val t1: Double
        val t2: Double

        if (abs(probability - p) < Constants.EPSILON_P)
            return temperature
        if (p < probability) {
            while (p < probability) {
                temperature *= 2
                p = acceptedPercentage(solution, temperature)
            }
            t1 = temperature / 2
            t2 = temperature
        } else {
            while (p > probability) {
                temperature /= 2
                p = acceptedPercentage(solution, temperature)
            }
            t1 = temperature
            t2 = 2 * temperature
        }
        return binarySearch(solution, t1, t2, probability)
    }

    /**
     * TODO
     *
     * @param solution
     * @param temperature
     * @return
     */
    private fun acceptedPercentage(solution: Solution, temperature: Double): Double {
        var c = 0
        for (i in 0 until ACCEPTED_NEIGHBORS) {
            solution.initializeNeighborIndices()

            if (metrologist.costFunction(solution, true) < metrologist.costFunction(solution) + temperature) {
                this.solution = solution.generateNeighbor()
                c++

            } else {
                solution.clearNeighborIndices()
            }
        }
        return c.toDouble() / ACCEPTED_NEIGHBORS
    }

    /**
     * TODO
     *
     * @param solution
     * @param t1
     * @param t2
     * @param probability
     * @return
     */
    private tailrec fun binarySearch(solution: Solution, t1: Double, t2: Double, probability: Double): Double {
        val tMid = (t1 + t2) / 2
        if (t2 - t1 < Constants.EPSILON_P)
            return tMid

        val p = acceptedPercentage(solution, tMid)
        if (abs(probability - p) < Constants.EPSILON_P)
            return tMid

        return if (p > probability)
            binarySearch(solution, t1, tMid, probability)
        else
            binarySearch(solution, tMid, t2, probability)
    }
}