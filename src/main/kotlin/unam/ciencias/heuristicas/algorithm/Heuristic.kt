package unam.ciencias.heuristicas.algorithm

import unam.ciencias.heuristicas.algorithm.Constants.Companion.EPSILON
import unam.ciencias.heuristicas.algorithm.Constants.Companion.L
import unam.ciencias.heuristicas.algorithm.Constants.Companion.T


class Heuristic<T> {


    fun binarySearch(){

    }

    fun thresholAccepting(temperature: Temperature, solution: Solution<T>){
        var p = 0.0
        while (T > EPSILON){
            var q = Double.POSITIVE_INFINITY
            while (p <= q){
                q = p

                val x  = Batch(L, solution, null)
            }
            temperature.T= Constants.PHI * temperature.T
        }
    }
}