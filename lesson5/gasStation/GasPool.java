package lesson5.gasStation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    Date date;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final float capacity = 200f;
    private static float fuelReserve = 200f;

    protected float getFuelReserveGP() {

        lock.readLock().lock();
        float tmp = fuelReserve;
        lock.readLock().unlock();
        return tmp;
    }

    //для выведения информации о фактическом состоянии
    public String info() {
        if (fuelReserve < 0) System.err.printf("Отрицательный fuelReserve = %f%n", fuelReserve);
        return "[Склад]{" +
                "Общий объем = " + capacity +
                ", Содержится топлива = " + fuelReserve +
                '}' + " " + Thread.currentThread().getName();
    }

    public boolean isEnoughFuel(float amount) {
        return fuelReserve - amount >= 0;
    }

    public float request(float amount) {
        lock.writeLock().lock();
        try {
            if (isEnoughFuel(amount)) {
                fuelReserve -= amount;

                date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:sssss");
                simpleDateFormat.format(date);

                return amount;
            } else {
                System.err.printf("[Склад] Не хватает топлива для заправки = %.1f%n", fuelReserve);
                return 0F;
            }
        } finally {
            lock.writeLock().unlock();

        }
    }
}
