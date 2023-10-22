package deque;

import java.util.Iterator;

public class ArrayDeque<T>  implements  Deque<T>{
    private T[] items;
    private int head = 0;
    private int tail = 0;
    private int size;

    public ArrayDeque(){
        items  = (T[])  new Object[8];
        size = 0;
    }

    private void resize(int cap) {
        T[] a = (T []) new Object[cap];
        for (int i = 0; i < size; i++) {
            a[i] = items[(head + i) % items.length];
        }
        items = a;
        head = 0;
        tail = size % items.length;
       }

    public void addFirst(T item){
        if (size == items.length) {
            resize(items.length * 2);
        }

        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size += 1;

    }

    public void addLast(T item){
        if (size == items.length) {
            resize(items.length * 2);
        }
        tail = tail % items.length;
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size += 1;
    }


    public int size(){
        return size;
    }

    public void printDeque(){
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();

    }

    public T removeFirst(){
        if (this.isEmpty()) {
            return null;
        }


        T returnItem = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;
        double useratio = (double)size / items.length;
        if (useratio < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return returnItem;
    }

    public T removeLast(){
        if (this.isEmpty()) {
            return null;
        }

        tail = (tail - 1 + items.length) % items.length;
        T returnItem = items[tail];
        items[tail] = null;
        size -= 1;
        double useratio = (double)size / items.length;
        if (useratio < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return returnItem;
    }

    public T get(int index){
        if (index > size) {
            return null;
        }
        else {
            return items[(head + index) % items.length];
        }
    }

    private class ArrayIterator  implements Iterator<T>{
        private int wizPos;
        public ArrayIterator() {
            wizPos = head;
        }
        public  boolean hasNext() {
            return (wizPos + 1) % items.length != tail;
        }
        public  T next() {
            T returnItem = items[wizPos];
            wizPos = (wizPos + 1) % items.length;
            return returnItem;
        }

    }


    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! (o instanceof  ArrayDeque oas)) {
            return false;
        }
        if (oas.size() != this.size()){
            return false;
        }
        Iterator<T> oasIterator = oas.iterator();
        Iterator<T> thisIterator = this.iterator();

        while (oasIterator.hasNext() && thisIterator.hasNext()) {
            T item1 = oasIterator.next();
            T item2 = thisIterator.next();
            if (!item1.equals(item2)) {
                return false;
            }
        }
        return !oasIterator.hasNext() && !thisIterator.hasNext();
    }
}
