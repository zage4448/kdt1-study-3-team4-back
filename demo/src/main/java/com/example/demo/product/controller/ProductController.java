package com.example.demo.product.controller;

import com.example.demo.Account.UserTokenRepository;
import com.example.demo.product.form.RegisterRequestProductForm;
import com.example.demo.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    final private ProductService productService;
    final private UserTokenRepository userTokenRepository;

    @PostMapping(value = "/register")
    public boolean registerProduct ( @RequestPart(value = "imageFile") List<MultipartFile> fileList,
                                  @RequestPart(value = "productInfo") RegisterRequestProductForm info){
        log.info("registerProduct()");

        return productService.register(info, fileList);
    }

}