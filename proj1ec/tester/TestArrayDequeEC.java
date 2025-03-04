package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void randomTest() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> broken = new StudentArrayDeque<>();
        StringBuilder message = new StringBuilder();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int opRandom = StdRandom.uniform(0, 4);
            if (opRandom == 0) {
                //addFirst
                int randVal = StdRandom.uniform(0, 100);
                correct.addFirst(randVal);
                broken.addFirst(randVal);
                message.append("addFirst(" + randVal + ")\n");
            } else if (opRandom == 1) {
                //addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                message.append("addLast(" + randVal + ")\n");
            } else if (opRandom == 2) {
                //removeFirst
                if (!correct.isEmpty() && !broken.isEmpty()) {
                    Integer valCorrect = correct.removeFirst();
                    Integer valBroken = broken.removeFirst();
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(), valCorrect, valBroken);
                }
            } else if (opRandom == 3) {
                if (!correct.isEmpty() && !broken.isEmpty()) {
                    Integer valCorrect = correct.removeLast();
                    Integer valBroken = broken.removeLast();
                    message.append("removeLast()\n");
                    assertEquals(message.toString(), valCorrect, valBroken);
                }
            }

        }
    }
}
