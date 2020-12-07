package lesson5.cars;

import lesson5.gasStation.FuelStation;

public class Car extends Cars {
    private final String stringId;

    public Car(FuelStation fuelStation) {
        super(fuelStation);
        super.setFuelFullCapacities(20);
        super.setFuelCapacities(20);
        super.setFuelConsumption(2.5f);
        stringId = getStringID();
    }

    @Override
    public void run() {
        super.run();
    }


}
