package cardealer.web.controllers;

import cardealer.domain.dtos.*;
import cardealer.service.*;
import cardealer.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Controller
public class CarDealerApp implements CommandLineRunner {

    private final static String SUPPLIERS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\import\\suppliers.xml";
    private final static String PARTS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\import\\parts.xml";
    private final static String CARS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\import\\cars.xml";
    private final static String CUSTOMERS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\import\\customers.xml";

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final SaleService saleService;
    private final CustomerService customerService;
    private final XmlParser xmlParser;

    @Autowired
    public CarDealerApp(SupplierService supplierService, PartService partService, CarService carService, SaleService saleService, CustomerService customerService, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.saleService = saleService;
        this.customerService = customerService;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.importSuppliers();
        //this.importParts();
        //this.importCars();
        //this.importCustomers();
        //this.importSales();

        //this.exportCustomers();
        //this.exportToyotaCars();
       // this.exportLocalSuppliers();
       // this.exportCarsAndParts();
       // this.exportCustomersTotalSales();
    }

    private void exportCustomersTotalSales() throws JAXBException {
        CustomerPurchaseExportRootDto customerPurchaseExportRootDto = this.customerService.getCustomersPurchases();

        this.xmlParser.exportToXml(customerPurchaseExportRootDto, CustomerPurchaseExportRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\export\\customers-total-sales.xml");

    }


    private void exportCarsAndParts() throws JAXBException {
        CarAndPartsExportRootDto carAndPartsExportRootDto = this.carService.getAllCarsWithParts();

        this.xmlParser.exportToXml(carAndPartsExportRootDto, CarAndPartsExportRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\export\\cars-and-parts.xml");
    }

    private void exportLocalSuppliers() throws JAXBException {
        SupplierExportRootDto supplierExportRootDto = this.supplierService.getAllLocalSuppliers();

        this.xmlParser.exportToXml(supplierExportRootDto, SupplierExportRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\export\\local-suppliers.xml");
    }

    private void exportToyotaCars() throws JAXBException {
        CarExportRootDto carExportRootDto = this.carService.getAllByModel("Toyota");

        this.xmlParser.exportToXml(carExportRootDto, CarExportRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\export\\toyota-cars.xml");
    }

    private void exportCustomers() throws JAXBException {
        CustomersExportRootDto customersExportRootDto = this.customerService.getAllSortedByBirthday();

        this.xmlParser.exportToXml(customersExportRootDto, CustomersExportRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\cardealerxml\\src\\main\\resources\\files\\export\\ordered-customers.xml");
    }

    private void importSales() {
        this.saleService.generateSales();
    }

    private void importCustomers() throws JAXBException, FileNotFoundException {
        CustomerImportRootDto customerImportRootDto = this.xmlParser
                .parseXml(CustomerImportRootDto.class, CUSTOMERS_XML_FILE_PATH);

        this.customerService.importCustomers(customerImportRootDto);
    }

    private void importCars() throws JAXBException, FileNotFoundException {
        CarImportRootDto carImportRootDto = this.xmlParser
                .parseXml(CarImportRootDto.class, CARS_XML_FILE_PATH);

        this.carService.importCars(carImportRootDto);
    }

    private void importParts() throws JAXBException, FileNotFoundException {
        PartImportRootDto partImportRootDto = this.xmlParser
                .parseXml(PartImportRootDto.class, PARTS_XML_FILE_PATH);

        this.partService.importParts(partImportRootDto);
    }

    public void importSuppliers() throws JAXBException, FileNotFoundException {
        SupplierImportRootDto supplierImportRootDto = this.xmlParser
                .parseXml(SupplierImportRootDto.class, SUPPLIERS_XML_FILE_PATH);

        this.supplierService.importSuppliers(supplierImportRootDto);
    }
}
