package unam.ciencias.heuristicas.algorithm

import java.util.*


class Graph<T, E> {
    private val nodes = hashMapOf<T, Node<T, E>>()
    val distancesMaxHeap = PriorityQueue<Double>(Collections.reverseOrder())

    fun addNode(a: T, info: E) {
        nodes[a] = Node(a, info, null)
    }

    fun addNodes(c: Collection<Pair<T, E>>) = c.forEach { addNode(it.first, it.second) }

    fun getNodeInfo(a: T): E? = if (nodes.contains(a)) nodes[a]!!.info else null

    fun addEdge(a: T, b: T, weight: Double) {
        if (nodes.contains(a) and nodes.contains(b)) {
            nodes[a]?.apply {
                neighbors = hashMapOf()
                neighbors!![b] = weight
            }
            nodes[b]?.apply {
                neighbors = hashMapOf()
                neighbors!![a] = weight
            }

            distancesMaxHeap.add(weight)
        }
        throw IllegalArgumentException("Tha graph doesn't contain such nodes")
    }

    fun size(): Int = nodes.size

    fun hasEdge(a: T, b: T): Boolean =
        nodes.contains(a) && nodes[a]!!.neighbors != null && nodes[a]!!.neighbors!!.contains(b)

    fun edgeWeight(a: T, b: T): Double? =
        if (hasEdge(a, b)) nodes[a]!!.neighbors!![b]
        else null

    fun existSuchPath(path: ArrayList<T>): Boolean {
        for (i in 0 until path.size - 1) {
            if (!hasEdge(path[i], path[i + 1]))
                return false
        }
        return true
    }

    private data class Node<T, E>(
        val id: T,
        val info: E,
        var neighbors: HashMap<T, Double>?
    )

/*
    private data class Edge<T>(
        val a: T,
        val b: T,
        val weight: Double
    )
*/
}