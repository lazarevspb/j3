package lesson5.cars;

import lesson5.gasStation.FuelStation;

public abstract class Cars implements Runnable {

    FuelStation fuelStation = new FuelStation();
    private static int idCars = 1;
    private final String stringID;
    private float fuelFullCapacities; // объемом топлива
    private float fuelCapacities; // объемом топлива
    private float fuelConsumption; //расход


    public String getStringID() {
        return stringID;
    }

    public float getFuelCapacities() {
        return fuelCapacities;
    }

    public void setFuelCapacities(float fuelCapacities) {
        this.fuelCapacities = fuelCapacities;
    }

    public float getFuelFullCapacities() {
        return fuelFullCapacities;
    }

    public void setFuelFullCapacities(int fuelFullCapacities) {
        this.fuelFullCapacities = fuelFullCapacities;
    }

    public static int getIdCars() {
        return idCars;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Cars(int uID) {
        this.stringID = getRusName() + "_" + idCars;
        idCars++;
    }

    protected String getRusName() {
        return getClass().getSimpleName().equals("Car") ? "Автомобиль"
                : getClass().getSimpleName().equals("Bus") ? "Автобус"
                : "Большегруз";
    }

    private float countingTheRemainingFuel() {
        if (fuelCapacities - fuelConsumption > 0) {
            fuelCapacities -= fuelConsumption;
        } else {
            fuelCapacities = 0;
            return 0;
        }
        return fuelCapacities;
    }

    @Override
    public void run() {
        theCarIsMoving();
    }

    private void refuelingACar() {
        System.out.printf("[%s] Топливо - %.1f, заезжаю на заправку\n", stringID, countingTheRemainingFuel());
        float requiredFuel = getFuelFullCapacities() - getFuelCapacities();
        System.out.printf("[%s} Заправка...%n", getStringID());

       if(fuelStation.getFuelReserveFS() - requiredFuel  > 0) {
            fuelStation.refuelTheCar(requiredFuel, this);
           this.fuelCapacities += requiredFuel;
           System.out.printf("[%s] Заправлен полный бак%n", stringID);
       }  else {
           System.out.printf("[%s] заправка не состоялась%n", stringID);
           return;
       }
        theCarIsMoving();
    }

    protected void theCarIsMoving() {
        float remainingFuel = fuelCapacities;
        while (remainingFuel > 5) {
            remainingFuel = countingTheRemainingFuel();
            System.out.printf("[%s] Едем...%n", stringID);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        refuelingACar();

    }
}
