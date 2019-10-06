package unam.ciencias.heuristicas.model

/**
 * For modeling the distance between each city, in the graph that it's going to be constructed, it represents
 * the weight of the edge.
 *
 * @property idCity1 The first city identifier.
 * @property idCity2 The second city identifier.
 * @property distance The distance of the cities.
 */
data class CityConnection(
    val idCity1: Int,
    val idCity2: Int,
    val distance: Double
)