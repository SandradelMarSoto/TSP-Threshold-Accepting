package unam.ciencias.heuristicas.heuristic

import unam.ciencias.heuristicas.Constants.Companion.T
import unam.ciencias.heuristicas.algorithm.Metrologist

/**
 * TODO
 *
 * @property metrologist
 */
class System(val metrologist: Metrologist) {
    /** TODO */
    var solution = Solution.getInitial(metrologist.inducedGraph.getNodes())
    /** TODO */
    var temperature = T
    /** TODO */
    val graphSize = metrologist.inducedGraph.size()

}