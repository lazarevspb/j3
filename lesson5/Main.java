package lesson5;

import lesson5.cars.Bus;
import lesson5.cars.Car;
import lesson5.cars.Cars;
import lesson5.cars.Truck;
import lesson5.gasStation.FuelStation;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int NUMBER_OF_CARS = 9;


    public static void main(String[] args) {
        ArrayList<Cars> carsArrayList = new ArrayList<>();
//        FuelStation fuelStation = new FuelStation();
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_CARS);

        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            carsArrayList.add(getCars(i));
            executorService.submit(carsArrayList.get(i));
        }

        executorService.shutdown();
    }

    protected static Cars getCars(int i) {
        return i < NUMBER_OF_CARS / 3
                ? new Car()
                : i > NUMBER_OF_CARS / 3 && i <= NUMBER_OF_CARS / 3 + NUMBER_OF_CARS / 3 ? new Bus()
                : new Truck();
    }

}
