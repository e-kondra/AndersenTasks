package andersen.AndersenTasks.collections;

import java.util.Arrays;
import java.util.Iterator;


public class CustomArrayList<E> implements Iterable<E> {

    private E[] elements;
    private int size;

    public CustomArrayList(int size){
        this.elements = (E[]) new Object[size];
    }

    public CustomArrayList(){
        this(0);
    }

    public int getSize(){
        return this.size;
    }
    public void put(E element){
        Object[] arrayElements = this.elements;
        int newSize = this.getSize() + 1;
        this.elements = (E[]) Arrays.copyOf(arrayElements, newSize);
        this.elements[newSize-1] = element;
        this.size = newSize;
    }

    public E get(int index){
        return this.elements[index];
    }

    public void delete(int index){
        int newSize = this.getSize() - 1;
        Object[] arrayElements = new Object[newSize];
        int j = 0;
        for(int i = 0; i < this.size; i++){
            if (i != index){
                arrayElements[j] = this.elements[i];
                j++;
            }
        }
        this.elements = (E[]) Arrays.copyOf(arrayElements, newSize);
        this.size = newSize;
    }

    @Override
    public Iterator iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator {
        private int current  = 0;
        @Override
        public boolean hasNext() {
            return current < getSize();
        }

        @Override
        public Object next() {
            return elements[current++];
        }
    }
}
