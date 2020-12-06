package lesson6;

/**
 * Homework for lesson #6
 *
 * @author Valeriy Lazarev
 * @since 05.12.2020
 */


public class Solution {


    public static int[] makeAnArrayAfterFour(int[] array) {

        return array;
    }


    public static boolean lookingForOneOrFour(int[] array) {
        for (int j : array) {
            if (j == 1 || j == 4) return true;
        }
        return false;
    }

}
