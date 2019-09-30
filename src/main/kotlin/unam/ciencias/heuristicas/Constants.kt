package unam.ciencias.heuristicas

/**
 * Variables for tuning our TSP system.
 *
 */
class Constants {
    companion object {

        /** TODO */
        const val EPSILON = 0.01
        /** TODO */
        const val EPSILON_P = 0.01
        /** Initial system temperature */
        const val T = 8.0
        /** Upper bound for iterations when calculating a batch */
        const val L: Int = 10e3.toInt()
        /** Cooling factor that determines how slow or fast the temperature [T] it's going to decrease */
        const val PHI = 0.5
        /** Earth radius in meters */
        const val EARTH_RADIUS_IN_METERS = 6373000.0
        /** Probability */
        const val P = 0.85
        /** Number of cities 1092 */
        const val NUM_CITIES = 1092
        /** TODO */
        const val ACCEPTED_NEIGHBORS = 100


    }
}

