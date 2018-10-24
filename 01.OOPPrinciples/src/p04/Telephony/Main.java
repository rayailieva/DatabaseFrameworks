package p04.Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        Smartphone smartphone = new Smartphone();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            Arrays.stream(reader.readLine().trim().split("\\s+"))
                    .forEach(number -> {
                        try {
                            smartphone.call(number);
                        } catch (IllegalArgumentException iae) {
                            System.out.println(iae.getMessage());
                        }
                    });


            Arrays.stream(reader.readLine().trim().split("\\s+"))
                    .forEach(number -> {
                        try {
                            smartphone.browse(number);
                        } catch (IllegalArgumentException iae) {
                            System.out.println(iae.getMessage());
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
