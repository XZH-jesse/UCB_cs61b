package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
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
            T[] newArrayDeque = (T[]) new Object[capacity * 2];
            int firstIndex = nextLast;
            int lastIndex = nextFirst;
            System.arraycopy(items, firstIndex, newArrayDeque, 0, items.length - firstIndex);
            if (firstIndex > lastIndex) {
                System.arraycopy(items, 0, newArrayDeque, items.length - firstIndex + 1, lastIndex + 1);
            }
            items = newArrayDeque;
        }

        @Override
        public void addFirst(T item) {
            if (size == items.length - 1) {
                resize(items.length);
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
            if (size == items.length - 1) {
                resize(items.length);
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
            if (size == 0) return null;
            if ((size < items.length / 4) && items.length > 8) {
                resize(items.length / 2);
            }

            int firstIndex = (nextFirst + 1) % items.length;
            T item = get(firstIndex);
            items[firstIndex] = null;
            nextFirst = firstIndex;

            return item;
        }

        @Override
        public T removeLast() {
            if (size == 0) return null;
            if ((size < items.length / 4) && items.length > 8) {
                resize(items.length / 2);
            }

            int lastIndex = (nextLast - 1 + items.length) % items.length;
            T item = get(lastIndex);
            items[lastIndex] = null;
            nextLast = lastIndex;

            return item;
        }

        @Override
        public T get(int index) {
            if (index >= size || index < 0) return null;
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
            if (this == o) return true;
            if (!(o instanceof Deque)) return false;
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (this.size() != other.size()) return false;
            for (int i = 0; i < this.size; i += 1) {
                if (this.get(i) != other.get(i)) return false;
            }
            return true;
        }


}
