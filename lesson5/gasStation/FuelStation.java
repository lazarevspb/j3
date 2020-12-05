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


//    private Cars cars;


    public FuelStation() {
        this.gasPool = new GasPool();
        this.semaphore = new Semaphore(CAR_RESTRICTION);

    }


    public void refuelTheCar(Cars cars) {

        try {
            semaphore.acquire();

//              lock.writeLock();
            float requiredFuel = cars.getFuelFullCapacities() - cars.getFuelCapacities(); //получаем значение нужного количества топлива
            if (gasPool.getFuelReserve() - requiredFuel >= 0) {
                Thread.sleep(300);
                System.out.printf("[%s} Заправка...%n", cars.getStringID());
                cars.setFuelCapacities(cars.getFuelCapacities() + requiredFuel);
                System.out.printf("requiredFuel %.1f\n", requiredFuel);
                gasPool.request(requiredFuel);

            } else {
                System.out.printf("[%s} заправка не состоялась%n", cars.getStringID());
//              return 0;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
//            lock.writeLock().unlock();
        }

    }

}
