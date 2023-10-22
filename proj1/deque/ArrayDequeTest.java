package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testThreeAddThreeRemove() {
        ArrayDeque CAList = new ArrayDeque<Integer>();
        ArrayDeque BAList = new ArrayDeque<Integer>();
        for (int i = 4; i <=6; i ++) {
            CAList.addLast(i);
            BAList.addLast(i);
        }
        assertTrue(CAList.equals(BAList));
        for (int i = 0; i < 3;  i ++) {
            assertEquals(CAList.removeLast(), BAList.removeLast());
        }
    }

    @Test
    public void equalTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        ArrayDeque<Integer> BL = new ArrayDeque<>();
        L.addLast(5);
        BL.addFirst(4);
        BL.addLast(5);
        assertFalse(L.equals(BL));
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i ++) {
            lld1.addLast(i);
        }
        int expectValue = 0;
        for (int s : lld1) {
            assertEquals(expectValue, s);
            expectValue ++;
        }

    }

    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        ArrayDeque<Integer> BL = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int Lsize = L.size();
                int BLsize = BL.size();
                assertEquals(Lsize, BLsize);
            } else if (operationNumber == 2) {
                if (L.size() != 0) {
                    int lLastValue = L.removeFirst();
                    int blLastValue = BL.removeFirst();
                    assertEquals(lLastValue, blLastValue);
                }
            } else if (operationNumber == 3) {
                if (L.size() != 0) {
                    int lLastValue = L.removeLast();
                    int blLastValue = BL.removeLast();
                    assertEquals(lLastValue, blLastValue);
                }

            }

        }
    }
}
