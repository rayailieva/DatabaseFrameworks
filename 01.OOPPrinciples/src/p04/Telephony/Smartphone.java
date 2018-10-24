package p04.Telephony;

public class Smartphone implements Callable, Browsable {
    private String phoneNumber;
    private String site;

    @Override
    public String browse(String site) {
        if(isSiteValid(site)) {
            return "Browsing: " + site + "!";
        }
        throw new IllegalArgumentException("Invalid URL!");
    }

    @Override
    public String call(String number) {
        if(isNumberValid(number)) {
            return "Calling..." + number;
        }
        throw new IllegalArgumentException("Invalid number!");
    }

    private boolean isSiteValid(String site) {
        return site.matches("\\D*");
    }

    private boolean isNumberValid(String number) {
        return number.matches("\\d+");
    }
}
