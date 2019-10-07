package unam.ciencias.heuristicas

/**
 * Variables for tuning our TSP system.
 */
class Constants {
    companion object {
        /** Path of the database used */
        const val DB_URL = "jdbc:sqlite:db/tsp.db"

        /** Epsilon used in Threshold Accepting heuristic */
        const val EPSILON = 0.0001
        /** Epsilon used in the initial temperature algorithm */
        const val EPSILON_P = 0.0001
        /** Initial system temperature */
        const val T = 8.0
        /** Upper bound for iterations when calculating a batch */
        const val L = 2000
        /** Cooling factor that determines how slow or fast the temperature [T] it's going to decrease */
        const val PHI = 0.9
        /** Earth radius in meters */
        const val EARTH_RADIUS_IN_METERS = 6373000.0
        /** Percentage */
        const val P = 0.6
        /**  Value of accepted neighbors used when calculating the initial temperature. */
        const val ACCEPTED_NEIGHBORS = 100
        /** Maximum number of attempts when calculating a batch. */
        const val MAX_ITERATIONS = L * 42

    }
}

