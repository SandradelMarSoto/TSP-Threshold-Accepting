@file:JvmName("Test")

package unam.ciencias.heuristicas

import unam.ciencias.heuristicas.data.DbConnector


fun main() {
    println(DbConnector.getCity(1))
    println(DbConnector.getConnectionBetweenTwoCities(3,11))
}