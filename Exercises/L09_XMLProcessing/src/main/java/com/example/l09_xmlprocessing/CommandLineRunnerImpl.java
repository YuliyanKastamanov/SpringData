package com.example.l09_xmlprocessing;

import com.example.l09_xmlprocessing.model.dto.*;
import com.example.l09_xmlprocessing.service.CategoryService;
import com.example.l09_xmlprocessing.service.ProductService;
import com.example.l09_xmlprocessing.service.UserService;
import com.example.l09_xmlprocessing.util.XmlParser;
import jakarta.xml.bind.JAXBException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String RESOURCES_FILE_PATH = "src/main/resources/files/";
    private static final String OUTPUT_FILE_PATH = "out/";
    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.xml";
    private static final String SOLD_PRODUCTS_FILE_NAME = "sold_products.xml";

    private final XmlParser xmlParser;
    private final CategoryService categoryService;

    private final UserService userService;

    private final ProductService productService;

    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(XmlParser xmlParser, CategoryService categoryService, UserService userService, ProductService productService) {
        this.xmlParser = xmlParser;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Select Exercise: ");
        int exNumber = Integer.parseInt(bufferedReader.readLine());

        switch (exNumber){
            case 1 -> productsInRange();
            case 2 -> usersWithSoldProducts();
        }

    }

    private void usersWithSoldProducts() throws JAXBException {

        UserViewRootDto userViewRootDto = userService
                .findUsersWithMoreThanOneSoldProduct();

        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + SOLD_PRODUCTS_FILE_NAME,
                userViewRootDto);
    }

    private void productsInRange() throws JAXBException {

        ProductViewRootDto rootDto = productService.findProductsInRangeWithoutBuyer();

        xmlParser.writeToFile(RESOURCES_FILE_PATH + OUTPUT_FILE_PATH + PRODUCTS_IN_RANGE_FILE_NAME,
                rootDto);


    }

    private void seedData() throws JAXBException, FileNotFoundException {

        if(categoryService.getEntityCount() == 0) {
            CategorySeedRootDto categorySeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + CATEGORIES_FILE_NAME, CategorySeedRootDto.class);

            categoryService.seedCategories(categorySeedRootDto.getCategories());

        }

        if (userService.getCount() == 0){
            UserSeedRootDto userSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + USERS_FILE_NAME, UserSeedRootDto.class);

            userService.seedUsers(userSeedRootDto.getUsers());
        }

        if(productService.getCount() == 0){
            ProductSeedRootDto productSeedRootDto = xmlParser
                    .fromFile(RESOURCES_FILE_PATH + PRODUCTS_FILE_NAME, ProductSeedRootDto.class);


            productService.seedProducts(productSeedRootDto.getProducts());

        }



    }
}
