/**
 * String parser which parses a string of cities' ids and returns a string with json format with the latitude and
 * longitude with the same order of the input.
 *
 * This is going to be used for drawing lines representing the path of the tour.
 *
 * It is intended to be juxtaposed in the javascript code.
 *
 * Usage:

 * ```
 * [1,2,3]
 * ```
 *
 * Output:
 *
 * ```
 * >> [{lat: 37.772, lng: -122.214}, {lat: 21.291, lng: -157.821},{lat: -18.142, lng: 178.431}]
 * ```
 */
@file:JvmName("IdsParser")

package unam.ciencias.heuristicas

import unam.ciencias.heuristicas.algorithm.Graph
import unam.ciencias.heuristicas.data.DbConnector
import unam.ciencias.heuristicas.model.City
import java.io.File

fun main(args: Array<String>) {
    val rawIds = File(args[0]).readLines()[0]
    val citiesIds = ArrayList(rawIds.split(",").map { it.toInt() })
    val idsFromDb = getIdsFromDb()

    val ans = StringBuilder()
    ans.append("[")
    citiesIds.forEachIndexed { index, id ->
        ans.append("{lat: ${idsFromDb.getValue(id).latitude}, lng: ${idsFromDb.getValue(id).longitude}}")
        if (index < citiesIds.size) {
            ans.append(",")
        }
    }
    ans.append("]")

    println(ans.toString())
}

/**
 * Constructs a [Graph] of the cities and its distance of a given database.
 *
 * @return A graph filled with the values of the database.
 */
fun getIdsFromDb(): Map<Int, City> {
    val map = mutableMapOf<Int, City>()

    DbConnector.getCities().forEach { map[it.id] = it }

    return map
}
