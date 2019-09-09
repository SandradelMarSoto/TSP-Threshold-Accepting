package unam.ciencias.heuristicas

class Constants {
    companion object {

        const val EPSILON = 10e-7
        const val T = 8
        const val L: Double = 10e7
        const val PHI = 0.5
        const val EARTH_RADIUS_IN_METERS = 6373000.0


        fun randomPercentage() : Double{
            //FIXME: [0.85, 0.95]
            return Math.random()
        }
        const val PERCENTAGE =1
    }
}

