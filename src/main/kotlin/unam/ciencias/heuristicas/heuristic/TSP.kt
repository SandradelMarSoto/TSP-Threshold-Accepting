package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist
import kotlin.random.Random

/**
 * TODO
 *
 * @property metrologist
 * @property seed
 */
class TSP(private val metrologist: Metrologist, private val seed: Int) {

    private val random = Random(seed)
    private val system = System(metrologist, random)

    /**
     * TODO
     *
     */
    fun thresholdAccepting() {

        var p = 0.0
        while (system.temperature > Constants.EPSILON) {
            var q = Double.POSITIVE_INFINITY
            while (p <= q) { // While there's no thermal equilibrium.
                q = p
                val (newAcceptedPercentage, s) = Batch(
                    system.solution, system.temperature, metrologist
                ).calculateBatch()

                p = newAcceptedPercentage
                system.solution = s

                println(system.solution.path)
                println(metrologist.costFunction(system.solution))
            }
            println(">>"+ system.temperature)
            system.temperature *= Constants.PHI
        }
    }

    /**
     * FIXME
     *
     * @return
     */
    fun calculatePath(): ArrayList<Int> {
        return arrayListOf()
    }

    /**
     * FIXME
     *
     * @return
     */
    fun evaluation(): Double {
        return 0.0
    }

    /**
     * FIXME
     *
     * @return
     */
    fun isFeasible(): Boolean {
        return false
    }

}