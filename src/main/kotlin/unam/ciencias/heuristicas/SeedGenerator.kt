/**
 * Script for generating `n` random seeds.
 *
 * It is useful because we can copy its output to a file for testing different seeds in our
 * system, in this way, we avoid just picking numbers randomly.
 *
 * It is executed by a Gradle task.
 */
@file:JvmName("SeedGenerator")

package unam.ciencias.heuristicas

/** Upper bound number for generating numbers from 0 to that number */
const val UPPER_BOUND_NUM = 104711

fun main(args: Array<String>) {
    val nSeeds = args[0].toInt()
    val seeds = mutableSetOf<Int>()

    while (seeds.size != nSeeds) {
        val x = (0..UPPER_BOUND_NUM).random()
        seeds.add(x)
    }

    val res = seeds.fold("", { acc, i -> "$acc,$i" }).substring(1)
    println(res)
}
