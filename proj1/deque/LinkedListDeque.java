package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next = node;
        firstNode.prev = node;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        Node node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = node;
        lastNode.next = node;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T i : this) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) return null;
        Node node = sentinel.next;
        sentinel.next = node.next;
        T item = node.item;
        node.next.prev = sentinel;
        this.size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) return null;
        Node node = sentinel.prev;
        sentinel.prev = node.prev;
        T item = node.item;
        node.prev.next = sentinel;
        this.size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > this.size - 1 || index < 0) return null;
        Node node = sentinel.next;
        while (index > 0) {
            node = node.next;
            index -= 1;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > this.size - 1) return null;
        return getRecursiveHelper(0, index, sentinel.next);
    }
    private T getRecursiveHelper(int now, int index, Node node) {
        if (now == index) return node.item;
        return getRecursiveHelper(now + 1, index, node);
    }

    /*
    Iterator
     */
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int wizPos;

        private LinkedListDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    /*
    equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deque)) return false;
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) return false;
        for (int i = 0; i < this.size; i += 1) {
            if (this.get(i) != other.get(i)) return false;
        }
        return true;
    }
}
