@file:JvmName("Test")

package unam.ciencias.heuristicas

import unam.ciencias.heuristicas.data.DbConnector


fun main() {
    println(DbConnector.getCities())
    //println(DbConnector.getConnectionsBetweenTwoCities())
}