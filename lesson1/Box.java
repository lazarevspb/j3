package lesson1;

import lesson1.fruit.Fruit;

import java.util.ArrayList;
import java.util.List;

/*b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;*/
public class Box<T extends Fruit> {
    /* Для хранения фруктов внутри коробки можете использовать ArrayList;*/
    List<T> boxFruitList = new ArrayList<>();

    /*g. Не забываем про метод добавления фрукта в коробку.*/
    void addBoxFruit(T fruits) {
        boxFruitList.add(fruits);
    }

    /*d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта
    (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);*/
    float getWeight() {
        return boxFruitList.size() * this.boxFruitList.get(0).getWeight();
    }

        /*e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
        которую подадут в compare в качестве параметра, true - если их веса равны, false в противном случае
        (коробки с яблоками мы можем сравнивать с коробками с апельсинами);*/

    boolean compare(Box<? extends   Fruit> otherFruitBox) {
        return Math.abs(this.getWeight() - otherFruitBox.getWeight()) > 0.0001;
    }

    /*f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
    (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
    соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
    которые были в этой коробке;*/
    void intersperse(Box<T> otherBox) {
        if (otherBox == this) return;
        for (int i = 0; i < boxFruitList.size(); i++) {
            otherBox.addBoxFruit(boxFruitList.get(i));
        }
        this.boxFruitList.clear();
    }
}

