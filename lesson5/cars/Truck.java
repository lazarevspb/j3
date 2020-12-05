package lesson5.cars;

public class Truck extends Cars{
//    private int id;
    private String stringId;

    public Truck() {
        super(Cars.getIdCars());
        super.setFuelFullCapacities(60);
        super.setFuelConsumption(15f);
        super.setFuelCapacities(getFuelFullCapacities());
        stringId = getStringID();
    }


    @Override
    public void run() {
        super.run();
    }
}
