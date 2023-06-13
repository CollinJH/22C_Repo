
// Collin Hargreaves
// CIS 22C
// Professor Mirsaeid Abolghasemi
// 06/03/2024

public class BinarySearchTree<T extends Comparable<? super T>>
    extends BinaryTree<T> implements SearchTreeInterface<T> {

        public BinarySearchTree() {
            super();
        } // end default constructor

        public BinarySearchTree(T rootEntry) {
            super();
            setRootNode(new BinaryNode<>(rootEntry));
        } // end constructor

        private void setRootNode(BinaryNode<T> tBinaryNode) {
            root = tBinaryNode;
        }

        private BinaryNode<T> getRootNode() {
            return root;
        }

        public void setTree(T rootData) {
            throw new UnsupportedOperationException();
        }

        public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
            throw new UnsupportedOperationException();
        }

        public boolean contains(T entry) {
            return getEntry(entry) != null;
        }

        public T getEntry(T entry) {
            assert root != null;

            return findEntry(root, entry);
        }

        private T findEntry (BinaryNode<T> currentNode, T entry) {
            if (currentNode == null) {
                return null;
            }

            int comparison = entry.compareTo(currentNode.getData());

            if (comparison == 0) {
                return currentNode.getData();
            } else if (comparison < 0) {
                return findEntry(currentNode.getLeftChild(), entry);
            } else {
                return findEntry(currentNode.getRightChild(), entry);
            }
        }

        public T add(T newEntry) {
            if (getRootNode() == null) {
                setRootNode(new BinaryNode<T>(newEntry));
                return null;
            } else {
                return addEntry(root, newEntry);
            }
        }

        private T addEntry(BinaryNode<T> currentNode, T newEntry) {
            assert currentNode != null;
            T result = null;
            int comparison = newEntry.compareTo(currentNode.getData());

            if (comparison == 0) {
                result = currentNode.getData();
                currentNode.setData(newEntry);
            } else if (comparison < 0) {
                if (currentNode.hasLeftChild()) {
                    result = addEntry(currentNode.getLeftChild(), newEntry);
                } else {
                    currentNode.setLeftChild(new BinaryNode<>(newEntry));
                }
            } else {
                if (currentNode.hasRightChild()) {
                    result = addEntry(currentNode.getRightChild(), newEntry);
                } else {
                    currentNode.setRightChild(new BinaryNode<>(newEntry));
                }
            }

            return result;
        }

        public T remove (T entry) {
            ReturnObject oldEntry = new ReturnObject(null);
            BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
            setRootNode(newRoot);

            return oldEntry.get();
        }

        private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry) {
            
            if (rootNode == null) return null;

            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);

            if (comparison > 0) {
                rootNode.setRightChild(removeEntry(rootNode.getRightChild(), entry, oldEntry));
            } else if (comparison < 0) {
                rootNode.setLeftChild(removeEntry(rootNode.getLeftChild(), entry, oldEntry));
            } else {
                oldEntry.set(rootData);

                if (!rootNode.hasLeftChild() && !rootNode.hasRightChild()) {
                    rootNode = null;
                } else if (rootNode.hasRightChild()) {
                    rootNode.setData(getSuccessor(rootNode));
                    rootNode.setRightChild(removeEntry(rootNode.getRightChild(), rootNode.getData(), new ReturnObject(null)));
                } else {
                    rootNode.setData(getPredecessor(rootNode));
                    rootNode.setLeftChild(removeEntry(rootNode.getLeftChild(), rootNode.getData(), new ReturnObject(null)));
                }
            }

            return rootNode;
        }

        private T getSuccessor(BinaryNode<T> rootNode) {
            rootNode = rootNode.getRightChild();
            while (rootNode.hasLeftChild()) {
                rootNode = rootNode.getLeftChild();
            }

            return rootNode.getData();
        }

        private T getPredecessor(BinaryNode<T> rootNode) {
            rootNode = rootNode.getLeftChild();
            while (rootNode.hasRightChild()) {
                rootNode = rootNode.getRightChild();
            }

            return rootNode.getData();
        }

        private class ReturnObject {
            private T item;

            private ReturnObject(T entry) {
                item = entry;
            }

            public T get() {
                return item;
            }

            public void set(T entry) {
                item = entry;
            }
        }

}
