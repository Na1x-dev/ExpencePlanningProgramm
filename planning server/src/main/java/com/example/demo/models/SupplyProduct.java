package com.example.demo.models;

import lombok.Data;

@Data
public class SupplyProduct {
    Product product;
    Supply supply;
    public SupplyProduct(Supply supply, Product product){
        this.supply = supply;
        this.product = product;
    }

    public SupplyProduct() {


    }

    @Override
    public String toString() {
        return "SupplyProduct{" +
                "product=" + product.getProductId() +
                ", supply=" + supply.getSupplyId() +
                '}';
    }
}