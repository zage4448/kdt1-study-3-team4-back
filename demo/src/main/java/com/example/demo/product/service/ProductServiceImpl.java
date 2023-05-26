package com.example.demo.product.service;

import com.example.demo.Account.UserTokenRepository;
import com.example.demo.Account.entity.Account;
import com.example.demo.Account.entity.UserToken;
import com.example.demo.Account.repository.AccountRepository;
import com.example.demo.product.entity.Product;
import com.example.demo.product.entity.ProductImages;
import com.example.demo.product.form.RegisterRequestProductForm;
import com.example.demo.product.repository.ImageRepository;
import com.example.demo.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private UserTokenRepository userTokenRepository;
    final private AccountRepository accountRepository;
    final private ImageRepository imageRepository;

    @Override
    public Boolean register(RegisterRequestProductForm info, List<MultipartFile> fileList) {
        Optional<UserToken> maybeUserToken = userTokenRepository.findByUserToken(info.getUserToken());
        if(maybeUserToken.isEmpty()){
            return false;
        }
        Long maybeBusinessId = maybeUserToken.get().getAccountId();
        Optional<Account> maybeBusiness = accountRepository.findById(maybeBusinessId);
        if(maybeBusiness.isEmpty()){
            return false;
        }

        if(maybeBusiness.get().getRole().equals("BUSINESS")){
            Product savedProduct = productRepository.save(info.toProduct());

            try{
                for(MultipartFile multipartFile: fileList){

                    String originalFileName = multipartFile.getOriginalFilename();
                    log.info("requestFilename: " +multipartFile.getOriginalFilename());
                    FileOutputStream writer =new FileOutputStream(
                            "/main/resources/UploadImgs" +
                                    //"../../../Vue/YeoulCho/frontend/src/assets/uploadImgs" +
                                    multipartFile.getOriginalFilename()
                    );
                    //하드디스크에 multipartFile을 기록할꺼야 경로랑 가져온 이름으로
                    writer.write(multipartFile.getBytes());
                    //byte파일로 기록할꺼야
                    writer.close();
                    //기록 끝! 그만할꺼야
                    ProductImages image = new ProductImages(originalFileName, savedProduct);
                    //fileTest 엔티티에 파일정보를 저장하겠다
                    log.info(originalFileName);
                    imageRepository.save(image);

                }
            }catch(FileNotFoundException e){
                e.printStackTrace();
                //파일이 존재하지 않을 때 에러 프린트해라
                return false;
            }catch(IOException e){
                e.printStackTrace();
                //입출력 에러(IO)않을 때 에러 프린트해라
                return false;
            }
        }else {return false;}

       return true;





    }
}
