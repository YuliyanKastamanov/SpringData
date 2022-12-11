package com.example.l08_jsonprocessing;

import com.example.l08_jsonprocessing.model.dto.ProductNameAndPriceDto;
import com.example.l08_jsonprocessing.model.dto.UserSoldDto;
import com.example.l08_jsonprocessing.service.CategoryService;
import com.example.l08_jsonprocessing.service.ProductService;
import com.example.l08_jsonprocessing.service.UserService;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    private static final String OUTPUT_PATH = "src/main/resources/files/out/";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.json";
    private static final String USERS_AND_SOLD_PRODUCTS = "users-and-sold-products";

    private final CategoryService categoryService;
    private final UserService userService;

    private final ProductService productService;

    private final BufferedReader bufferedReader;

    private final Gson gson;

    public CommandLineRunner(CategoryService categoryService, UserService userService, ProductService productService, Gson gson) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.gson = gson;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Enter exercise:");
        int exNumber = Integer.parseInt(bufferedReader.readLine());

        switch (exNumber){
            case 1 -> productsInRange();
            case 2 -> soldProducts();
            }

    }

    private void soldProducts() throws IOException {

        List<UserSoldDto> userSoldDtos = userService.findAllUsersWithMoreThanOneSoldProduct();
        String content = gson.toJson(userSoldDtos);
        writeToFile(OUTPUT_PATH + USERS_AND_SOLD_PRODUCTS, content);
    }

    private void productsInRange() throws IOException {

        List<ProductNameAndPriceDto> productDtos = productService
                .findAllProductsInRangeOrderByPrice(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String content = gson.toJson(productDtos);

        writeToFile(OUTPUT_PATH + PRODUCTS_IN_RANGE_FILE_NAME, content);

    }

    private void writeToFile(String filePath, String content) throws IOException {

        Files.write(Path.of(filePath), Collections.singleton(content));
    }

    private void seedData() throws IOException {

        categoryService.seedCategories();
        userService.seedUsers();
        productService.seedProducts();
    }
}
