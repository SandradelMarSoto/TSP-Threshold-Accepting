package unam.ciencias.heuristicas.model

data class City(
    val id: Int,
    val name: String,
    val country: String,
    val population: Int,
    val latitude: Double,
    val longitude: Double
)