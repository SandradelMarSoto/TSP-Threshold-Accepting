package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.EPSILON
import unam.ciencias.heuristicas.Constants.Companion.PHI
import unam.ciencias.heuristicas.Constants.Companion.T

class ThresholdAccepting(val system: System) {
    fun updateTemperature() {
        var p = 0.0
        while (T > EPSILON) {
            var q = Double.POSITIVE_INFINITY
            while (p <= q) {
                q = p
                val (p, s) = Batch(System.solution, .0, System.metrologist).calculateBatch()

            }
            system.updateTemperature(PHI * T)
        }
    }

    fun acceptedPercentage(): Unit {

    }

    fun binarySearch(): Unit {

    }
}