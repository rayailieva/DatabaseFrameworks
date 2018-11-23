package cardealer.web.controllers;

import cardealer.domain.dtos.*;
import cardealer.service.*;
import cardealer.util.FileIOUtil;
import cardealer.util.FileIOUtilImpl;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {

    private final static String SUPPLIER_FILE_PATH =
        "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\suppliers.json" ;
    private final static String PART_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\parts.json" ;
    private final static String CARS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\cars.json" ;
    private final static String CUSTOMER_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\customers.json" ;



    private final SupplierService supplierService;
    private final PartsService partsService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SalesService salesService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    public CarDealerController(SupplierService supplierService, PartsService partsService, CarService carService, CustomerService customerService, SalesService salesService, Gson gson) {
        this.supplierService = supplierService;
        this.partsService = partsService;
        this.carService = carService;
        this.customerService = customerService;
        this.salesService = salesService;
        this.fileIOUtil = new FileIOUtilImpl();
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedSuppliers();
        // this.seedParts();
        //this.seedCars();
        //this.seedCustomers();
        //this.seedSales();
        //this.orderedCustomers();
        this.getToyotaCars("Toyota");
    }

    private void getToyotaCars(String make) throws IOException {
        List<CarViewDto> carsFromToyotaDtos = this.carService.getCarsByMake(make);

        String carsFromToyotaDtoJson = this.gson.toJson(carsFromToyotaDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\toyota-cars.json");

        FileWriter writer = new FileWriter(file);
        writer.write(carsFromToyotaDtoJson);
        writer.close();
    }

    private void orderedCustomers() throws IOException {
        List<OrderedCustomersDto> orderedCustomersDtos = this.customerService.getOrderedCustomers();

        String orderedCustomersJson = this.gson.toJson(orderedCustomersDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\ordered-customers.json");

        FileWriter writer = new FileWriter(file);
        writer.write(orderedCustomersJson);
        writer.close();
    }

    private void seedSales() {
        this.salesService.generateSales();
    }

    private void seedCustomers() throws IOException {
        String customersFileContent = this.fileIOUtil.readFile(CUSTOMER_FILE_PATH);

        CustomersSeedDto[] customersSeedDtos = this.gson.fromJson(customersFileContent, CustomersSeedDto[].class);

        this.customerService.seedCustomers(customersSeedDtos);
    }

    private void seedCars() throws IOException {
        String carsFileContent = this.fileIOUtil.readFile(CARS_FILE_PATH);

        CarsSeedDto[] carsSeedDtos = this.gson.fromJson(carsFileContent, CarsSeedDto[].class);

        this.carService.seedCars(carsSeedDtos);

    }

    private void seedSuppliers() throws IOException {
        String suppliersFileContent = this.fileIOUtil.readFile(SUPPLIER_FILE_PATH);

        SupplySeedDto[] supplySeedDtos = this.gson.fromJson(suppliersFileContent, SupplySeedDto[].class);

        this.supplierService.seedSuppliers(supplySeedDtos);
    }

    private void seedParts() throws IOException {
        String  partsFileContent = this.fileIOUtil.readFile(PART_FILE_PATH);

        PartsSeedDto[] partsSeedDtos = this.gson.fromJson(partsFileContent, PartsSeedDto[].class);

        this.partsService.seedParts(partsSeedDtos);
    }
}
