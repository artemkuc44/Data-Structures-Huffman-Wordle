# Huffman Compression and Wordle Solver using Data Structures Projects

Welcome to my GitHub repository! This repository showcases two distinct projects that demonstrate my proficiency in Java, data structures, algorithms, and problem-solving skills. These projects aim to provide practical examples of compression techniques and a Wordle game solver. The data structures used in these projects were based on the book **Data Structures and Algorithms in Java, 6th Edition** by Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser.

This was a two-part project:
1. First, I implemented various data structures and wrote tests to ensure they function correctly.
2. Then, I built the Huffman compression algorithm and the Wordle solver using these data structures.

## Data Structures

The following data structures were implemented based on the book^ :
- **Hashtable**: Efficient data lookup and storage.
- **Interfaces**: Abstractions for various data structures.
- **List**: Implementation of linked lists.
- **Priority Queue**: A queue structure where elements are processed based on priority.
- **Stacks and Queues**: Implementation of stack and queue data structures for efficient operations.
- **Tree**: Binary trees used for hierarchical data storage and Huffman encoding.

Each of these data structures was tested extensively before being used in the final projects.

## Project 1: Huffman Compression Algorithm

### Description:
The **Huffman Compression Algorithm** is an implementation of one of the most efficient techniques for data compression. It creates a variable-length code for each character depending on its frequency in the input text, leading to reduced file sizes. This project consists of two Java classes:
1. `Huffman.java`: Contains the logic to build the Huffman tree, encode, and decode text files.
2. `HuffmanTest.java`: Provides test cases for validating the correct functioning of the Huffman algorithm.

### Files:
- **`Huffman.java`**: Implements the Huffman encoding and decoding algorithm.
- **`HuffmanTest.java`**: JUnit test cases to verify the compression and decompression functionalities.
- **`dictionary.txt`**: Sample input dictionary used to test the encoding.
- **`sample.txt`**: A sample text file used for compression and decompression testing. The file contains a philosophical text about the balance between pleasure and pain.

### How It Works:
- **Encoding**: The program scans the input file and counts the frequency of each character, creating a binary tree (Huffman Tree) where more frequent characters have shorter codes.
- **Decoding**: The encoded file is then decoded using the tree, returning the original content.
  
### Applications:
This compression technique is widely used in real-world applications, including ZIP file compression, multimedia files, and more.

---

## Project 2: Wordle Solver

### Description:
The **Wordle Solver** is designed to help solve the popular word puzzle game, Wordle. It simulates the guessing game and narrows down the correct word by systematically eliminating possibilities based on feedback.

### Files:
- **`Wordle.java`**: The core logic for simulating the game and solving Wordle puzzles.
- **`WordleTest.java`**: JUnit tests for ensuring the correctness of the solver.
- **`dictionary.txt`**: A standard Wordle word list used in the game simulation.
- **`extended-dictionary.txt`**: An extended word list used for solving more complex or unusual Wordle scenarios.
- **`testDict.txt`**: Contains additional words used for testing.

### How It Works:
- The program uses a guessing algorithm that narrows down the correct word by evaluating the feedback from previous guesses (i.e., correct letters in the wrong or right position).
- It continues to refine guesses until it identifies the correct word or exhausts the possibilities.

### Key Features:
- **Efficiency**: The solver aims to find the correct word within a few guesses by leveraging dictionary data.
- **Accuracy**: The program uses multiple dictionaries to simulate a real game environment and tests edge cases.

---

## Technologies Used:
- **Language**: Java
- **Testing**: JUnit for unit testing
- **Data Structures**: Binary trees, arrays, lists, hash tables, priority queues, and stacks
- **Algorithms**: Huffman coding, heuristic search for Wordle solving
