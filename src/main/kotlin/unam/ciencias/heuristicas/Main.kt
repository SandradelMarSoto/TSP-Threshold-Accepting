@file:JvmName("Main")

package unam.ciencias.heuristicas

import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.algorithm.Metrologist
import unam.ciencias.heuristicas.data.DbConnector
import unam.ciencias.heuristicas.heuristic.Solution
import unam.ciencias.heuristicas.heuristic.TSP
import unam.ciencias.heuristicas.model.City


fun main() {
    val graph = initializeGraph()

    val citiesIds = ArrayList(readLine()!!.split(",").map { it.toInt() })
    val metrologist = Metrologist(graph, citiesIds)

    println("Cities: $citiesIds")
    println("Evaluation: ${metrologist.costFunction(Solution(citiesIds))}")
    println("Max distance: ${metrologist.maxDistance()}")
    println("Normalizer: ${metrologist.normalizer()}")
    println()

    val tsp = TSP(metrologist)
    tsp.thresholdAccepting()

    println("Path: ${tsp.calculatePath()}")
    println("Evaluation: ${tsp.evaluation()}")
    println("Feasible: ${tsp.isFeasible()}")
}

fun initializeGraph(): Graph<Int, City> {
    val graph = Graph<Int, City>()

    DbConnector.getCities().forEach { graph.addNode(it.id, it) }
    DbConnector.getConnectionsBetweenTwoCities()
        .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }

    return graph
}
