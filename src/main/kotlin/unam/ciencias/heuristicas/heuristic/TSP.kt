package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist

/**
 * TODO
 *
 * @property metrologist
 */
class TSP(private val metrologist: Metrologist) {

    private val system = System(metrologist)


    /**
     * TODO
     *
     */
    fun thresholdAccepting() {
        val solution = system.solution
        val temperature = system.temperature

        var p = 0.0
        while (temperature > Constants.EPSILON) {
            var q = Double.POSITIVE_INFINITY
            while (p <= q) { // While there's no thermal equilibrium.
                q = p
                val (newAcceptedPercentage, s) = Batch(
                    solution, temperature, metrologist
                ).calculateBatch()

                p = newAcceptedPercentage
                system.solution = s
                println(system.solution.path)
                println(metrologist.costFunction(system.solution))
            }
            system.temperature = Constants.PHI * temperature
        }
    }

    fun calculatePath(): ArrayList<Int> {
        return arrayListOf()
    }

    fun evaluation(): Double {
        return 0.0
    }

    fun isFeasible(): Boolean {
        return false
    }


}