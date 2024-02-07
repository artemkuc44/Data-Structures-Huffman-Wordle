package project20280.stacksqueues;

import project20280.interfaces.Stack;

import java.util.NoSuchElementException;

public class BoundedStack<E> implements Stack<E> {

    int maxSize;
    int top = -1;
    E[] List;


    public BoundedStack(int size) {
        this.maxSize = size;
        List = (E[ ]) new Object[maxSize];
    }

    @Override
    public int size() {
        return top+1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == maxSize;
    }
    @Override
    public void push(E e) throws IllegalStateException{
        if(size() == maxSize) throw new IllegalStateException("Out of space");
        List[++top] = e;
    }

    @Override
    public E top() throws NoSuchElementException{
        if(size() == 0){
            throw new NoSuchElementException();
        }
        return List[top];
    }

    @Override
    public E pop() throws NoSuchElementException{
        if(size() == 0){
            throw new NoSuchElementException();
        }
        E ans = List[top];
        List[top--] = null;
        return ans;
    }
}
