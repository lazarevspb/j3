package lesson5.gasStation;


import lesson5.Main;
import lesson5.cars.Cars;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*FuelStaion одновременно может заправлять только 3 автомобиля, остальные должны пока не освободится место
(порядок не имеет значения).*/
//public   class  FuelStation extends GasPool {
public class FuelStation {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private GasPool gasPool;
    public static final int CAR_RESTRICTION = 3;
    private Semaphore semaphore;

    public FuelStation() {
        this.gasPool = new GasPool();
        this.semaphore = new Semaphore(CAR_RESTRICTION);

    }

    public float getFuelReserveFS() {
        System.out.println("getFuelReserveFS() - " + gasPool.getFuelReserveGP());
        return gasPool.getFuelReserveGP();
    }

    public float refuelTheCar(float amount, Cars car) {


        try {
            System.out.printf("[%s] прибыл на заправку%n", car.getStringID());
            semaphore.acquire();
            System.out.printf("[%s] requiredFuel(float amount) %.1f\n", car.getStringID(), amount);
            if (gasPool.getFuelReserveGP() - amount >= 0) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return gasPool.request(amount);
            }
            System.out.println("[Заправка] - Нету топлива");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
        return 0F;
    }


}
