package unam.ciencias.heuristicas.model

/**
 * For modeling a City from the db.
 *
 * @property id The identifier of the city.
 * @property name The name of the city.
 * @property country The country where it's located.
 * @property population The population of the city.
 * @property latitude The latitude of the city.
 * @property longitude The longitude of the city.
 */
data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
)