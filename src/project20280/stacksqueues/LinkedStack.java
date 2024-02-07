package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll = new DoublyLinkedList<>();//empty list

    public static void main(String[] args) {
    }

//    public LinkedStack() {//linked stack relis on initially empty list
//        // TODO
//
//    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) {
        // TODO
        ll.addFirst(e);

    }

    @Override
    public E top() {
        // TODO
        return ll.first();
    }

    @Override
    public E pop() {
        // TODO

        E ans = ll.first();
        ll.removeFirst();
        return ans;
    }

    public String toString() {
        return ll.toString();
    }
}
