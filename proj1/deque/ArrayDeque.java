package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }


    private void resize(int capacity) {
        T[] newArrayDeque = (T[]) new Object[capacity];
        int firstIndex = (nextFirst + 1) % items.length;
        int lastIndex = (nextLast - 1 + items.length) % items.length;
        if (firstIndex < lastIndex) {
            System.arraycopy(items, firstIndex, newArrayDeque, 0, lastIndex - firstIndex + 1);
        }
        if (firstIndex > lastIndex) {
            System.arraycopy(items, firstIndex, newArrayDeque, 0, items.length - firstIndex);
            System.arraycopy(items, 0, newArrayDeque, items.length - firstIndex, lastIndex + 1);
        }
        items = newArrayDeque;
        nextFirst = items.length - 1;
        nextLast= this.size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
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
        if (size == 0) {
            return null;
        }
        if ((size < items.length / 4) && items.length > 8) {
            resize(items.length / 2);
        }

        int firstIndex = (nextFirst + 1 + items.length) % items.length;
        T item = get(0);
        items[firstIndex] = null;
        nextFirst = firstIndex;
        this.size -= 1;

        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((size < items.length / 4) && items.length > 8) {
            resize(items.length / 2);
        }

        int lastIndex = (nextLast - 1 + items.length) % items.length;
        T item = get(size - 1);
        items[lastIndex] = null;
        nextLast = lastIndex;
        this.size -= 1;

        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(index + nextFirst + 1 + items.length) % items.length];
    }




    /*
    Iterator
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;

        private ArrayDequeIterator() {
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size; i += 1) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
