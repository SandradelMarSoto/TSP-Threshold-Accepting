package unam.ciencias.heuristicas.model

/**
 * TODO
 *
 * @property id
 * @property name
 * @property country
 * @property population
 * @property latitude
 * @property longitude
 */
data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
)