package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import unam.ciencias.heuristicas.algorithm.Metrologist
import kotlin.random.Random

/**
 *
 *
 * @property metrologist
 * @property seed
 */
class TSP(
    private val metrologist: Metrologist,
    private val seed: Int,
    private val printOnlyBestSolutions: Boolean
) {

    private val random = Random(seed)
    private val system = System(metrologist, random)
    lateinit var bestSolutionSoFar: Pair<Solution, Double>

    /**
     * TODO
     *
     */
    fun thresholdAccepting() {
        var min = Double.MAX_VALUE

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

                if (min > metrologist.costFunction(system.solution)) {
                    bestSolutionSoFar = Pair(system.solution, metrologist.costFunction(system.solution))
                    min = metrologist.costFunction(system.solution)
                    if (printOnlyBestSolutions) {
                        println("E: " + metrologist.costFunction(system.solution))
                    }

                }
                if (!printOnlyBestSolutions) {
                    println("E: " + metrologist.costFunction(system.solution))
                }
            }
            system.temperature *= Constants.PHI
        }
    }

    /**
     * Returns the path ob the best solution found so far.
     *
     * @return a list of the solution.
     */
    fun path(): ArrayList<Int> = bestSolutionSoFar.first.path

    /**
     *
     *
     * @return the evaluation of the path
     */
    fun evaluation(): Double = bestSolutionSoFar.second

    /**
     * TODO
     *
     * @return
     */
    fun isFeasible(): Boolean = bestSolutionSoFar.second <= 1

}