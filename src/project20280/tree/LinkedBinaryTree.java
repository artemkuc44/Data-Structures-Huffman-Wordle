package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;


/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        // Direct construction of Tree
        Position<Integer> root = bt.addRoot(12);
        Position<Integer> p1 = bt.addLeft(root, 25);
        Position<Integer> p2 = bt.addRight(root, 31);

        Position<Integer> p3 = bt.addLeft(p1, 58);
        bt.addRight(p1, 36);

        Position<Integer> p5 = bt.addLeft(p2, 42);
        bt.addRight(p2, 90);

        Position<Integer> p4 = bt.addLeft(p3, 62);
        bt.addRight(p3, 75);

        System.out.println("bt inorder: " + bt.size() + " " + bt.inorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.preorder());
        System.out.println("bt preorder: " + bt.size() + " " + bt.postorder());

        System.out.println(bt.toBinaryTreeString());


    }

    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        // TODO
        if(this.root != null){
            throw new IllegalStateException("Root already exists");
        }
        root = new Node<>(e,null,null,null);
        size =1;
        return root;
    }

    public void insert(E e) {
        root = addRecursive(root, e);
    }

    // Recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {


        if(p == null){
            addRoot(e);
            return new Node<>(e,p,null,null);
        }
        int comparison = ((Comparable<? super E>) e).compareTo(p.getElement());

        if(comparison > 0){
            p.setRight(addRecursive(p.getRight(),e));
        }else if(comparison < 0){
            p.setLeft(addRecursive(p.getLeft(),e));
        }
        size++;
        return p;

    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> Parent = validate(p);
        if(Parent.getLeft() != null){
            throw new IllegalArgumentException("left node already exists");
        }

        Node<E> newNode = new Node<>(e,Parent,null,null);
        Parent.setLeft(newNode);

        size++;

        return newNode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        Node<E> Parent = validate(p);
        if(Parent.getRight() != null){
            throw new IllegalArgumentException("right node already exists");
        }

        Node<E> newNode = new Node<>(e,Parent,null,null);
        Parent.setRight(newNode);

        size++;

        return newNode;    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        if(p == null){
            throw new IllegalArgumentException("Node doesnt exist");
        }
        Node<E> pnode = validate(p);
        E temp = pnode.getElement();
        pnode.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p);
        if(isInternal(p) )throw new IllegalArgumentException("P must be external");
        size = t1.size() + t2.size();
        if(!t1.isEmpty()){
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if(!t2.isEmpty()){
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }

    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        // TODO
        Node<E> node = validate(p);
        if(numChildren(p) == 2){
            throw new IllegalArgumentException("p have 2 children and be removed");
        }
        Node<E> child = (node.getLeft()!= null?node.getLeft():node.getRight());
        if(child!=null){
            child.setParent(node.getParent());
        }
        if(node == root){
            root = child;
        }else{
            Node<E> parent = node.getParent();
            if(node == parent.getLeft()){
                parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }

        size --;
        E temp = node.getElement();
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node);

        return temp;
    }

    public String toString () {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        inorderTraversal(root,sb,count);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return (sb.toString());


    }

    // Recursive inorder traversal method
    private void inorderTraversal(Node<E> node, StringBuilder sb,int count) {
        count++;
        if (node == null) {
            return;
        }


        inorderTraversal(node.getLeft(), sb,count); // Traverse left subtree
        if(count == size-1){
            sb.append(node.getElement()); // Visit node
        }else{
            sb.append(node.getElement()).append(", ");

        }
        inorderTraversal(node.getRight(), sb,count); // Traverse right subtree

    }




    public void createLevelOrder(ArrayList<E> list) {
        root = createLevelOrderHelper(list, null, 0);
    }

    private Node<E> createLevelOrderHelper(ArrayList<E> list, Node<E> parent, int index) {
        if (index < list.size()) {
            Node<E> node = new Node<>(list.get(index), parent, null, null);
            node.left = createLevelOrderHelper(list, node, 2 * index + 1);
            node.right = createLevelOrderHelper(list, node, 2 * index + 2);
            size++; // Increment size for each node added
            return node;
        }
        return null;
    }


    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> parent, int i) {

        // Base case: if index is out of bounds of the array
        if (i < arr.length) {
            Node<E> temp = new Node<>(arr[i], parent, null, null);
            // Recursive case: construct left and right subtrees
            temp.left = createLevelOrderHelper(arr, temp, 2 * i + 1); // Left child
            temp.right = createLevelOrderHelper(arr, temp, 2 * i + 2); // Right child
            size++;
            return temp;
        }
        return null;
    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }



    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }





    // Method to print the binary tree
    public void printBinaryTree(E[] arr) {
        this.root = createLevelOrderHelper(arr, null, 0); // Populate the tree
        int height = height(root);
        printLevels(root, height);
    }

    // Helper method to print all levels of the tree
    private void printLevels(Node<E> root, int height) {
        for (int i = 1; i <= height; i++) {
            printGivenLevel(root, i, height - i);
            System.out.println(); // Move to the next line after printing each level
        }
    }

    // Method to print nodes at a given level
    private void printGivenLevel(Node<E> root, int level, int indentSpaces) {
        if (root == null) {
            printSpaces(indentSpaces);
            System.out.print(" ");
            printSpaces(indentSpaces);
            return;
        }
        if (level == 1) {
            printSpaces(indentSpaces);
            System.out.print(root.getElement());
            printSpaces(indentSpaces);
        } else if (level > 1) {
            int nextIndent = (indentSpaces - 1) / 2; // Compute the indent for the child nodes
            printGivenLevel(root.getLeft(), level - 1, nextIndent);
            printGivenLevel(root.getRight(), level - 1, nextIndent);
        }
    }

    // Method to print spaces
    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    // Method to calculate the height of the tree
    private int height(Node<E> node) {
        if (node == null) return 0;
        else {
            int lHeight = height(node.getLeft());
            int rHeight = height(node.getRight());
            return (lHeight > rHeight) ? (lHeight + 1) : (rHeight + 1);
        }
    }

}
