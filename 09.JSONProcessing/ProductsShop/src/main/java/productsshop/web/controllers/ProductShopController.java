package productsshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productsshop.domain.dtos.CategorySeedDto;
import productsshop.domain.dtos.ProductSeedDto;
import productsshop.domain.dtos.UserSeedDto;
import productsshop.service.CategoryService;
import productsshop.service.ProductService;
import productsshop.service.UserService;
import productsshop.util.FileIOUtil;

import java.io.IOException;

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
       this.seedUsers();
       this.seedCategories();
       this.seedProducts();
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
}
