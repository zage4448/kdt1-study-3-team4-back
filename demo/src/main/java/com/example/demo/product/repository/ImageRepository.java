package com.example.demo.product.repository;

import com.example.demo.product.entity.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<ProductImages,Long> {
    @Query("select pi from ProductImages pi join fetch pi.product p where p.id = :productId")
    List<ProductImages> findImagePathByProductId(Long productId);
}
