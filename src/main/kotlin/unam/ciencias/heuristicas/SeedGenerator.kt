@file:JvmName("SeedGenerator")

package unam.ciencias.heuristicas

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

