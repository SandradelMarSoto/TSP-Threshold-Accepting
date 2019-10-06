package unam.ciencias.heuristicas.algorithm

import unam.ciencias.heuristicas.Constants.Companion.EARTH_RADIUS_IN_METERS
import unam.ciencias.heuristicas.heuristic.Solution
import unam.ciencias.heuristicas.model.City
import kotlin.math.*

/**
 * TODO
 *
 * @property graph Pondered graph representing a the cities as vertices and its connections
 * represented as weighted edges.
 * @property cities The instance of TSP which is going to be solved.
 */
class Metrologist(
    private val graph: Graph<Int, City>,
    private val cities: ArrayList<Int>
) {
    /** The induced graph built with the [graph] using the [cities] as vertices. */
    val inducedGraph = graph.inducedGraph(cities)

    /** TODO */
    val normalizer = normalizer()

    private fun augmentedCostFunction(u: Int, v: Int): Double =
        if (graph.hasEdge(u, v))
            graph.edgeWeight(u, v)!!
        else
            naturalDistance(u, v) * maxDistance()

    private fun naturalDistance(u: Int, v: Int): Double {
        val latitudeU = rad(inducedGraph.getNodeInfo(u)!!.latitude)
        val longitudeU = rad(inducedGraph.getNodeInfo(u)!!.longitude)
        val latitudeV = rad(inducedGraph.getNodeInfo(v)!!.latitude)
        val longitudeV = rad(inducedGraph.getNodeInfo(v)!!.longitude)

        val a = sin((latitudeV - latitudeU) / 2).pow(2) +
                cos(latitudeU) * cos(latitudeV) * sin((longitudeV - longitudeU) / 2).pow(2)

        val r = EARTH_RADIUS_IN_METERS
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return r * c
    }

    fun costFunction(solution: Solution, swappedIndices: Boolean = false): Double {
        val path = solution.path

        var pathWeightSum = 0.0

        for (i in 0 until path.size - 1) {
            pathWeightSum += augmentedCostFunction(path[i], path[i + 1])
        }

        if (swappedIndices) {
            val n = path.size - 1
            val i = solution.swappedIndices!!.first
            val j = solution.swappedIndices!!.second

            var edgeWeightToRemove = 0.0
            var modifiedEdgeWeights = 0.0

            // If they are adjacents.
            if (j - i == 1) {
                if (i == 0) {
                    edgeWeightToRemove = augmentedCostFunction(path[i], path[j]) +
                            augmentedCostFunction(path[j], path[j + 1])

                    modifiedEdgeWeights = augmentedCostFunction(path[j], path[i]) +
                            augmentedCostFunction(path[i], path[j + 1])
                } else if (i > 0 && j < n) {
                    edgeWeightToRemove = augmentedCostFunction(path[i - 1], path[i]) +
                            augmentedCostFunction(path[i], path[j]) +
                            augmentedCostFunction(path[j], path[j + 1])

                    modifiedEdgeWeights = augmentedCostFunction(path[i - 1], path[j]) +
                            augmentedCostFunction(path[j], path[i]) +
                            augmentedCostFunction(path[i], path[j + 1])
                } else if (i == n - 1) {
                    edgeWeightToRemove = augmentedCostFunction(path[i - 1], path[i]) +
                            augmentedCostFunction(path[i], path[j])

                    modifiedEdgeWeights = augmentedCostFunction(path[i - 1], path[n]) +
                            augmentedCostFunction(path[n], path[i])
                }
            } else {
                if (i == 0 && j == n) {
                    edgeWeightToRemove = augmentedCostFunction(path[0], path[1]) +
                            augmentedCostFunction(path[n - 1], path[n])

                    modifiedEdgeWeights = augmentedCostFunction(path[n], path[1]) +
                            augmentedCostFunction(path[n - 1], path[0])
                } else if (i == 0 && j > 1 && j < n) {
                    edgeWeightToRemove = augmentedCostFunction(path[0], path[1]) +
                            augmentedCostFunction(path[j - 1], path[j]) +
                            augmentedCostFunction(path[j], path[j + 1])

                    modifiedEdgeWeights = augmentedCostFunction(path[j], path[1]) +
                            augmentedCostFunction(path[j - 1], path[0]) +
                            augmentedCostFunction(path[0], path[j + 1])
                } else if (i > 0 && i < n - 1 && j == n) {
                    edgeWeightToRemove = augmentedCostFunction(path[i - 1], path[i]) +
                            augmentedCostFunction(path[i], path[i + 1]) +
                            augmentedCostFunction(path[n - 1], path[n])

                    modifiedEdgeWeights = augmentedCostFunction(path[i - 1], path[n]) +
                            augmentedCostFunction(path[n], path[i + 1]) +
                            augmentedCostFunction(path[n - 1], path[i])
                } else {
                    edgeWeightToRemove = augmentedCostFunction(path[i - 1], path[i]) +
                            augmentedCostFunction(path[i], path[i + 1]) +
                            augmentedCostFunction(path[j - 1], path[j]) +
                            augmentedCostFunction(path[j], path[j + 1])

                    modifiedEdgeWeights = augmentedCostFunction(path[i - 1], path[j]) +
                            augmentedCostFunction(path[j], path[i + 1]) +
                            augmentedCostFunction(path[j - 1], path[i]) +
                            augmentedCostFunction(path[i], path[j + 1])
                }
            }
            pathWeightSum -= edgeWeightToRemove
            pathWeightSum += modifiedEdgeWeights
        }

        return pathWeightSum / normalizer
    }

    /**
     * TODO
     *
     * @return
     */
    private fun normalizer(): Double {
        var result = 0.0

        val orderedWeights = ArrayList(inducedGraph.edges)
        orderedWeights.sortWith(Comparator { o1, o2 -> o2.weight.compareTo(o1.weight) })

        for ((i, edge) in orderedWeights.withIndex()) {
            if (i > inducedGraph.size() - 2)
                break
            result += edge.weight
        }
        return result
    }

    fun maxDistance(): Double = inducedGraph.edges.peek().weight

    private fun rad(g: Double): Double = (g * Math.PI) / 180

}