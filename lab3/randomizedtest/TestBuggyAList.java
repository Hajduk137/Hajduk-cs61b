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
    AListNoResizing CAList = new AListNoResizing<Integer>();
    BuggyAList BAList = new BuggyAList<Integer>();
    for (int i = 4; i <=6; i ++) {
        CAList.addLast(i);
        BAList.addLast(i);
    }

    for (int i = 0; i < 3;  i ++) {
        assertEquals(CAList.removeLast(), BAList.removeLast());
    }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

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
                    int lLastValue = L.getLast();
                    int blLastValue = BL.getLast();
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




