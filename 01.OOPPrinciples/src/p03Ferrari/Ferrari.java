package Ferrari;

public class Ferrari implements Car{
    private final String model = "488-Spider";
    private String driver;

    public Ferrari(String driver) {
        this.driver = driver;
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String pushGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return "488-Spider/Brakes!/Zadu6avam sA!/" + this.driver;
    }
}
