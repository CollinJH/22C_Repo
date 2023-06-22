// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 06/15/23

import java.util.Iterator;
import java.util.Stack;

public class DirectedGraph <T> implements GraphInterface <T> {
    private DictionaryInterface <T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    } // end default constructor

    protected void resetVertices() {
        Iterator <VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unvisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } // end while

    } // end reset vertices

    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        // assumes graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); // enqueue vertex label
        vertexStack.push(originVertex); // enqueue vertex

        while (!vertexStack.isEmpty()) {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if (nextNeighbor != null) {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            } else {
                vertexStack.pop();
            } // all neighbors visited
        } // end while

        return traversalOrder;
    } // end getDepthFirstTraversal

    /**
     * Performs a breadth-first traversal of this graph.
     *
     * @param origin An object that labels the origin vertex of the traversal.
     * @return A queue of labels of the vertices in the traversal, with
     * the label of the origin vertex at the queue's front.
     */
    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {

        resetVertices();

        QueueInterface<T> queueOrder = new LinkedQueue<>();
        QueueInterface<VertexInterface<T>> secondaryQueue = new LinkedQueue<>();

        VertexInterface<T> oVertex = vertices.getValue(origin);
        secondaryQueue.enqueue(oVertex);

        Iterator<VertexInterface<T>> iter;

        do {
            if (secondaryQueue.getFront().isVisited()) secondaryQueue.dequeue();
            else {
                iter = secondaryQueue.getFront().getNeighborIterator();

                while (iter.hasNext()) secondaryQueue.enqueue(iter.next());

                secondaryQueue.getFront().visit();

                queueOrder.enqueue(secondaryQueue.dequeue().getLabel());
            }

        } while (!secondaryQueue.isEmpty());


        return queueOrder;
    }

    public StackInterface<T> getTopologicalOrder() {
        resetVertices();

        StackInterface <T> vertexStack = new LinkedStack<>();
        int numberOfVertices = getNumberOfVertices();
        for (int c = 1; c <= numberOfVertices; c++) {
            VertexInterface <T> nextVertex = findTerminal();
            nextVertex.visit();
            vertexStack.push(nextVertex.getLabel());
        } // end for

        return vertexStack;
    } // end topological order

    /** precondition: path is an empty stack (NOT null) */
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        resetVertices();
        boolean done = false;
        QueueInterface <VertexInterface<T>> vertexQueue = new LinkedQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        originVertex.visit();

        // Assertion: resetVertices() has executed setCost(0)
        // and setPredecessor(null) for originVertex

        vertexQueue.enqueue(originVertex);

        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface <T> frontVertex = vertexQueue.dequeue();

            Iterator <VertexInterface <T>> neighbors = frontVertex.getNeighborIterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface <T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(1 + frontVertex.getCost());
                    nextNeighbor.setPredecessor(nextNeighbor);
                } // end if

                if (nextNeighbor.equals(endVertex)) {
                    done = true;
                }
            } // end while
        } // end while

        // traversal ends; construct shortest path

        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathLength;
    } // end getShortestPath

    /** Precondition: path is an empty stack (NOT null) */
    public double getCheapestPath(T begin, T end, StackInterface<T> path)
    {
        resetVertices();
        boolean done = false;

    // Use EntryPQ instead of Vertex because multiple entries contain
    // the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
        PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();

        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!done && !priorityQueue.isEmpty())
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if (!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());

                if (frontVertex.equals(endVertex))
                    done = true;
                else
                {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
                    while (neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();

                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
                        } // end if
                    } // end while
                } // end if
            } // end if
        } // end while

