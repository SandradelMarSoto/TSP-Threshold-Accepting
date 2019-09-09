package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants
import kotlin.math.abs

class Temperature {

    companion object {

        fun initial(solution: Solution, temperature: Double, probability: Double): Double {
            val p = 0.0
            if (abs(probability - p) < Constants.EPSILON_P)
                return temperature
            if (p < probability) {
                return .0
            } else {
                return .0
            }
        }
    }

}