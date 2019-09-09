package unam.ciencias.heuristicas.heuristic

/**
 *
 */
class Solution<T>(
    val path: ArrayList<T>
) {

    fun neighbors(s: Solution<T>): Boolean {
        var swaps = 0
        for (i in 0 until path.size) {
            if (this.path[i] != s.path[i]) swaps++
        }
        return swaps == 2
    }

    fun generateNeighbor(): Solution<T> {
        // Both inclusive
        val n = path.size

        val uIndex = (0..n).random()
        var vIndex = (0..n).random()
        while (uIndex == vIndex)
            vIndex = (0..n).random()

        val newPath = ArrayList(path)

        val auxIndex = newPath[uIndex]
        newPath[uIndex] = newPath[vIndex]
        newPath[vIndex] = auxIndex

        return Solution<T>(newPath)
    }

    companion object {
        fun <T> getInitialSolution(elems: Collection<T>): Solution<T> {
            val path = ArrayList<T>(elems)
            path.shuffle()
            return Solution(path)
        }
    }
}