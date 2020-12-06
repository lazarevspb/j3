package lesson5.gasStation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    Date date;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final float capacity = 200f;
    private volatile static float fuelReserve = 200f;

    protected float getFuelReserveGP() {
        return fuelReserve;
    }

    //для выведения информации о фактическом состоянии
    synchronized public String info() {
        if (fuelReserve < 0) System.err.printf("Отрицательный fuelReserve = %f%n", fuelReserve);
        return "[Склад]{" +
                "Общий объем = " + capacity +
                ", Содержится топлива = " + fuelReserve +
                '}' + " " + Thread.currentThread().getName();
    }

    public boolean isEnoughFuel(float amount) {
        return fuelReserve - amount >= 0;
    }

    synchronized float request(float amount) {
        if (isEnoughFuel(amount)) {
            lock.writeLock().lock();
            fuelReserve -= amount;

            date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:sssss");
            simpleDateFormat.format(date);

            lock.writeLock().unlock();
            return amount;
        } else {
            System.err.printf("[Склад] Не хватает топлива для заправки = %.1f%n", fuelReserve);
            return 0F;
        }
    }
}
