package lesson6;

import java.util.ArrayList;
import java.util.List;

/**
 * Homework for lesson #6
 *
 * @author Valeriy Lazarev
 * @since 05.12.2020
 */

public class Solution {

    public static int[] makeAnArrayAfterFour(int[] array) {
        int count = 0;
        List<Integer> integers = new ArrayList<>();
        if (array.length > 0) {
            for (int i = array.length - 1; i >= 0; i--) {
                if (array[i] == 4) {
                    for (int j = i + 1; j < array.length; j++) {
                        integers.add(array[j]);
                        count++;
                    }
                    if (integers.size() == 0) return array;
                    else return integers.stream().mapToInt(k -> k).toArray();
                }
            }
            throw new RuntimeException("RuntimeException");
        }
        return array;
    }

    public static boolean lookingForOneOrFour(int[] array) {
        for (int j : array) {
            if (j == 1 || j == 4) return true;
        }
        return false;
    }
}
