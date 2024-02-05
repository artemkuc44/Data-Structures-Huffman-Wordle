package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
        Node<E> newNode = new Node<>(e,pred,succ);
        pred.next = newNode;
        succ.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int i) {
        // TODO
        if(head == null || isEmpty()){
            return null;
        }
        Node<E> current = head;
        for(int j = 0;j<=i;j++){
            current = current.getNext();
        }

        return current.getData();
    }

    @Override
    public void add(int i, E e) {
        // TODO
        if(head == null){
            head = new Node<>(e,null,null);
        }
        if(i == 0){
            addFirst(e);
        }
        if(i == size-1){
            addLast(e);
        }
        Node<E> current = head;
        for(int j = 0;j<i;j++){
            current = current.getNext();
        }
        Node<E> newNode = new Node<>(e,current,current.getNext());
        if(current.getNext() != null){
            current.getNext().setPrev(newNode);
        }
        current.setNext(newNode);
        size ++;
    }

    @Override
    public E remove(int i) {
        // TODO
        if(head == null || isEmpty()){
            return  null;
        }
        if(i == 0){
            return removeFirst();
        }
        if(i == size-1){
           return removeLast();
        }
        Node<E> current = head;
        for(int j = 0;j<=i;j++){
            current = current.getNext();
        }
        E removed = current.getData();

        current.getPrev().setNext(current.getNext());
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        }

        return removed;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>)head.next;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        // TODO
        if(head == null || isEmpty()){
            return  null;
        }
        Node<E> current = head;
        for(int i = 0;i<size-1;i++){

            current = current.getNext();
            if(current.equals(n)){
                break;
            }
        }

        current.getPrev().setNext(current.getNext());
        if (current.getNext() != null) {
            current.getNext().setPrev(current.getPrev());
        }
        size--;
        return n.getData();

    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        E newE = head.getNext().getData();
        return newE;
    }

    public E last() {
        // TODO

        return tail.getPrev().getData();
    }

    @Override
    public E removeFirst() {
        // TODO
        if(isEmpty()){
            return null;
        }
        E removed = head.getData();

        if(size == 1){

            head = null;
        }else{
            head = head.getNext();
            head.setPrev(null);
        }

        size--;

        return removed;
    }

    @Override
    public E removeLast() {
        // TODO
        if(isEmpty()){
            return null;
        }
        E removed = tail.getPrev().getData();
        if(size == 1){
            head = null;
            tail = null;
        }else{
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size --;
        return removed;

    }

    public void addLast(E e) {
        addBetween(e, tail.getPrev( ), tail);
    }


    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());

    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}