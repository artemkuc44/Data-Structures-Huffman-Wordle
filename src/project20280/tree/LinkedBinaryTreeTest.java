package project20280.tree;

import org.junit.jupiter.api.Test;
import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedBinaryTreeTest {

    @Test
    void testSize() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        Position<Integer> root = bt.addRoot(1);
        assertEquals(1, bt.size());

        Position<Integer> l = bt.addLeft(root, 2);
        Position<Integer> l2 = bt.addLeft(l, 3);



        bt.remove(l);
        assertEquals(2, bt.size());
    }

    @Test
    void testAddRoot() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        assertEquals(c, bt.root().getElement(), "root not added correctly");
    }

    @Test
    void testAddLeft() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addLeft(bt.root(), 1);
        assertEquals(1, bt.left(bt.root()).getElement());
    }

    @Test
    void testAddRight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        assertEquals(1, bt.right(bt.root()).getElement());
    }

    @Test
    void testRemove() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer c = Integer.parseInt("0");
        bt.addRoot(c);
        bt.addRight(bt.root(), 1);
        Integer old = bt.remove(bt.right(bt.root()));
        assertEquals(old, 1);
        assertEquals(1, bt.size());
    }
    @Test
    void testRemoveLeafNode() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(0);
        Position<Integer> rightChild = bt.addRight(bt.root(), 1);
        Integer old = bt.remove(rightChild);
        assertEquals(Integer.valueOf(1), old);
        assertEquals(1, bt.size());
        boolean contains = false;
        for (Position<Integer> pos : bt.positions()) {
            if (pos.getElement().equals(rightChild.getElement())) {
                contains = true;
                break;
            }
        }
        assertFalse(contains);
    }
    @Test
    void testRemoveNodeWithOneChild() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(0);
        Position<Integer> leftChild = bt.addLeft(bt.root(), 1);
        bt.addLeft(leftChild, 2);
        Integer old = bt.remove(leftChild);
        assertEquals(Integer.valueOf(1), old);
        assertEquals(2, bt.size());
        assertEquals(Integer.valueOf(2), bt.root.getLeft().getElement());
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(0);
        Position<Integer> root = bt.root();
        bt.addLeft(root, 1);
        bt.addRight(root, 2);
        assertThrows(IllegalArgumentException.class, () -> bt.remove(root));
    }

    @Test
    void testRemoveRootNodeWithOneChild() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(0);
        bt.addRight(bt.root(), 1);
        Integer old = bt.remove(bt.root());
        assertEquals(Integer.valueOf(0), old);
        assertEquals(1, bt.size());
        assertEquals(Integer.valueOf(1), bt.root().getElement());
    }

    @Test
    void testRemoveRootNodeNoChild() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.addRoot(0);
        Integer old = bt.remove(bt.root());
        assertEquals(Integer.valueOf(0), old);
        assertEquals(0, bt.size());
        assertNull(bt.root());
    }



    @Test
    void testToString() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
        BinaryTreePrinter btp = new BinaryTreePrinter<>(bt);
        System.out.println(btp.print());
    }

    @Test
    void testCreateLevelOrder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.toString());
    }

    @Test
    void testInorder() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);
        //System.out.println(bt.toString());
        assertEquals("[8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7]", bt.inorder().toString());
    }

    @Test
    void testDepth() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(0, bt.depth(bt.root()));
    }

    @Test
    void testHeight() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();

        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        bt.createLevelOrder(arr);

        assertEquals(3, bt.height(bt.root()));
    }
    @Test
    void testFindLeaves() {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

        // Populate the binary tree in a manner suitable for your implementation
        // This example assumes you have such a method to add elements
        bt.addRoot(1);
        var leftChild = bt.addLeft(bt.root(), 2);
        var rightChild = bt.addRight(bt.root(), 3);
        bt.addLeft(leftChild, 4);
        bt.addRight(leftChild, 5);
        bt.addLeft(rightChild, 6);
        bt.addRight(rightChild, 7);

        // Get the leaves using the public method that internally calls findLeaves
        ArrayList<LinkedBinaryTree.Node<Integer>> leaves = bt.getLeaves(); // Hypothetical public wrapper for findLeaves

        // Extract elements from the nodes for comparison
        ArrayList<Integer> leafElements = new ArrayList<>();
        for (LinkedBinaryTree.Node<Integer> leaf : leaves) {
            leafElements.add(leaf.getElement());
        }

        // Expected leaf nodes' elements
        ArrayList<Integer> expectedLeaves = new ArrayList<>(Arrays.asList(4, 5, 6, 7));

        // Assert the leaves are as expected
        assertEquals(expectedLeaves, leafElements, "Leaf nodes do not match the expected results.");
    }



}
