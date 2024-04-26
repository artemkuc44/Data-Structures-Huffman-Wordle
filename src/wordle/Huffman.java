package wordle;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.BinaryTree;
import project20280.interfaces.Entry;
import project20280.interfaces.Position;
import project20280.tree.BinaryTreePrinter;
import project20280.tree.LinkedBinaryTree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Huffman {
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
        project20280.hashtable.ChainHashMap<String, Integer> freqMap = getLetterFreq(X);//create letter freq map

        project20280.priorityqueue.HeapPriorityQueue<Integer, project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode>> Q =
                new project20280.priorityqueue.HeapPriorityQueue<>();

        for (String c : freqMap.keySet()) {
            project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode> T = new project20280.tree.LinkedBinaryTree<Huffman.HuffmanNode>();
            T.addRoot(new Huffman.HuffmanNode(c, freqMap.get(c)));//use car to crate single node bin tree
            Q.insert(freqMap.get(c), T);////insert to q with frequency as key
        }

        while (Q.size() > 1) {
            var e1 = Q.removeMin();
            var e2 = Q.removeMin();

            project20280.tree.LinkedBinaryTree<HuffmanNode> T1 = e1.getValue();
            project20280.tree.LinkedBinaryTree<HuffmanNode> T2 = e2.getValue();
            project20280.tree.LinkedBinaryTree<HuffmanNode> combinedTree = new project20280.tree.LinkedBinaryTree<>();
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
    public double calculateCompressionRatio(String originalData) {
        // Calculate the size of the original data in bits.
        int originalDataSize = originalData.length() * 8;  // Each character assumed to be 8 bits.
        System.out.println("Ascii coding: " + originalDataSize + " bits");

        // Calculate the size of the compressed data in bits.
        int compressedDataSize = 0;
        for (String character : letterCodes.keySet()) {
            int frequency = getLetterFreq(originalData).getOrDefault(character, 0);
            int codeLength = letterCodes.get(character).length();
            compressedDataSize += frequency * codeLength;
        }
        System.out.println("Huffman coding: " + compressedDataSize + " bits");


        if (compressedDataSize == 0) return 0.0;  // To handle divide by zero if compression data size is zero.

        // Calculate the compression ratio.
        double compressionRatio = (double) originalDataSize / compressedDataSize;
        return 100/compressionRatio;
    }

    public Map<String, String> encodeWords(List<String> words) {
        Map<String, String> encodedWords = new java.util.HashMap<>();
        for (String word : words) {
            StringBuilder encodedWord = new StringBuilder();
            for (char character : word.toCharArray()) {
                String code = letterCodes.get(String.valueOf(character));
                if (code != null) {
                    encodedWord.append(code);
                } else {
                    // Handle the case where a character is not found in the letterCodes map
                    System.err.println("No Huffman code found for character: " + character);
                }
            }
            encodedWords.put(word, encodedWord.toString());
        }
        return encodedWords;
    }
    public List<Map.Entry<String, String>> sortEncodedWords(Map<String, String> encodedWords) {
        List<Map.Entry<String, String>> sortedEntries = new ArrayList<>(encodedWords.entrySet());

        sortedEntries.sort(Comparator.comparingInt(entry -> entry.getValue().length()));

        return sortedEntries;
    }





    public static void main(String[] args) {
        // Example data to encode
        Wordle game = new Wordle();
        Huffman huff = new Huffman(String.join("", game.dictionary));//uses wordle dict
        System.out.println("Load factor" + huff.letterCodes.getLoadFactor());
        System.out.println("Collision: " +huff.letterCodes.countCollisions());

        // Calculate and print the Compression Ratio

        System.out.println("Compression Ratio: " + huff.calculateCompressionRatio(String.join("", game.dictionary)) + "\n");

        Map<String, String> encodedWords = huff.encodeWords(game.dictionary);

        List<Map.Entry<String, String>> sortedEncodedWords = huff.sortEncodedWords(encodedWords);

        System.out.println("Sorted Encoded Words (from shortest to longest):");
        for (Map.Entry<String, String> entry : sortedEncodedWords) {
            System.out.println("Word: " + entry.getKey() + ", Encoded: " + entry.getValue() + " (Length: " + entry.getValue().length() + ")");
        }
    }




}
