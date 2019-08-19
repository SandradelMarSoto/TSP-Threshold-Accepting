@file:JvmName("Test")

package unam.ciencias.heuristicas

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

const val url = "jdbc:sqlite:db/tsp.db"

fun main() {
    var connection: Connection?
    var statement: Statement?

    try {
        connection = DriverManager.getConnection(url)
        connection.autoCommit = false

        statement = connection.createStatement()

        val resultSet = statement.executeQuery("SELECT * FROM connections WHERE id_city_1 = 3  AND id_city_2 = 11")

        if (resultSet.next()) {
            val a = resultSet.getDouble("distance")
            println(a)
        }

        resultSet.close()
        statement.close()
        connection.close()

    } catch (e: SQLException) {
        println(e.message)
    }
}