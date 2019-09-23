package unam.ciencias.heuristicas.heuristic

import kotlin.random.Random

/**
 * TODO
 *
 * @property path
 */
class Solution(val path: ArrayList<Int>, private val random: Random) {

    /**
     * TODO
     * FIXME: Improve it.
     *
     * @return
     */
    fun generateNeighbor(): Solution {
        val n = path.size

        val uIndex = (0 until n).random(this.random)
        var vIndex = (0 until n).random(this.random)
        while (uIndex == vIndex)
            vIndex = (0 until n).random(this.random)

        val newPath = ArrayList(path)

        val auxIndex = newPath[uIndex]
        newPath[uIndex] = newPath[vIndex]
        newPath[vIndex] = auxIndex

        return Solution(newPath, this.random)
    }

}