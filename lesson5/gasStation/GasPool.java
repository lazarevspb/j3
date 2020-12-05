package lesson5.gasStation;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final float capacity = 200f;
    private volatile static float fuelReserve = 200f;

    protected float getFuelReserveGP() {
        return fuelReserve;
    }

    //для выведения информации о фактическом состоянии
 synchronized public String info() {
        return "GasPool{" +
                "capacity=" + capacity +
                ", fuelReserve=" + fuelReserve +
                '}' +" " + Thread.currentThread().getName();
    }

   synchronized float  request(float amount) {
            lock.writeLock().lock();
        if (fuelReserve - amount >= 0) {
            fuelReserve -= amount;
            lock.writeLock().unlock();

            System.out.println(info());
            return amount;
        } else {
            System.out.println("На заправке не хватает топлива");
            return 0F;
        }
    }
}
