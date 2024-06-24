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

    private LinkedList<E> getBucket(E element){
        int hash = element.hashCode();
        int bucketIndex = hash % arraySize;
        if(this.elements.get(bucketIndex) == null){
            this.elements.set(bucketIndex, new LinkedList<>());
        }
        return (LinkedList<E>) this.elements.get(bucketIndex);
    }

    public boolean contains(E element){
        LinkedList<E> bucket = getBucket(element);
        for (Object o : bucket){
            if (o.equals(element)) return true;
        }
        return false;
    }

    public void put(E element){
        LinkedList<E> bucket = getBucket(element);
        if (!contains(element))
            bucket.add(element);
    }

    public void delete(E element){
        if (contains(element)) {
            LinkedList<E> bucket = getBucket(element);
            bucket.remove(element);
            if(bucket.size() == 0)
                this.elements.remove(bucket);
        }
    }

    public void print(){
        for (List<E> list : this.elements) {
            if (list != null) {
                for (Object o : list) {
                    System.out.println(o);
                }
            }
        }
    }

    public int countElements(){
        int numberElements = 0;
        for (List<E> list : this.elements) {
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

        private int currentBucket;
        private E currentElement;


        public CustomIterator(){
            currentBucket = -1;
            currentElement = null;
        }

        private int getIndex(E currentElement){
            LinkedList<E> bucket = getBucket(currentElement);
            for (int i = 0; i< bucket.size(); i++){
                if(bucket.get(i).equals(currentElement))
                    return i;
            }
            return 0;
        }
        @Override
        public boolean hasNext() {
            if(currentElement != null && getIndex(currentElement ) < getBucket(currentElement).size()-1 )
                return true;
            for(int i = currentBucket+1; i< elements.size();i++){
                if(elements.get(i) != null) return true;
            }
            return false;
        }

        @Override
        public Object next() {

            if(currentElement == null || getIndex(currentElement) == getBucket(currentElement).size()-1) {
                currentBucket++;

                while (currentBucket < elements.size() && elements.get(currentBucket) == null) {
                    currentBucket++;
                }

                if(currentBucket < elements.size()){
                    currentElement = elements.get(currentBucket).get(0);
                } else throw new NoSuchElementException();
            }else {
                currentElement = getBucket(currentElement).get(getIndex(currentElement) + 1);
            }

            return currentElement;
        }

    }

}
