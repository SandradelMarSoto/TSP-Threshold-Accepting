package unam.ciencias.heuristicas.heuristic

/**
 * TODO
 *
 * @property path
 */
class Solution(
    val path: ArrayList<Int>
) {

    fun neighbors(s: Solution): Boolean {
        var swaps = 0
        for (i in 0 until path.size) {
            if (this.path[i] != s.path[i]) swaps++
        }
        return swaps == 2
    }

    /**
     * TODO
     * FIXME: Improve it.
     *
     * @return
     */
    fun generateNeighbor(): Solution {
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

        return Solution(newPath)
    }

    companion object {
        /**
         * TODO
         *
         * @param elems
         * @return
         */
        fun getInitial(elems: Collection<Int>): Solution {
            val path = ArrayList(elems)
            path.shuffle()
            return Solution(path)
        }
    }
}