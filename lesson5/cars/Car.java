package lesson5.cars;

public class Car extends Cars {
    private final String stringId;


    public Car() {
        super(Cars.getIdCars());
        super.setFuelFullCapacities(20);
        super.setFuelConsumption(2.5f);
        super.setFuelCapacities(getFuelFullCapacities());
        stringId = getStringID();
    }

    @Override
    public void run() {
        super.run();
    }


}
