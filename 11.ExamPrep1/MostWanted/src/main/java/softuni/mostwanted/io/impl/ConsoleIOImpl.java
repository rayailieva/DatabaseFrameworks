package softuni.mostwanted.io.impl;


import softuni.mostwanted.io.interfaces.ConsoleIO;

public class ConsoleIOImpl implements ConsoleIO {
    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
