package unam.ciencias.heuristicas

/**
 * TODO
 *
 */
class Constants {
    companion object {

        /** TODO */
        const val EPSILON = 10e-7
        /** TODO */
        const val EPSILON_P = 10e-7
        /** TODO */
        const val T = 8.0
        /** TODO */
        const val L: Double = 10e7
        /** TODO */
        const val PHI = 0.5
        /** TODO */
        const val EARTH_RADIUS_IN_METERS = 6373000.0

        /**
         * TODO
         *
         * @return
         */
        fun randomPercentage(): Double {
            var aux = Math.random()
            while (aux !in 0.85..0.95)
                aux = Math.random()
            return aux
        }

    }
}

