package lesson5.cars;

public class Car extends Cars {
    // private int id;
    private final String stringId;


    public Car() {
        super(Cars.getIdCars());
        super.setFuelFullCapacities(20);
        super.setFuelConsumption(2.5f);
        super.setFuelCapacities(getFuelFullCapacities());
//        id = Cars.getIdCars();
        stringId = getStringID();
//        super.id++;
    }

    @Override
    public void run() {
        super.run();
    }


}