// Traversal ends, construct cheapest path
        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        } // end while

        return pathCost;
    } // end getCheapestPath

    protected VertexInterface<T> findTerminal()
    {
        boolean found = false;
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

        while (!found && vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();

// If nextVertex is unvisited AND has only visited neighbors)
            if (!nextVertex.isVisited())
            {
                if (nextVertex.getUnvisitedNeighbor() == null )
                {
                    found = true;
                    result = nextVertex;
                } // end if
            } // end if
        } // end while

        return result;
    } // end findTerminal

    // Used for testing
    public void displayEdges()
    {
        System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
        System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
        Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
        while (vertexIterator.hasNext())
        {
            ((Vertex<T>)(vertexIterator.next())).display();
        } // end while
    } // end displayEdges

    /**
     * Adds a given vertex to this graph.
     *
     * @param vertexLabel An object that labels the new vertex and is
     *                    distinct from the labels of current vertices.
     * @return True if the vertex is added, or false if not.
     */
    @Override
    public boolean addVertex(T vertexLabel) {
        vertices.add(vertexLabel, new Vertex<>(vertexLabel));
        return true;
    }

    /**
     * Adds a weighted edge between two given distinct vertices that
     * are currently in this graph. The desired edge must not already
     * be in the graph. In a directed graph, the edge points toward
     * the second vertex given.
     *
     * @param begin      An object that labels the origin vertex of the edge.
     * @param end        An object, distinct from begin, that labels the end
     *                   vertex of the edge.
     * @param edgeWeight The real value of the edge's weight.
     * @return True if the edge is added, or false if not.
     */
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        VertexInterface<T> bVertex = vertices.getValue(begin);
        VertexInterface<T> eVertex = vertices.getValue(end);

        bVertex.connect(eVertex, edgeWeight);

        edgeCount++;

        return true;
    }


    /**
     * Adds an unweighted edge between two given distinct vertices
     * that are currently in this graph. The desired edge must not
     * already be in the graph. In a directed graph, the edge points
     * toward the second vertex given.
     *
     * @param begin An object that labels the origin vertex of the edge.
     * @param end   An object, distinct from begin, that labels the end
     *              vertex of the edge.
     * @return True if the edge is added, or false if not.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        VertexInterface<T> bVertex = vertices.getValue(begin);
        VertexInterface<T> eVertex = vertices.getValue(end);

        bVertex.connect(eVertex);

        edgeCount++;

        return true;
    }

    /**
     * Sees whether an edge exists between two given vertices.
     *
     * @param begin An object that labels the origin vertex of the edge.
     * @param end   An object that labels the end vertex of the edge.
     * @return True if an edge exists.
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        VertexInterface<T> bVertex = vertices.getValue(begin);
        VertexInterface<T> eVertex = vertices.getValue(end);

        Iterator<VertexInterface<T>> iter = bVertex.getNeighborIterator();

        while (iter.hasNext()) if (iter.next().equals(eVertex)) return true;

        return false;
    }

    private class EntryPQ implements Comparable<EntryPQ>
    {
        private VertexInterface<T> vertex;
        private VertexInterface<T> previousVertex;
        private double cost; // cost to nextVertex

        private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex)
        {
            this.vertex = vertex;
            this.previousVertex = previousVertex;
            this.cost = cost;
        } // end constructor

        public VertexInterface<T> getVertex()
        {
            return vertex;
        } // end getVertex

        public VertexInterface<T> getPredecessor()
        {
            return previousVertex;
        } // end getPredecessor

        public double getCost()
        {
            return cost;
        } // end getCost

        public int compareTo(EntryPQ otherEntry)
        {
        // Using opposite of reality since our priority queue uses a maxHeap;
        // could revise using a minheap
            return (int)Math.signum(otherEntry.cost - cost);
        } // end compareTo

        public String toString()
        {
            return vertex.toString() + " " + cost;
        } // end toString
    } // end EntryPQ

    /**
     * Sees whether this graph is empty.
     *
     * @return True if the graph is empty.
     */
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /**
     * Gets the number of vertices in this graph.
     *
     * @return The number of vertices in the graph.
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.getSize();
    }

    /**
     * Gets the number of edges in this graph.
     *
     * @return The number of edges in the graph.
     */
    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    /**
     * Removes all vertices and edges from this graph.
     */
    @Override
    public void clear() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    }

}
