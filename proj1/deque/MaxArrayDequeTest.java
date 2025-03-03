package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {




    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        Comparator<String> cmp = new Comparator<>() {
            //重写排序方法
            @Override
            public int compare(String strA, String strB) {
                return strB.compareTo(strA);
            }
        };

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<String> lld1 = new MaxArrayDeque<String>(cmp);

        assertTrue("A newly initialized ALDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        Comparator<Integer> cmp = new Comparator<>() {
            //重写排序方法
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(cmp);
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        Comparator<Integer> cmp = new Comparator<>() {
            //重写排序方法
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(cmp);
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    /*
    @Test
    // Check if you can create LinkedListDeques with different parameterized types
    public void multipleParamTest() {


        MaxArrayDeque<String>  lld1 = new MaxArrayDeque<String>();
        MaxArrayDeque<Double>  lld2 = new MaxArrayDeque<Double>();
        MaxArrayDeque<Boolean> lld3 = new MaxArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }
    */

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        Comparator<Integer> cmp = new Comparator<>() {
            //重写排序方法
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>(cmp);

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    /*
    @Test
    // Add large number of elements to deque; check if order is correct.
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 5; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 9; i > 5; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

     */
}
