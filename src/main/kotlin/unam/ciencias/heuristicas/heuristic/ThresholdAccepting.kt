package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.EPSILON
import unam.ciencias.heuristicas.Constants.Companion.EPSILON_P
import unam.ciencias.heuristicas.Constants.Companion.PHI
import unam.ciencias.heuristicas.Constants.Companion.T
import kotlin.math.abs

class ThresholdAccepting(private val system: System) {

    /** TODO */
    private var acceptedSolutionsPercentage = 0.0

    fun updateTemperature() {
        val solution = system.solution
        val temperature = system.temperature

        while (temperature > EPSILON) {
            var q = Double.POSITIVE_INFINITY
            while (acceptedSolutionsPercentage <= q) { // While there's no thermal equilibrium.
                q = acceptedSolutionsPercentage
                val (newAcceptedPercentage, s) = Batch(
                    solution, temperature, system.metrologist
                ).calculateBatch()

                system.solution = s
                acceptedSolutionsPercentage = newAcceptedPercentage
            }
            system.temperature = PHI * T
        }
    }

    fun initialTemperature(probability: Double): Double {
        val solution = system.solution
        val temperature = system.temperature

        var p = acceptedPercentage()

        val t1: Double
        val t2: Double

        if (abs(probability - p) < EPSILON_P)
            return temperature
        if (p < probability) {
            while (p < probability) {
                system.temperature *= 2
                p = acceptedPercentage()
            }
            t1 = system.temperature / 2
            t2 = system.temperature
        } else {
            while (p > probability) {
                system.temperature /= 2
                p = acceptedPercentage()
            }
            t1 = system.temperature
            t2 = 2 * system.temperature
        }
        return binarySearch(solution, t1, t2, probability)
    }

    private fun acceptedPercentage(): Double {
        var c = 0
        val costFunction = { s: Solution -> system.metrologist.costFunction(s) }

        for (i in 0 until system.graphSize - 1) {
            val neighbor = system.solution.generateNeighbor()
            if (costFunction(neighbor) <= costFunction(system.solution) + T) {
                c++
                system.solution = neighbor
            }
        }
        return c.toDouble() / system.graphSize
    }

    private tailrec fun binarySearch(solution: Solution, t1: Double, t2: Double, probability: Double): Double {
        val tMid = (t1 + t2) / 2
        if (t2 - t1 < EPSILON_P)
            return tMid

        val p = acceptedPercentage()
        if (abs(probability - p) < EPSILON_P)
            return tMid

        return if (p > probability)
            binarySearch(solution, t1, tMid, probability)
        else
            binarySearch(solution, tMid, t2, probability)
    }
}