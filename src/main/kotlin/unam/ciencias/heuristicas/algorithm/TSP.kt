package unam.ciencias.heuristicas.algorithm

import unam.ciencias.heuristicas.model.City
import kotlin.math.*

private const val EARTH_RADIUS_IN_METERS = 6373000.0

class TSP<T>(
    private val graph: Graph<T, City>,
    private val tspInstance: ArrayList<T>
) {
    private val inducedGraph = graph.inducedGraph(tspInstance)

    private fun augmentedCostFunction(u: T, v: T): Double =
        if (graph.hasEdge(u, v))
            graph.edgeWeight(u, v)!!
        else
            naturalDistance(u, v) * maxDistance()

    private fun naturalDistance(u: T, v: T): Double {
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

    fun feasibleSolution(): Boolean = graph.existSuchPath(tspInstance)

    fun costFunction(): Double {
        var pathWeightSum = 0.0

        for (i in 0 until tspInstance.size - 1)
            pathWeightSum += augmentedCostFunction(tspInstance[i], tspInstance[i + 1])

        return pathWeightSum / normalizer()
    }

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