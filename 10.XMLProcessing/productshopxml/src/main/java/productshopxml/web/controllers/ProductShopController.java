package productshopxml.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productshopxml.domain.dtos.binding.CategorySeedRootDto;
import productshopxml.domain.dtos.binding.ProductSeedRootDto;
import productshopxml.domain.dtos.binding.UserSeedRootDto;
import productshopxml.domain.dtos.view.ProductInRangeDto;
import productshopxml.domain.dtos.view.ProductInRangeRootDto;
import productshopxml.service.CategoryService;
import productshopxml.service.ProductService;
import productshopxml.service.UserService;
import productshopxml.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final static String USERS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\productshopxml\\src\\main\\resources\\files\\input\\users.xml";

    private final static String PRODUCTS_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\productshopxml\\src\\main\\resources\\files\\input\\products.xml";

    private final static String CATEGORIES_XML_FILE_PATH =
            "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\productshopxml\\src\\main\\resources\\files\\input\\categories.xml";

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductShopController(UserService userService, ProductService productService, CategoryService categoryService, XmlParser xmlParser, ModelMapper modelMapper) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        // System.out.println(this.seedXmlUsers());
        //System.out.println(this.seedXmlProducts());
        // System.out.println(this.seedXmlCategories());

        this.getAllProductsInRange();
    }

    private void getAllProductsInRange() throws JAXBException {
        ProductInRangeRootDto products =
                this.productService.getAvailableProductsInPriceRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        this.xmlParser.exportToXml(products, ProductInRangeRootDto.class, "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\10.XMLProcessing\\productshopxml\\src\\main\\resources\\files\\output\\products-in-range.xml");


    }

    private String seedXmlUsers() throws JAXBException, FileNotFoundException {
        UserSeedRootDto userSeedRootDto =
                this.xmlParser.parseXml(UserSeedRootDto.class, USERS_XML_FILE_PATH);

        this.userService.seedUsers(userSeedRootDto);
        return "Imported users!";
    }

    private String seedXmlProducts() throws JAXBException, FileNotFoundException {
        ProductSeedRootDto productSeedRootDto =
                this.xmlParser.parseXml(ProductSeedRootDto.class, PRODUCTS_XML_FILE_PATH);

        this.productService.seedProducts(productSeedRootDto);
        return "Imported products!";
    }

    private String seedXmlCategories() throws JAXBException, FileNotFoundException {
        CategorySeedRootDto categorySeedRootDto =
                this.xmlParser.parseXml(CategorySeedRootDto.class, CATEGORIES_XML_FILE_PATH);

        this.categoryService.seedCategories(categorySeedRootDto);
        return "Imported categories!";
    }

}
