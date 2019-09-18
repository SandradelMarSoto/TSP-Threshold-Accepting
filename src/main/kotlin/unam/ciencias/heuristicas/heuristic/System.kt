package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist
import kotlin.math.abs
import kotlin.random.Random

/**
 * Bunch of variables that are going to be stored and updated throughout the TSP heuristic.
 *
 * @property metrologist
 * @property seed no-op
 */
class System(private val metrologist: Metrologist, private val seed: Int) {

    /** TODO */
    var solution = initialSolution()
    /** TODO */
    var temperature = initialTemperature(solution)
    /** TODO */
    val random = Random(seed)

    /**
     * TODO
     *
     * @return
     */
    private fun initialSolution(): Solution {
        val cities = metrologist.inducedGraph.getNodes()
        val path = ArrayList(cities)
        path.shuffle()
        return Solution(path)
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
        val costFunction = { s: Solution -> metrologist.costFunction(s) }
        val graphSize = metrologist.inducedGraph.size()

        for (i in 0 until graphSize) {
            val neighbor = solution.generateNeighbor()
            if (costFunction(neighbor) <= costFunction(solution) + temperature) {
                c++
                this.solution = neighbor
            }
        }
        return c.toDouble() / graphSize
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