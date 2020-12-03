package lesson4;

/**
 * Homework for lesson #4
 *
 * @author Valeriy Lazarev
 * @since 29.11.2020
 */

/*
Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
Используйте wait/notify/notifyAll.
*/
public class Solution {
    private final Object lock = new Object();
    char charA = 'A';
    char charB = 'B';
    char charC = 'C';
    private volatile char currentLetter = charA;

    public static void main(String[] args) {
        Solution s = new Solution();
        Thread t1 = new Thread(s::printA);
        Thread t2 = new Thread(s::printB);
        Thread t3 = new Thread(s::printC);

        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != charA) {
                        lock.wait();
                    }
                    System.out.print(charA);
                    currentLetter = charB;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != charB) {
                        lock.wait();
                    }
                    System.out.print(charB);
                    currentLetter = charC;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (lock) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != charC) {
                        lock.wait();
                    }
                    System.out.print(charC);
                    currentLetter = charA;
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
