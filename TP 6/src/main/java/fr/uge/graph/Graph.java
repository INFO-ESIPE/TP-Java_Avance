package fr.uge.graph;

/**
 * An oriented graph with values on edges and not on nodes.
 */
public sealed interface Graph<T> permits MatrixGraph {
  /**
   * Returns the number of nodes of this graph.
   * @return the number of nodes of this graph.
   */
  int nodeCount();

  /**
   * Create a graph implementation based on a matrix.
   *
   * @param <T> type of the edge weight.
   * @param nodeCount the number of nodes.
   * @return a new implementation of Graph.
   */
  //createMatrixGraph(nodeCount)

  /**
   * Add an edge between two nodes or replace it if an edge already exists.
   *
   * @param src source node.
   * @param dst destination node.
   * @param weight weight of the edge.
   * @throws NullPointerException if weight is {@code null}.
   * @throws IndexOutOfBoundsException if src or dst is not a valid node number.
   */
  //addEdge(src, dst, weight)

  /**
   * Return the weight of an edge.
   *
   * @param src source ndoe.
   * @param dst destination nde.
   * @return the weight of the edge between {@code src}and {@code dst} or Optional.empty().
   * @throws IndexOutOfBoundsException if src or dst is not a valid node number.
   */
  //getWeight(src, dst)

  /**
   * Adds all the edge values of the graph taken as parameter to the current graph,
   * uses the {@code merger} if there is already a value to merge the value.
   *
   * @param graph a graph
   * @param merger the function to call if there are two values to merge.
   * @throws NullPointerException if either graph or merger is null.
   * @throws IllegalArgumentException if the graphs do not have the same number of nodes.
   */
  //mergeAll(graph, merger)

  /**
   * Returns all the nodes that are connected to the node taken as parameter.
   * The order of the nodes may be different that the insertion order.
   * @param src a node.
   * @return an iterator on all nodes connected to the specified source node.
   * @throws IndexOutOfBoundsException if src is not a valid node number.
   */
  //neighborIterator(src)

  /**
   * An edge of the graph.
   *
   * @param src the index of the source node.
   * @param dst the index of the destination node.
   * @param weight the weight associated to the edge.
   * @param <T> the type of the weight
   */
  // Edge

  /**
   * Call the consumer for each edge associated to the source node.
   *
   * @param src the source node.
   * @param function the function called for all edge that have src as source node.
   * @throws NullPointerException if consumer is null.
   * @throws IndexOutOfBoundsException if src is not a valid index for a node.
   */
  // forEachEdge(src, function)

  // Q9

  /**
   * Returns all the edges of the graph that have a value.
   *
   * @return all the edges of the graph that have a value in any order.
   */
  //edges()

  /**
   * Create a graph implementation based on a node map.

   * @param nodeCount the number of nodes
   * @return a new graph implementation
   * @param <T> type of the edge weight
   */
  //createNodeMapGraph(nodeCount)
}