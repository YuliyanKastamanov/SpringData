package com.example.l09_xmlprocessing.service.impl;

import com.example.l09_xmlprocessing.model.dto.ProductSeedDto;
import com.example.l09_xmlprocessing.model.dto.ProductViewRootDto;
import com.example.l09_xmlprocessing.model.dto.ProductWithSellerDto;
import com.example.l09_xmlprocessing.model.entity.Product;
import com.example.l09_xmlprocessing.repository.ProductRepository;
import com.example.l09_xmlprocessing.service.CategoryService;
import com.example.l09_xmlprocessing.service.ProductService;
import com.example.l09_xmlprocessing.service.UserService;
import com.example.l09_xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public long getCount() {

       return  productRepository.count();


    }

    @Override
    public void seedProducts(List<ProductSeedDto> products) {
        products.stream()
                .filter(validationUtil::isValid)
                .map(productSeedDto -> {
                    Product product = modelMapper.map(productSeedDto, Product.class);
                    product.setSeller(userService.getRandomUser());
                    if(product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0){
                        product.setBuyer(userService.getRandomUser());

                    }
                    product.setCategories(categoryService.getRandomCategories());

                    return product;
                })
                .forEach(productRepository::save);
    }

    @Override
    public ProductViewRootDto findProductsInRangeWithoutBuyer() {

        ProductViewRootDto rootDto = new ProductViewRootDto();
        rootDto.setProducts(productRepository
                .findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L))
                .stream().map(product -> {
                    ProductWithSellerDto productWithSellerDto = modelMapper
                            .map(product, ProductWithSellerDto.class);
                    productWithSellerDto.setSeller(String.format("%s %s",
                            product.getSeller().getFirstName() == null ? "" : product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productWithSellerDto;
                })
                .collect(Collectors.toList()));
        return rootDto;
    }
}
