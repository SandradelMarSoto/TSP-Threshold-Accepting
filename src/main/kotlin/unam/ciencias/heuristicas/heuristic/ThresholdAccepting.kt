package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.EPSILON
import unam.ciencias.heuristicas.Constants.Companion.T
import unam.ciencias.heuristicas.algorithm.Metrologist

class ThresholdAccepting<T>(val system: System) {
    fun updateTemperature() {
        var p = 0.0
        while (T > EPSILON) {
            var q = Double.POSITIVE_INFINITY
            while (p <= q) {
                q = p

                //val x = Batch<Solution<T>?, Metrologist<T>?>(null, null)
            }
            //temperature.T = Constants.PHI * temperature.T
        }

    }
}