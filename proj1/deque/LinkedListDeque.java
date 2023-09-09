package deque;

public class LinkedListDeque<T> {
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

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        else {
            return false;
        }
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
}
