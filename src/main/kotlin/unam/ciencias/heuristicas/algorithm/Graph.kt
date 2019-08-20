package unam.ciencias.heuristicas.algorithm

import java.util.*


class Graph<T, E> {
    private val nodes = hashMapOf<T, Node<T, E>>()
    //TODO: hacer que las distancias sea un par donde la promera entrada sea la distancia con un doubke
    //TODO: y la segunda que se las aristas
    val distancesMaxHeap = PriorityQueue<Double>(Collections.reverseOrder())

    fun addNode(a: T, info: E) {
        nodes[a] = Node(a, info, null)
    }

    fun addNodes(c: Collection<Pair<T, E>>) {
        c.forEach { addNode(it.first, it.second) }
    }

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
        throw IllegalAccessException("Tha graph doesn't contain such nodes")
    }

    fun hasEdge(a: T, b: T): Boolean =
        nodes.contains(a) && nodes[a]!!.neighbors != null && nodes[a]!!.neighbors!!.contains(b)

    fun edgeWeight(a: T, b: T): Double? =
        if (hasEdge(a, b)) nodes[a]!!.neighbors!![b]
        else null

    private data class Node<T, E>(
        val id: T,
        val info: E,
        var neighbors: HashMap<T, Double>?
    )

    private data class Edge<T>(
        val a: T,
        val b: T,
        val weight: Double
    )
}