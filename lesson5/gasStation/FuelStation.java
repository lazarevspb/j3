package lesson5.gasStation;


import lesson5.cars.Cars;

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

    public boolean isEnoughFuel(float amount) {
        return gasPool.getFuelReserveGP() - amount >= 0;
    }


    public float refuelTheCar(float amount, Cars car) {
        try {
            semaphore.acquire(3);
            System.out.printf("[%s] прибыл на заправку%n", car.getStringID());
            System.out.printf("[%s] Запрос топлива в количестве %.1f, на складе %.1f \n", car.getStringID(), amount, gasPool.getFuelReserveGP());
            if (gasPool.isEnoughFuel(amount)) {
                try {
                    Thread.sleep(500);
                    return gasPool.request(amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("[Заправка] - Нету топлива");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(gasPool.info());
            semaphore.release();

        }
        return 0;
    }


}
