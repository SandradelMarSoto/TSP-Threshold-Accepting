package unam.ciencias.heuristicas.heuristic

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
 * TODO
 *
 * @property path
 */
class Solution(val path: ArrayList<Int>, private val random: Random) {

    /**
     *
     */
    var swappedIndices: Pair<Int, Int>? = null

    /**
     * TODO
     *
     * @return
     */
    fun generateNeighbor(): Solution {
        val uIndex: Int
        var vIndex: Int

        if (swappedIndices == null) {
            val n = path.size

            uIndex = (0 until n).random(this.random)
            vIndex = (0 until n).random(this.random)
            while (uIndex == vIndex)
                vIndex = (0 until n).random(this.random)

        } else {
            uIndex = swappedIndices!!.first
            vIndex = swappedIndices!!.second
        }

        val newPath = ArrayList(path)

        val auxIndex = newPath[uIndex]
        newPath[uIndex] = newPath[vIndex]
        newPath[vIndex] = auxIndex

        return Solution(newPath, this.random)
    }

    /**
     * TODO
     *
     */
    fun initializeNeighborIndices() {
        val n = path.size

        val i = (0 until n).random(this.random)
        var j = (0 until n).random(this.random)
        while (i == j)
            j = (0 until n).random(this.random)

        swappedIndices = Pair(min(i, j), max(i, j))
    }

    /**
     * TODO
     *
     */
    fun clearNeighborIndices() {
        swappedIndices = null
    }

}