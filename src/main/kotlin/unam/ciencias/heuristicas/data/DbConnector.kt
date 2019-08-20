package unam.ciencias.heuristicas.data

import unam.ciencias.heuristicas.model.City
import unam.ciencias.heuristicas.model.CityConnection
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

private const val dbUrl = "jdbc:sqlite:db/tsp.db"

class DbConnector {

    companion object {
        fun getCity(id: Int): City? {
            val connection: Connection?
            val statement: Statement?
            var city: City? = null

            try {
                connection = DriverManager.getConnection(dbUrl)
                connection.autoCommit = false

                statement = connection.createStatement()
                val resultSet = statement.executeQuery("SELECT * FROM cities WHERE id = $id")

                if (resultSet.next()) {
                    val name = resultSet.getString("name")
                    val country = resultSet.getString("country")
                    val population = resultSet.getInt("population")
                    val latitude = resultSet.getDouble("latitude")
                    val longitude = resultSet.getDouble("longitude")

                    city = City(id, name, country, population, latitude, longitude)
                }

                resultSet.close()
                statement.close()
                connection.close()
            } catch (e: SQLException) {
                println(e.message)
            }

            return city
        }

        fun getConnectionBetweenTwoCities(id1: Int, id2: Int): CityConnection? {
            val connection: Connection?
            val statement: Statement?
            var cityConnection: CityConnection? = null

            try {
                connection = DriverManager.getConnection(dbUrl)
                connection.autoCommit = false

                statement = connection.createStatement()
                val resultSet =
                    statement.executeQuery("SELECT * FROM connections WHERE id_city_1 = $id1 AND id_city_2 = $id2")

                if (resultSet.next()) {
                    val distance = resultSet.getDouble("distance")

                    cityConnection = CityConnection(id1, id1, distance)
                }

                resultSet.close()
                statement.close()
                connection.close()
            } catch (e: SQLException) {
                println(e.message)
            }

            return cityConnection
        }
    }

}