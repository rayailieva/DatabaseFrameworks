package fastfood.web.controllers;

import com.google.gson.Gson;
import fastfood.domain.dtos.EmployeeImportDto;
import fastfood.domain.dtos.ItemImportDto;
import fastfood.domain.dtos.orders.OrderImportRootDto;
import fastfood.service.EmployeeService;
import fastfood.service.ItemService;
import fastfood.service.OrderService;
import fastfood.util.FileUtil;
import fastfood.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ApplicationController implements CommandLineRunner {

    private final static String EMPLOYEES_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\FastFood\\src\\main\\resources\\files\\json\\employees.json";
    private final static String ITEMS_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\FastFood\\src\\main\\resources\\files\\json\\items.json";
    private final static String ORDERS_FILE_CONTENT =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\11.ExamPreps\\FastFood\\src\\main\\resources\\files\\xml\\orders.xml";

    private final EmployeeService employeeService;
    private final ItemService itemService;
    private final OrderService orderService;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public ApplicationController(EmployeeService employeeService, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ItemService itemService, OrderService orderService, XmlParser xmlParser) {
        this.employeeService = employeeService;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.itemService = itemService;
        this.orderService = orderService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.importEmployees();
        //this.importItems();
        //this.importOrders();
    }

    private void importOrders() throws JAXBException, FileNotFoundException {
        OrderImportRootDto orderImportRootDto = this.xmlParser.parseXml(OrderImportRootDto.class, ORDERS_FILE_CONTENT);

        this.orderService.importOrders(orderImportRootDto);
    }

    private void importItems() throws IOException {
        String itemsFileContent = this.fileUtil.readFile(ITEMS_FILE_CONTENT);

        ItemImportDto[] itemImportDtos = this.gson.fromJson(itemsFileContent, ItemImportDto[].class);
        this.itemService.importItems(itemImportDtos);
    }

    private void importEmployees() throws IOException {
        String employeesFileContent = this.fileUtil.readFile(EMPLOYEES_FILE_CONTENT);

        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employeesFileContent, EmployeeImportDto[].class);
        this.employeeService.importCustomers(employeeImportDtos);
    }
}
