package lesson5.cars;

public class Bus extends Cars {
    //    private int id;
    private final String stringId;

    public Bus() {
        super(Cars.getIdCars());
        super.setFuelFullCapacities(40);
        super.setFuelConsumption(7.5f);
        super.setFuelCapacities(getFuelFullCapacities());
//        id = Cars.getIdCars();
        stringId = getStringID();
    }

    @Override
    public void run() {
        super.run();
    }
}
