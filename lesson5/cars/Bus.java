package lesson5.cars;

public class Bus extends Cars {
    private final String stringId;

    public Bus() {
        super.setFuelFullCapacities(40);
        super.setFuelCapacities(40);
        super.setFuelConsumption(7.5f);
        stringId = getStringID();
    }

    @Override
    public void run() {
        super.run();
    }
}
