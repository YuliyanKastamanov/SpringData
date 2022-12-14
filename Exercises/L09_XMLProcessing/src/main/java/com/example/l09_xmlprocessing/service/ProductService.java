package com.example.l09_xmlprocessing.service;

import com.example.l09_xmlprocessing.model.dto.ProductSeedDto;
import com.example.l09_xmlprocessing.model.dto.ProductViewRootDto;

import java.util.List;

public interface ProductService {
    long getCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto findProductsInRangeWithoutBuyer();
}
