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

    /*Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)*/
    public abstract class Fruit {
        float weight;

        public float getWeight() {
            return weight;
        }
    }

    public class Apple extends Fruit {
        public Apple() {
            super.weight = 1.0f;
        }
    }

    public class Orange extends Fruit {
        public Orange() {
            super.weight = 1.5f;
        }
    }

    /*b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;*/
    public class Box<T extends Fruit> {
        /* Для хранения фруктов внутри коробки можете использовать ArrayList;*/
        List<T> boxFruitList = new ArrayList<>();

        /*g. Не забываем про метод добавления фрукта в коробку.*/
        protected void addBoxFruit(T fruits) {
            boxFruitList.add(fruits);
        }

        /*d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
        (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);*/
        protected float getWeight() {
            return boxFruitList.size() * this.boxFruitList.get(0).getWeight();
        }

        /*e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
        которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
        (коробки с яблоками мы можем сравнивать с коробками с апельсинами);*/

        protected boolean compare(T fruitsFirst, T fruitsSecond) {
            return Math.abs(fruitsFirst.getWeight() - fruitsSecond.getWeight()) > 0.0001;
        }

        /*f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
        (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
        соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
        которые были в этой коробке;*/
        protected void intersperse(Box<T> otherBox) {
            if (otherBox == this) return;
            for (int i = 0; i < boxFruitList.size(); i++) {
                otherBox.addBoxFruit(boxFruitList.get(i));
            }
            this.boxFruitList.clear();
        }
    }
}
