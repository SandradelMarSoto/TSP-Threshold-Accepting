package unam.ciencias.heuristicas.algorithm

class Batch<T>(val limit: Double, var solution: Solution<T>, val tsp: TSP<T>?) {

    val solutions = arrayListOf<Solution<T>>()

    //FIXME: hay un error en la función objetivo
    fun calculateBatch(temperature: Temperature, solution: Solution<T>): Pair<Double, Solution<T>> {
        var c = 0
        var r = 0
        while (c < Constants.T) {
            var s = solution.generateNeighbor()
            if (tsp!!.costFunction() < tsp!!.costFunction() + Constants.T) {

                this.solution = s!!
                c++
                r += 0
            }
        }
        return Pair(r / Constants.L, solution)
    }

    companion object {}
}