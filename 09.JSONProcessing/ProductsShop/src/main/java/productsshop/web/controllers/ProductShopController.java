package productsshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productsshop.domain.dtos.*;
import productsshop.service.CategoryService;
import productsshop.service.ProductService;
import productsshop.service.UserService;
import productsshop.util.FileIOUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {

    private final static String USER_FILE_PATH = "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\users.json";
    private final static String CATEGORIES_FILE_PATH = "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\categories.json";
    private final static String PRODUCTS_FILE_PATH = "C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\products.json";


    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;
    private final Gson gson;

    public ProductShopController(CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {
       //this.seedUsers();
       //this.seedCategories();
       //this.seedProducts();

       //this.productsInRange();
       //his.usersSoldProducts();
       this.categoriesByProductsCount();
    }

    private void seedUsers() throws IOException {
        String usersFileContent = this.fileIOUtil.readFile(USER_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String categoryFileContent = this.fileIOUtil.readFile(CATEGORIES_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoryFileContent, CategorySeedDto[].class);

        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String productFileContent = this.fileIOUtil.readFile(PRODUCTS_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(productFileContent, ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);
    }

    private void productsInRange() throws IOException {
        List<ProductInRangeDto> productInRangeDtos = this.productService.productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String productsInRangeJson = this.gson.toJson(productInRangeDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\output\\products-in-range.json");

        FileWriter writer = new FileWriter(file);
        writer.write(productsInRangeJson);
        writer.close();
    }

    private void usersSoldProducts() throws IOException {
        List<UserFirstAndLastNamesAndSoldProductsDto> userFirstAndLastNamesAndSoldProductsDtos = this.userService.getSuccessfulSellers();

        String soldProductsJson = this.gson.toJson(userFirstAndLastNamesAndSoldProductsDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\output\\users-sold-products.json");

        FileWriter writer = new FileWriter(file);
        writer.write(soldProductsJson);
        writer.close();
    }

    private void categoriesByProductsCount() throws IOException {
        List<CategoryNameProductsCountAverageAndTotalPricesDto> categoryNameProductsCountAverageAndTotalPricesDtos =
                this.categoryService.getCategoriesByProductsCount();

        String categories = this.gson.toJson(categoryNameProductsCountAverageAndTotalPricesDtos);

        File file = new File("C:\\Users\\raya\\IdeaProjects\\JavaDatabaseAdvanced\\09.JSONProcessing\\ProductsShop\\src\\main\\resources\\files\\output\\categories-by-products.json");

        FileWriter writer = new FileWriter(file);
        writer.write(categories);
        writer.close();
    }
}
