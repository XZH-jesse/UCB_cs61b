package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> incorrect = new BuggyAList<>();

        correct.addLast(4);
        incorrect.addLast(4);
        correct.addLast(5);
        incorrect.addLast(5);
        correct.addLast(6);
        incorrect.addLast(6);

        assertEquals(correct.size(), incorrect.size());

        assertEquals(correct.removeLast(), incorrect.removeLast());
        assertEquals(correct.removeLast(), incorrect.removeLast());
        assertEquals(correct.removeLast(), incorrect.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(), B.size());
                //System.out.println("size: " + size);
            } else if (L.size() > 0) {
                if (operationNumber == 2) {
                    int LastVal = L.getLast();
                    assertEquals(L.getLast(), B.getLast());
                    //System.out.println("getLast(" + LastVal + ")");
                } else {
                    int LastVal = L.removeLast();
                    assertEquals(LastVal, (int) B.removeLast());
                    //System.out.println("removeLast(" + LastVal + ")");
                }
            }
        }
    }
}
