package lesson5.cars;

import lesson5.gasStation.FuelStation;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Cars implements Runnable {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    FuelStation fuelStation;
    private static int idCars = 1;
    private final String stringID;
    private float fuelFullCapacities; // объемом топлива
    private float fuelConsumption; //расход

    private float fuelCapacities; // объемом топлива

    public Cars(FuelStation fuelStation) {
        fuelCapacities = fuelFullCapacities;
        this.stringID = getRusName() + "_" + idCars;
        this.fuelStation = fuelStation;
        idCars++;
    }



    public void setFuelCapacities(float fuelCapacities) {
        this.fuelCapacities = fuelCapacities;
    }

    public String getStringID() {
        return stringID;
    }

    public void setFuelFullCapacities(int fuelFullCapacities) {
        this.fuelFullCapacities = fuelFullCapacities;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    protected String getRusName() {
        return getClass().getSimpleName().equals("Car") ? "Автомобиль"
                : getClass().getSimpleName().equals("Bus") ? "Автобус"
                : "Большегруз";
    }

    private float countingTheRemainingFuel() {
        if (fuelCapacities - fuelConsumption > 0) {
            return fuelCapacities -= fuelConsumption;

        } else {
            fuelCapacities = 0;
            return fuelCapacities;
        }
    }

    @Override
    public void run() {
        theCarIsMoving();
    }

    private void refuelingACar() {
        float requiredFuel = fuelFullCapacities - fuelCapacities;

        lock.readLock().lock();
        if (fuelStation.isEnoughFuel(requiredFuel)) {
            System.out.printf("[%s} Заправка...%n", stringID);
            fuelStation.refuelTheCar(requiredFuel, this);
            this.fuelCapacities += requiredFuel;
            System.out.printf("[%s] Заправлен полный бак%n", stringID);
        } else {
            System.out.printf("[%s] заправка не состоялась, нету топлива в достаточном количестве%n", stringID);
            return;
        }
        lock.readLock().unlock();
        theCarIsMoving();
    }

    protected void theCarIsMoving() {
        float remainingFuel = fuelCapacities;

        while (remainingFuel > 5) {
            remainingFuel = countingTheRemainingFuel();
            System.out.printf("[%s] Едем...%n", stringID);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("[%s] Топливо - %.1f, заезжаю на заправку\n", stringID, remainingFuel);
        refuelingACar();
    }
}
