package Ferrari;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalClassFormatException {

        Scanner scanner = new Scanner(System.in);
        String driverName = scanner.nextLine();

        Car car = new Ferrari(driverName);

        System.out.println(car.toString());

        String ferrariName = Ferrari.class.getSimpleName();
        String carInterface = Car.class.getSimpleName();
        boolean isCreated = Car.class.isInterface();
        if (!isCreated) {
            throw new IllegalClassFormatException("No interface created!");
        }


    }
}
