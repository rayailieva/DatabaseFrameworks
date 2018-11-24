package cardealer.web.controllers;

import cardealer.domain.dtos.binding.CarsSeedDto;
import cardealer.domain.dtos.binding.CustomersSeedDto;
import cardealer.domain.dtos.binding.PartsSeedDto;
import cardealer.domain.dtos.binding.SupplySeedDto;
import cardealer.domain.dtos.view.*;
import cardealer.service.*;
import cardealer.util.FileIOUtil;
import cardealer.util.FileIOUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {

    private final static String SUPPLIER_FILE_PATH =
        "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\input\\suppliers.json" ;
    private final static String PART_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\input\\parts.json" ;
    private final static String CARS_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\input\\cars.json" ;
    private final static String CUSTOMER_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\input\\customers.json" ;



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
        //this.seedParts();
        //this.seedCars();
        // this.seedCustomers();
        //this.seedSales();

        //this.orderedCustomers();
        //this.getToyotaCars("Toyota");
        //this.getLocalSuppliers();
        this.getCarsAndParts();
        //this.getCustomersTotalSales();
        //this.getSalesDiscounts();
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

    private void orderedCustomers() throws IOException {
        List<OrderedCustomersDto> orderedCustomersDtos = this.customerService.getOrderedCustomers();

        String orderedCustomersJson = this.gson.toJson(orderedCustomersDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\ordered-customers.json");

        FileWriter writer = new FileWriter(file);
        writer.write(orderedCustomersJson);
        writer.close();
    }

    private void getToyotaCars(String make) throws IOException {
        List<CarViewDto> carsFromToyotaDtos = this.carService.getCarsByMake(make);

        String carsFromToyotaDtoJson = this.gson.toJson(carsFromToyotaDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\toyota-cars.json");

        FileWriter writer = new FileWriter(file);
        writer.write(carsFromToyotaDtoJson);
        writer.close();
    }

    private void getLocalSuppliers() throws IOException {
        List<SupplierViewDto> supplierViewDtos = this.supplierService.getLocalSuppliers();

        String localSuppliersJson = this.gson.toJson(supplierViewDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\local-suppliers.json");

        FileWriter writer = new FileWriter(file);
        writer.write(localSuppliersJson);
        writer.close();
    }

    private void getCarsAndParts() throws IOException {
        List<CarPartsViewDto> carPartsViewDtos = this.carService.getCarsWithParts();

        String carsPartsJson = this.gson.toJson(carPartsViewDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\cars-and-parts.json");

        FileWriter writer = new FileWriter(file);
        writer.write(carsPartsJson);
        writer.close();
    }


    private void getCustomersTotalSales() throws IOException {
        List<CustomerPurchasesViewDto> customerPurchasesViewDtos = this.customerService.getCustomersPurchases();

        String salesJson = this.gson.toJson(customerPurchasesViewDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\customers-total-sales.json");

        FileWriter writer = new FileWriter(file);
        writer.write(salesJson);
        writer.close();
    }

    private void getSalesDiscounts() throws IOException {
        List<SaleDetailsViewDto> saleDetailsViewDtos = this.salesService.getSalesDetails();

        String salesDetailsJson = this.gson.toJson(saleDetailsViewDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\CarDealer\\src\\main\\resources\\files\\output\\sales-discounts.json");

        FileWriter writer = new FileWriter(file);
        writer.write(salesDetailsJson);
        writer.close();
    }



}
