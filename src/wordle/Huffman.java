package wordle;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;
import project20280.tree.BinaryTreePrinter;
import project20280.tree.LinkedBinaryTree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Huffman {

    String fileName = "wordle/resources/dictionary.txt";
    //String fileName = "wordle/resources/extended-dictionary.txt";
    //String fileName = "wordle/resources/testDict.txt";
    project20280.hashtable.ChainHashMap<String,String> letterCodes;

    project20280.tree.LinkedBinaryTree<HuffmanNode> huffmanTree;


    public Huffman(String data){

        letterCodes = new project20280.hashtable.ChainHashMap<>();
        huffmanTree = compress(data);
        generateCodes(huffmanTree.root(),"",this.letterCodes);
        letterCodes.printHashMap();
        this.printTree(data);
    }

    class HuffmanNode implements Comparable<HuffmanNode>{
        String data;
        int frequency;
        HuffmanNode left, right;

        public HuffmanNode(String data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }

        public HuffmanNode getLeft() {
            return left;
        }

        public HuffmanNode getRight() {
            return right;
        }


        @Override
        public int compareTo(HuffmanNode o) {
            return this.frequency - o.frequency;
        }

        @Override
        public String toString() {
            return data;
        }

    }


    public project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode> compress(String X) {
        project20280.hashtable.ChainHashMap<String, Integer> freqMap = getLetterFreq(X);

        project20280.priorityqueue.HeapPriorityQueue<Integer, project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode>> Q =
                new project20280.priorityqueue.HeapPriorityQueue<>();

        for (String c : freqMap.keySet()) {
            project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode> T = new project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode>();
            T.addRoot(new Huffman.HuffmanNode(c, freqMap.get(c)));
            Q.insert(freqMap.get(c), T);
        }

        while (Q.size() > 1) {
            var e1 = Q.removeMin();
            var e2 = Q.removeMin();

            LinkedBinaryTree<HuffmanNode> T1 = e1.getValue();
            LinkedBinaryTree<HuffmanNode> T2 = e2.getValue();
            LinkedBinaryTree<HuffmanNode> combinedTree = new LinkedBinaryTree<>();
            String combinedData = T1.getRoot().getElement().data + T2.getRoot().getElement().data;
            Position<HuffmanNode> root = combinedTree.addRoot(
                    new HuffmanNode(combinedData, e1.getKey() + e2.getKey()));
            combinedTree.attach(root, T1, T2);

            Q.insert(root.getElement().frequency, combinedTree);
        }

        project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode> huffmanTree = Q.removeMin().getValue();
        return huffmanTree;
    }


    public void printTree(String X) {
        BinaryTree<HuffmanNode> huffmanTree = compress(X);
        BinaryTreePrinter<HuffmanNode> printer = new BinaryTreePrinter<>(huffmanTree);
        System.out.println(printer.print());
    }

    private void generateCodes(Position<HuffmanNode> position, String code, ChainHashMap<String, String> letterCodes) {
        if (position == null) {
            return;
        }

        HuffmanNode hNode = position.getElement();

        if (huffmanTree.isExternal(position) && hNode.data.length() == 1) {
            letterCodes.put(hNode.data, code);
        } else {
            if (huffmanTree.left(position) != null) {
                generateCodes(huffmanTree.left(position), code + "0", letterCodes);
            }
            if (huffmanTree.right(position) != null) {
                generateCodes(huffmanTree.right(position), code + "1", letterCodes);
            }
        }
    }


    public project20280.hashtable.ChainHashMap<String, Integer> getLetterFreq(String X) {

        project20280.hashtable.ChainHashMap<String, Integer> frequenciesMap = new project20280.hashtable.ChainHashMap<>();
        for (char c : X.toCharArray()) {
            String key = String.valueOf(c);
            frequenciesMap.put(key, frequenciesMap.getOrDefault(key, 0) + 1);
        }

        return frequenciesMap;
    }

    public project20280.hashtable.ChainHashMap<String,String> getLetterCodes() {
        return letterCodes;
    }

    public static void main(String[] args) {
        Wordle game = new Wordle();
        Huffman huff = new Huffman(String.join("", game.dictionary));
        //Huffman test = new Huffman("BACADAEAFABBAAAGAH");
        //Huffman tes2 = new Huffman("ttttttttttttttttttttttaaaaaaaaaaaaaaaaaaaaooooooooooooooocccccccccccsssssssssseeeeeeeeeemmmmmmmmdduu");
        //test.printTree("BACADAEAFABBAAAGAH");
        //huff.printTree(String.join(" ", game.dictionary));
    }




}
