package unam.ciencias.heuristicas.heuristic

/**
 * TODO
 *
 * @property path
 */
class Solution(val path: ArrayList<Int>) {

    /**
     * TODO
     * FIXME: Improve it.
     *
     * @return
     */
    fun generateNeighbor(): Solution {
        val n = path.size

        val uIndex = (0 until n).random()
        var vIndex = (0 until n).random()
        while (uIndex == vIndex)
            vIndex = (0 until n).random()

        val newPath = ArrayList(path)

        val auxIndex = newPath[uIndex]
        newPath[uIndex] = newPath[vIndex]
        newPath[vIndex] = auxIndex

        return Solution(newPath)
    }

}