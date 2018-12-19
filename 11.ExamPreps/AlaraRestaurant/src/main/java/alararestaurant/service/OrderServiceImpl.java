package alararestaurant.service;

import alararestaurant.constants.Constants;
import alararestaurant.domain.dtos.orders.ItemXmlImportDto;
import alararestaurant.domain.dtos.orders.OrderImportDto;
import alararestaurant.domain.dtos.orders.OrderImportRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String ORDERS_FILE_CONTENT =
            System.getProperty("user.dir") + "/src/main/resources/files/orders.xml";

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final OrderRepository orderRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, XmlParser xmlParser, OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {
        return this.fileUtil.readFile(ORDERS_FILE_CONTENT);
    }

    @Override
    public String importOrders() throws JAXBException, ParseException, FileNotFoundException {
        StringBuilder importResult = new StringBuilder();

        OrderImportRootDto orderImportRootDto = this.xmlParser.parseXml(OrderImportRootDto.class, ORDERS_FILE_CONTENT);

        for(OrderImportDto orderImportDto : orderImportRootDto.getOrderImportDtos()){

            Employee employee = this.employeeRepository
                    .findByName(orderImportDto.getEmployee())
                    .orElse(null);

            if(!this.validationUtil.isValid(orderImportDto) || employee == null){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }


            Order order = this.modelMapper.map(orderImportDto, Order.class);
            order.setEmployee(employee);
            order.setDateTime(LocalDateTime.parse(orderImportDto.getDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));

            Boolean hasInvalidItem = false;
            List<OrderItem> orderItems = new ArrayList<>();
            for(ItemXmlImportDto itemXmlImportDto : orderImportDto.getItems().getItemXmlImportDtos()){

                Item item = this.itemRepository
                        .findByName(itemXmlImportDto.getName())
                        .orElse(null);

                if (item == null) {
                    hasInvalidItem = true;
                    break;
                }

                OrderItem orderItem = new OrderItem();
                orderItem.setQuantity(itemXmlImportDto.getQuantity());
                orderItem.setOrder(order);
                orderItem.setItem(item);

                orderItems.add(orderItem);
            }

            if(!hasInvalidItem){
                orderRepository.saveAndFlush(order);
                for(OrderItem orderItem : orderItems){
                    orderItemRepository.saveAndFlush(orderItem);
                }
            } else {
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            importResult
                    .append(String.format("Order for %s on %s added", order.getCustomer(), order.getDateTime()))
                    .append(System.lineSeparator());
        }


        return importResult.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        StringBuilder exportResult = new StringBuilder();

        List<Order> orders =
                this.orderRepository.exportOrders("Burger Flipper");

        orders.forEach(order -> {
            exportResult
                    .append(String.format("Name: %s", order.getEmployee().getName()))
                    .append(System.lineSeparator())
                    .append("Orders:")
                    .append(System.lineSeparator())
                    .append(String.format("\tCustomer: %s", order.getCustomer()))
                    .append(System.lineSeparator())
                    .append("\tItems:")
                    .append(System.lineSeparator());

            order.getOrderItems().forEach(orderItem -> {
                exportResult
                        .append(String.format("\t\tName: %s", orderItem.getItem().getName()))
                        .append(System.lineSeparator())
                        .append(String.format("\t\tPrice: %.2f", orderItem.getItem().getPrice()))
                        .append(System.lineSeparator())
                        .append(String.format("\t\tName: %d", orderItem.getQuantity()))
                        .append(System.lineSeparator());
                exportResult.append(System.lineSeparator());
            });
        });

        return exportResult.toString().trim();
    }
}
