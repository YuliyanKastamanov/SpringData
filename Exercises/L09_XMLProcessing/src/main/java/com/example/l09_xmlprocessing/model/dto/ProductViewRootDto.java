package com.example.l09_xmlprocessing.model.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductViewRootDto {

    @XmlElement(name = "product")
    private List<ProductWithSellerDto> products;

    public List<ProductWithSellerDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithSellerDto> products) {
        this.products = products;
    }
}
