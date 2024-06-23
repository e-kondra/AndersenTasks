package andersen.AndersenTasks.collections;


import java.util.*;


public class CustomHashSet<E> implements Iterable<E> {
    private List<List<E>> elements;
    private final int arraySize = 10;


    public CustomHashSet() {
        this.elements = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++)
            this.elements.add(null);
    }

    private List<E> getBucket(E element){
        int hash = element.hashCode();
        int bucketIndex = hash % arraySize;
        if(this.elements.get(bucketIndex) == null){
            this.elements.set(bucketIndex, new LinkedList<>());
        }
        return this.elements.get(bucketIndex);
    }

    public boolean contains(E element){
        List<E> bucket = getBucket(element);
        for (Object o : bucket){
            if (o.equals(element)) return true;
        }
        return false;
    }

    public void put(E element){
        List<E> bucket = getBucket(element);
        if (!contains(element))
            bucket.add(element);
    }

    public void delete(E element){
        if (contains(element)) {
            List<E> bucket = getBucket(element);
            bucket.remove(element);
            if(bucket.size() == 0)
                this.elements.remove(bucket);
        }
    }

    public void print(){
        for (List list : this.elements) {
            if (list != null) {
                for (Object o : list) {
                    System.out.println(o);
                }
            }
        }
    }

    public int countElements(){
        int numberElements = 0;
        for (List list : this.elements) {
            if (list != null) {
                for (Object o : list) {
                    numberElements++;
                }
            }
        }
        return numberElements;
    }
    @Override
    public Iterator<E> iterator() {
        return new CustomIterator();
    }


    private class CustomIterator implements Iterator {
        private int current  = 0;
        @Override
        public boolean hasNext() {
            return current < countElements();
        }

        @Override
        public Object next() {
            Object e = elements.get(current++);
            return e;
        }
    }

}
