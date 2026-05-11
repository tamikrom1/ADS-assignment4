# Assignment 4: Graph Traversal and Representation System

## A. Project Overview
A graph is a non-linear data structure defined as a pair $G = (V, E)$, consisting of a set of vertices (or nodes) $V$ representing entities, and a set of edges $E$ mapping relationships or pathways between them.

* **Vertices (Nodes):** Fundamental data objects housing unique identity attributes (IDs).
* **Edges:** Connections that bind pairs of vertices, structurally defining directed routes in this project.
* **Breadth-First Search (BFS):** A traversal strategy that prioritizes exploration horizontally, scanning all direct neighbors of a state before diving deeper.
* **Depth-First Search (DFS):** A backtracking-based strategy that dives deep into vertical paths, tracing a single path until its terminal node before stepping backward.


## B. Class Descriptions
The system is built entirely on Object-Oriented Principles to model abstract concepts accurately:

* **`Vertex`:** Contains a private `id` and manages unique identity constraints.
* **`Edge`:** Encapsulates relationship structures between a distinct `source` and `destination` vertex.
* **`Graph`:** Implements an **Adjacency List** using Java's `Map<Integer, List<Edge>>`. Adjacency lists optimize spatial footprint relative to sparse matrices, bringing edge lookup costs down to linear relationships.
* **`Experiment`:** Handles generation of isolated graphs, manages benchmarking using `System.nanoTime()`, and displays comparison summaries.


## C. Algorithm Descriptions

### 1. Breadth-First Search (BFS)
* **Mechanics (Step-by-Step):**
  1) Initialize an empty queue and a boolean tracking set of visited nodes.
  2) Enqueue the starting vertex and mark it as visited.
  3) While the queue contains elements:
     * Dequeue the head element and inspect it.
     * Identify all unvisited neighboring nodes from the adjacency list.
     * Mark those neighbors as visited and enqueue them sequentially.
  4) Terminate when the queue is exhausted.
* **Use Cases:** Best-suited for shortest-path calculation in unweighted graph structures (e.g., GPS mapping or network routing hops).
* **Time Complexity:** $O(V + E)$, as every vertex is processed exactly once, and every adjacent edge is scanned.

### 2. Depth-First Search (DFS)
* **Mechanics (Step-by-Step):**
  1) Allocate a tracking structure for visited nodes.
  2) Pass the starting vertex into a recursive helper function.
  3) In the helper:
     * Mark the current vertex as visited.
     * Recursively call the helper on each unvisited neighbor.
  4) Trace the recursion back to previous decision vertices once path ends are encountered.
* **Use Cases:** Topological sorting, solving maze-like puzzles, detecting cycles, or checking structural connectivity.
* **Time Complexity:** $O(V + E)$, since it traverses every connected vertex and adjacent edge exactly once.


## D. Experimental Results

| Graph Size | BFS Execution Time (ns) | DFS Execution Time (ns) |
| :--- | :--- | :--- |
| **Small (10 Vertices)** | 7,128,100 | 1,933,200 |
| **Medium (30 Vertices)** | 1,781,000 | 1,545,800 |
| **Large (100 Vertices)** | 8,373,200 | 5,895,300 |

### Observations and Patterns
1. **Initial Overhead and Warmup:** The "Small" graph run records higher times because of JVM initialization costs, class-loading overhead, and JIT compiler activities. Sub-sequent medium and large runs illustrate actual execution trends much more cleanly once the virtual machine settles.
2. **Comparative Speeds:** Depth-First Search consistently runs faster in this environment. Since BFS leverages dynamic Java `Queue` implementations (`LinkedList`), it pays a runtime allocation premium. DFS is managed on the local stack frame, introducing less heap management overhead.
3. **Complexity Matching:** Both algorithms scale linearly according to their vertices and edges, confirming that empirical runs closely match the theoretical bounds of $O(V+E)$.


## E. Benchmarking Screenshots

### Graph Structure Output
<img width="276" height="220" alt="image" src="https://github.com/user-attachments/assets/f15c8634-034f-4b4c-a4b5-154b6b82f59f" />

### BFS Traversal Output
<img width="195" height="173" alt="image" src="https://github.com/user-attachments/assets/129c8be5-d988-43a1-adab-c45382c3a013" />

### DFS Traversal Output
<img width="190" height="176" alt="image" src="https://github.com/user-attachments/assets/d74e4a4c-bb44-4082-9971-801ca6bf358f" />

### Performance Results Table
<img width="472" height="103" alt="image" src="https://github.com/user-attachments/assets/ae99e66c-7376-4872-ade2-3040a60aca3b" />



## F. Reflection Section
This assignment highlights how selecting structural designs changes performance. Developing a graph model using adjacency lists showed the balance between memory footprint and access speed. Unlike adjacency matrices, which take $O(V^2)$ space, using an adjacency list keeps memory consumption linear relative to edges and nodes. This difference becomes crucial as graph instances scale up.

Comparing BFS and DFS also clarified their trade-offs. While DFS is clean to write using recursion, it presents real-world risks like `StackOverflowError` on deep, sparse graphs. BFS avoids this with iterative queues, but it requires a larger memory footprint to hold entire frontier layers. Seeing these theoretical differences play out in actual execution times makes the abstract complexity equations feel tangible.
