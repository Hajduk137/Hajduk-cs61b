package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int head = 5;
    private int tail = 5;
    private int size;

    public ArrayDeque(){
        items  = (T[])  new Object[8];
        size = 0;
    }

    private void resize(int cap) {
        T[] a = (T []) new Object[cap];
       if (tail <= head) {
           System.arraycopy(items, head, a, 0,  items.length - head + 1);

           System.arraycopy(items, 0, a, items.length - head, tail );
           head = 0;
           tail = items.length;
       }
       else {
          System.arraycopy(items, 0, a, 0, items.length);
       }
        items = a;

    }
    public void addFirst(T item){
        if (size == items.length) {
            resize(size * 2);
        }

        head = (head - 1 + items.length) % items.length;
        items[head] = item;
        size += 1;

    }

    public void addLast(T item){
        if (size == items.length) {
            resize(size * 2);
        }
        items[tail] = item;
        tail = (tail + 1) % items.length;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){

    }

    public T removeFirst(){
        if (this.isEmpty()) {
            return null;
        }
        int useratio = size / items.length;
        if (useratio < 0.25) {
            resize(size / 2);
        }
        T returnItem = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;
        return returnItem;
    }

    public T removeLast(){
        if (this.isEmpty()) {
            return null;
        }
        int useratio = size / items.length;
        if (useratio < 0.25) {
            resize(size / 2);
        }
        tail = (tail - 1 + items.length) % items.length;
        T returnItem = items[tail];
        items[tail] = null;
        size -= 1;
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

}
