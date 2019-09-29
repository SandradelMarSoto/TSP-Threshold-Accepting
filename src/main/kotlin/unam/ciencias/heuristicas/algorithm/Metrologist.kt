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

    fun costFunction(solution: Solution): Double {
        val path = solution.path

        var pathWeightSum = 0.0
        for (i in 0 until path.size - 1)
            pathWeightSum += augmentedCostFunction(path[i], path[i + 1])

        return pathWeightSum / normalizer()
    }

     // FIXME: quitar esto, pero solo es para las pruebas
    fun costFunction(l: ArrayList<Int>): Double {

        var pathWeightSum = 0.0
        for (i in 0 until l.size - 1)
            pathWeightSum += augmentedCostFunction(l[i], l[i + 1])

        return pathWeightSum / normalizer()
    }

    // FIXME: memoize it.
    fun normalizer(): Double {
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