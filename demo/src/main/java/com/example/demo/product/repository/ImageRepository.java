package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ProductImages,Long> {
}
