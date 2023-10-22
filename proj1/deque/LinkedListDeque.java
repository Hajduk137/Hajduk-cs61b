package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;
        public Node(T i, Node n, Node p) {
            this.item = i;
            this.next = n;
            this.prev = p;
        }
    }



    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        this.size = 0;
    }



    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        this.size += 1;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, null, null);
        newNode.next = sentinel;
        newNode.prev = sentinel.prev;
        sentinel.prev = newNode;
        newNode.prev.next = newNode;
        this.size += 1;
    }



    public int size() {
        return this.size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.println(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next != sentinel) {
            Node returnNode = sentinel.next;
            sentinel.next = returnNode.next;
            returnNode.next.prev = sentinel;
            returnNode.next = null;
            returnNode.prev = null;
            this.size -= 1;
            return returnNode.item;
        }
        else {
            return null;
        }

    }

    public T removeLast() {
        if (sentinel.prev != sentinel) {
            Node removeNode = sentinel.prev;
            sentinel.prev = removeNode.prev;
            removeNode.prev.next = sentinel;
            removeNode.next = null;
            removeNode.prev = null;
            this.size -= 1;
            return removeNode.item;
        }
        else {
            return null;
        }

    }

    public T get(int index) {
        Node currentNode = sentinel.next;
        int currentIndex = 0;
        if (index < 0) {
            return null;
        }
        while (currentNode != sentinel) {
            if (currentIndex == index) {
                return currentNode.item;
            }
            currentIndex += 1;
            currentNode = currentNode.next;
        }
        return null;
    }

    public T getRecursive(int index) {
            Node current = sentinel.next;
            if (index < 0) {
                return null;
            }
            if (index == 0) {
                return current.item;
            }

            current = current.next;
            return getRecursive(index - 1);
    }
    private class ListIterator implements Iterator<T>{
        private Node curr;
        public ListIterator() {
            curr = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return curr.next != sentinel;
        }

        @Override
        public T next() {
            T returnItem = curr.item;
            curr = curr.next;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! (o instanceof  LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> oas = (LinkedListDeque<T>) o;
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
