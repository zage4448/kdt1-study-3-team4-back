package com.example.demo.product.service;

import com.example.demo.product.entity.Product;
import com.example.demo.product.form.RegisterRequestProductForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    Boolean register(RegisterRequestProductForm info, List<MultipartFile> fileList);
}
