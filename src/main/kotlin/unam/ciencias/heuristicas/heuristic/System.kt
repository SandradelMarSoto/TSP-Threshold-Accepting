package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.algorithm.Metrologist
import unam.ciencias.heuristicas.model.City

class System(private val graph: Graph<Int, City>) {

    var solution = Solution.getInitial(graph.getNodes())
    var temperature = Temperature()


    fun updateTemperature(d: Double): Unit {

    }

    companion object {
        val solution = Solution(arrayListOf(1))
        val metrologist = Metrologist(Graph(), arrayListOf(1))

    }
}