package unam.ciencias.heuristicas.algorithm

import unam.ciencias.heuristicas.model.City
import kotlin.math.*

private const val EARTH_RADIUS_IN_METERS = 6373000.0

class TSP<T>(
    private val graph: Graph<T, City>,
    private val tspInstance: ArrayList<T>
) {


    private fun augmentedCostFunction(u: T, v: T): Double =
        if (graph.hasEdge(u, v))
            graph.edgeWeight(u, v)!!
        else
            naturalDistance(u, v) * maxDistance()

    private fun naturalDistance(u: T, v: T): Double {
        val latitudeU = rad(graph.getNodeInfo(u)!!.latitude)
        val longitudeU = rad(graph.getNodeInfo(u)!!.longitude)
        val latitudeV = rad(graph.getNodeInfo(v)!!.latitude)
        val longitudeV = rad(graph.getNodeInfo(v)!!.latitude)

        val a = sin((latitudeV - latitudeU) / 2).pow(2) +
                (cos(latitudeU) * cos(latitudeV) * sin((longitudeV - longitudeU) / 2).pow(2))

        val r = EARTH_RADIUS_IN_METERS
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return c * r
    }

    fun feasibleSolution(): Boolean = graph.existSuchPath(tspInstance)

    //TODO: este sí
    fun costFunction(): Double {
        val pathWeightSum =
            (0 until tspInstance.size - 1).sumByDouble { augmentedCostFunction(tspInstance[it], tspInstance[it + 1]) }

        return pathWeightSum / normalizer()
    }

    //TODO: este sí
    fun normalizer(): Double {
        var result = 0.0
        for ((i, edge) in graph.edges.withIndex()) {
            if (i < graph.size())
                break
            result += edge.weight
        }
        return result
    }

    //TODO: este sí
    fun maxDistance(): Double = graph.edges.peek().weight

    private fun rad(g: Double): Double = (g * Math.PI) / 180

}