package lesson5.cars;

import lesson5.gasStation.FuelStation;

public class Truck extends Cars{
    private String stringId;

    public Truck(FuelStation fuelStation) {
        super(fuelStation);
        super.setFuelFullCapacities(60);
        super.setFuelCapacities(60);
        super.setFuelConsumption(15f);
        stringId = getStringID();
    }

    @Override
    public void run() {
        super.run();
    }
}
