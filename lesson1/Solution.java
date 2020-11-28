package lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Homework for lesson #1
 *
 * @author Valeriy Lazarev
 * @since 19.11.2020
 */


public class Solution {
    /*1. Написать метод, который меняет два элемента массива местами. (Массив может быть любого ссылочного типа)*/
    public <T> void swaps(T[] arrays, int indexFirst, int indexSecond) {
        if (arrays.length > 0
                && indexFirst < arrays.length
                && indexSecond < arrays.length
                && arrays[indexFirst] != null && arrays[indexSecond] != null) {
            T tmp = arrays[indexFirst];
            arrays[indexFirst] = arrays[indexSecond];
            arrays[indexSecond] = tmp;
        }
    }

    /*2. Написать метод, который преобразует массив в ArrayList;*/
    public <T> ArrayList<T> convertsToList(T[] arrays) {
        if (arrays.length > 0) {
            return (ArrayList<T>) Arrays.asList(arrays);
        }
        return null;
    }







}
