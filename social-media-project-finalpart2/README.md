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
8. What is a Heap tree?
9. What is the difference between Min Heap and Max Heap?
10. What the differences between BFS(Breadth-first search) and DFS(Depth-First Search)?
11. What is the difference between the weighted and unweighted graphs? Explain with examples.
12. What is the difference between directed graphs and undirected graphs? Explain with examples.