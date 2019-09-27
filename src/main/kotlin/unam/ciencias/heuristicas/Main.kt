@file:JvmName("Main")

package unam.ciencias.heuristicas

import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.algorithm.Metrologist
import unam.ciencias.heuristicas.data.DbConnector
import unam.ciencias.heuristicas.heuristic.TSP
import unam.ciencias.heuristicas.model.City
import java.io.File


fun main(args: Array<String>) {
    // Initialize the graph from the db.
    val graph = initializeGraph()

    // Read cities' ids and seeds from a file.
    val citiesInput = File(args[0]).readLines()[0]
    val seedsInput = File(args[1]).readLines()[0]

    val citiesIds = ArrayList(citiesInput.split(",").map { it.toInt() })
    val seeds = seedsInput.split(",").map { it.toInt() }

    val metrologist = Metrologist(graph, citiesIds)

    println("Cities: $citiesIds")

    for (seed in seeds) {
        println("Seed: $seed")
        //println("Evaluation: ${metrologist.costFunction(Solution(citiesIds))}")
        println("Max distance: ${metrologist.maxDistance()}")
        println("Normalizer: ${metrologist.normalizer()}")
        println()

        val tsp = TSP(metrologist, seed)
        tsp.thresholdAccepting()

        println("Path: ${tsp.calculatePath()}")
        println("Evaluation: ${tsp.evaluation()}")
        println("Feasible: ${tsp.isFeasible()}")
    }
}

fun initializeGraph(): Graph<Int, City> {
    val graph = Graph<Int, City>()

    DbConnector.getCities().forEach { graph.addNode(it.id, it) }
    DbConnector.getConnectionsBetweenTwoCities()
        .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }

    return graph
}
