package com.example.l08_jsonprocessing.repository;

import com.example.l08_jsonprocessing.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(BigDecimal lower, BigDecimal upper);
}
