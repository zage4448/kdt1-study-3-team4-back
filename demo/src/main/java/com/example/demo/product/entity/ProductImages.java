package com.example.demo.product.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String imageResourcePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prduct_id")
    private Product product;

    public ProductImages(String imageResourcePath){
        this.imageResourcePath=imageResourcePath;
    }

    public ProductImages(String imageResourcePath, Product product) {
        this.imageResourcePath = imageResourcePath;
        this.product = product;
    }
}
