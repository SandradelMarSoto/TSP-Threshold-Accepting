package unam.ciencias.heuristicas

class Constants {
    companion object {

        /** TODO */
        const val EPSILON = 10e-7
        /** TODO */
        const val EPSILON_P = 10e-7
        /** TODO */
        const val T = 8
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
            //FIXME: [0.85, 0.95]
            return Math.random()
        }

    }
}

