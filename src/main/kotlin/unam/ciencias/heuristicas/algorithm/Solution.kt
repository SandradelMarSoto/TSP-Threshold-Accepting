package unam.ciencias.heuristicas.algorithm

/**
 *
 */
class Solution<T>(
    val path: ArrayList<T>
) {
    val initialSolution = arrayListOf<Int>()

    fun neighbors(s: Solution<T>): Boolean {
        var swaps = 0
        for (i in 0 until path.size) {
            if (this.path[i] != s.path[i]) swaps++
        }
        return swaps == 2
    }

    fun generateNeighbor(): Solution<T>? {
        return null
    }

    companion object {}
}