// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 06/23/2023

Final Project Part 1

Incorporating the Graph Data Structure into our previous social network project.

1.What data structures did you use in your code and why?
I used an undirected graph, this is because, typically in a social network if you add someone to a friends list, it means they added you back.
This type of behavior is easily implemented with an undirected graph.
2. You should show me in your code where you used these data structures and where you created objects of their classes. (You should explain your code and I do not accept if you say somebody else did this part of the code).
3. What is the process of adding a node in a BST(Binary Search Tree)?
First start at the root of the BST,
If the tree is empty, we create a new node with the given value and call it the root of the BST
If not empty, we must compare the value of the new node to the value of the current node
If newNode value is less than currentNode value, we move it to the left subtree
If the left subtree is empty, create a new node with the given value and make it a left child of the current node
Now if newNode is greater than currentNode we move to the right subtree
if empty, we create a new node with the value and make it a right child of the currentNode
continue this process recursively for as long as you need
4. What is the process of removing a node in a BST?
Start at the root of the node
check if the tree is empty, if it is there is no value to remove
if the tree is not empty, we compare the value of the node to be removed to the value of the currentNode
if the value of removeNode is less than currentNode we move to the left subtree
if removeNode is greater, move to the right
when we find the value we check for stuff
if removeNode has no children, simply remove it
if removeNode has one child, replace the node with its child
if removeNode has two children, we need to find the successor and predecessor
continue this process until the node is removed
5. What is a complete tree?
a special type of binary tree, in which all levels, except possibly the last are filled
its a way to allocate nodes to a tree so that it is perfectly balanced
6. What is an AVL tree?
essentially a self balanced binary tree, maintains a property called the AVL balance factor for each node
this allows the tree to remain balanced at all times
7. What is the similarity and difference between an AVL and a BST?
AVL trees specifically maintain balance for each node using a balance factor
this is the difference between the heigh of the left and right subtree
AVL trees also use operations to maintain balance after insertion and deletion.
BST will not perform automatic balancing
AVL tree operations have a guarenteed worst case time of O(log n) due to the balancing property
BST tree operations have an average worst time of O(log n) but can end up O(n) for the worst
8. What is a Heap tree?
This is essentially a binary heap tree, which satisfies the heap property
for every node in a tree, the value of the node is either greater than or equal to in a max heap
or less than or equal to in a min heap
9. What is the difference between Min Heap and Max Heap?
In in a max heap the value of each node is greater than or equal to the value of its children
in a min heap the value of each node is less than or equal to the value of its children
10. What the differences between BFS(Breadth-first search) and DFS(Depth-First Search)?
Breadth-first search explores all neighboring nodes at current depth level before movin to the next
Depth-first search will explore as far as possible before backtracking up
11. What is the difference between the weighted and unweighted graphs? Explain with examples.
Unweighted Graphs
    each edge is considered to have a equal weight
    edges of unweighted graphs are represented by simple connections
    consider a social network where each person is a node or vertex and connections between each person represents friendships
    the edges between each person would only consider if they are in facts friends or not, with no weight
    there would be no additional attribute like closeness
Weighted Graphs
    each edge is associated with a weight
    each weight can represent a different factor
    used for relationships with various degrees
    consider a transportation network, the weighted edges could refer to the distance to each individual city
12. What is the difference between directed graphs and undirected graphs? Explain with examples.
Directed Graphs
    Edges do not have a specific direction to them
    represented by simple lines or connections
    undirected graphs are used to represent symetric relationships, like adding friends on a social network
Directed Graphs
    Edges represent a way one street
    relationship depends on the direction of traversal
    a webpage network that consists of nodes and the edges can represent hyperlinks between pages
    the edges would represent the direction of those hyperlinks