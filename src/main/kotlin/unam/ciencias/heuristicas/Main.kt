/**
 * The main execution.
 * It handles the flags passed by command line, read the database and build its graph and,
 * finally runs the meta-heuristic.
 *
 * The program ends by printing out the best solution so far (list alike format), its evaluation and if it's
 * feasible.
 *
 * It handles multiples seeds, and in each evaluation, shows the result for each seed.
 */
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
    val printOnlyBestSolutions = args[2] == "true"

    val citiesIds = ArrayList(citiesInput.split(",").map { it.toInt() })
    val seeds = seedsInput.split(",").map { it.toInt() }

    val metrologist = Metrologist(graph, citiesIds)

    println("Cities: $citiesIds")

    for (seed in seeds) {
        println("Seed: $seed")

        println()

        val tsp = TSP(metrologist, seed, printOnlyBestSolutions)
        tsp.thresholdAccepting()

        println("Path: ${tsp.path()}")
        println("Evaluation: ${tsp.evaluation()}")
        println("Feasible: ${tsp.isFeasible()}")
        println("---------------------------------------------\n")
    }
}

/**
 * Constructs a [Graph] of the cities and its distance of a given database.
 *
 * @return A graph filled with the values of the database.
 */
fun initializeGraph(): Graph<Int, City> {
    val graph = Graph<Int, City>()

    DbConnector.getCities().forEach { graph.addNode(it.id, it) }
    DbConnector.getConnectionsBetweenTwoCities()
        .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }

    return graph
}
