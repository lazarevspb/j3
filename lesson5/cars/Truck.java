package lesson5.cars;

public class Truck extends Cars{
    private String stringId;

    public Truck() {
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
