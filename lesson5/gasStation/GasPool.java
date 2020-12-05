package lesson5.gasStation;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GasPool {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    private final float capacity = 200f;
    private volatile static float fuelReserve = 200f;

    public float getFuelReserve() {
        return fuelReserve;
    }


    public void setFuelReserve(float fuelReserve) {
        GasPool.fuelReserve = fuelReserve;
    }


    //для выведения информации о фактическом состоянии
    synchronized public String info() {
        return "GasPool{" +
                "capacity=" + capacity +
                ", fuelReserve=" + fuelReserve +
                '}';
    }

    //для получения необходимого кол-ва топлива
   synchronized void  request(float amount) {
        if (fuelReserve - amount >= 0) {
            lock.writeLock().lock();
//            System.out.printf("amount %.1f\n", amount);
            fuelReserve -= amount;
//            lock.readLock();
            System.out.println(info());
            lock.writeLock().unlock();
//            lock.readLock().unlock();
        } else {
            System.out.println("На заправке не хватает топлива");
        }
    }

    @Override
    public String toString() {
        return info();
    }
}
