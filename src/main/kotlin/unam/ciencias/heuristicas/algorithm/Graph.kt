package unam.ciencias.heuristicas.algorithm

import java.util.*


class Graph<T, E> {
    private val nodes = hashMapOf<T, Node<T, E>>()
    val edges = PriorityQueue<Edge<T>>(Comparator { o1, o2 -> o2.weight.compareTo(o1.weight) })

    fun addNode(a: T, info: E) {
        nodes[a] = Node(a, info, null)
    }

    fun getNodeInfo(a: T): E? = if (a in nodes) nodes[a]!!.info else null

    fun addEdge(a: T, b: T, weight: Double) {
        if (a in nodes && b in nodes) {
            if (nodes[a]!!.neighbors == null)
                nodes[a]!!.neighbors = hashMapOf()
            if (nodes[b]!!.neighbors == null)
                nodes[b]!!.neighbors = hashMapOf()

            nodes[a]!!.neighbors!![b] = weight
            nodes[b]!!.neighbors!![a] = weight

            edges.add(Edge(a, b, weight))
        } else {
            throw IllegalArgumentException("Tha graph doesn't contain such nodes")
        }
    }

    fun size(): Int = nodes.size

    fun hasEdge(a: T, b: T): Boolean =
        a in nodes && nodes[a]!!.neighbors != null && b in nodes[a]!!.neighbors!!

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

    fun inducedGraph(verticesSubSet: Collection<T>): Graph<T, E> {
        val graph = Graph<T, E>()

        verticesSubSet.forEach { graph.addNode(it, nodes[it]!!.info) }

        for (edge in edges) {
            if (edge.a in graph.nodes && edge.b in graph.nodes)
                graph.addEdge(edge.a, edge.b, edge.weight)

        }
        return graph
    }

    private data class Node<T, E>(
        val id: T,
        val info: E,
        var neighbors: HashMap<T, Double>?
    )

    data class Edge<T>(
        val a: T,
        val b: T,
        val weight: Double
    )

}