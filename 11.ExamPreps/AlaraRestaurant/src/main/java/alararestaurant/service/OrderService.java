package alararestaurant.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface OrderService {

    Boolean ordersAreImported();

    String readOrdersXmlFile() throws IOException;

    String importOrders() throws JAXBException, ParseException, FileNotFoundException;

    String exportOrdersFinishedByTheBurgerFlippers();
}
