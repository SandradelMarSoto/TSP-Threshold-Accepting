package unam.ciencias.heuristicas

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.algorithm.Metrologist
import unam.ciencias.heuristicas.data.DbConnector
import unam.ciencias.heuristicas.heuristic.Solution
import unam.ciencias.heuristicas.model.City
import java.io.File
import kotlin.random.Random
import kotlin.test.assertTrue


object TspTest : Spek({

    val filesForTesting = mapOf(
        40 to "examples/input/40-cities.txt",
        150 to "examples/input/150-cities.txt"
    )

    fun initializeGraph(): Graph<Int, City> {
        val graph = Graph<Int, City>()

        DbConnector.getCities().forEach { graph.addNode(it.id, it) }
        DbConnector.getConnectionsBetweenTwoCities()
            .forEach { graph.addEdge(it.idCity1, it.idCity2, it.distance) }

        return graph
    }

    Feature("Evaluate permutation for TSP") {
        filesForTesting.forEach { (numberCities, filePath) ->

            var normalizer = 0.0
            var maxDistance = 0.0
            var costFunction = 0.0

            Scenario("Parse input of the TSP instance ($numberCities cities)") {
                val graph = initializeGraph()

                val citiesInput = File(filePath).readLines()[0]
                val citiesIds = ArrayList(citiesInput.split(",").map { it.toInt() })

                val metrologist = Metrologist(graph, ArrayList(citiesIds))

                And("Set the expected values given by Canek") {
                    if (numberCities == 40) {
                        normalizer = 182907823.060000002384186
                        maxDistance = 4970123.959999999962747
                        costFunction = 4526237.801017570309341
                    } else {
                        normalizer = 722989785.090000391006470
                        maxDistance = 4978506.480000000447035
                        costFunction = 6210491.034747813828290
                    }
                }

                Then("Must be the same normalizer") {
                    assertTrue { metrologist.normalizer == normalizer }
                }

                And("Must be the same max distance") {
                    assertTrue { metrologist.maxDistance() == maxDistance }
                }

                And("Must be the same cost function") {
                    val costFunctionWithLessPrecision = String.format("%.7f", costFunction).toDouble()

                    val solution = Solution(citiesIds, Random(1))
                    val tspCostFunctionWithLessPrecision =
                        String.format("%.7f", metrologist.costFunction(solution)).toDouble()

                    assertTrue { tspCostFunctionWithLessPrecision == costFunctionWithLessPrecision }

                }
            }
        }

    }
})
