package com.springboot.jpa.service.impl;


import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;


    @Autowired
    public ProductServiceImpl(ProductDAO productDAO){
        this.productDAO = productDAO;
    }

    //컨트롤러에서 서비스를 부를것이다 넘버를 보내줄것이다
    //getProduct는 상품을 불러와야되니 넘버로 db를검색해야한다 그래서 dao 를 연결한다
    //그럼 dao 를 불러와야한다. 그럼 db객체를 들고 올것이고 이것을 담을 그릇이 필요하다.
    // 그릇은 엔티티 타입의 변수이다
    // 불러온후 그대로 놔두고 응답 dto에 담아서 리턴하면 컨트롤러로 보내지게된다.
    @Override
    public ProductResponseDto getProduct(Long number) {

        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(productResponseDto.getPrice());
        productResponseDto.setStock(productResponseDto.getStock());

        return productResponseDto;

    }


    /*
    서비스에서 저장하는 로직을 구현하려면
    들어온 dto 객체로 엔티티에 저장을 해야한다
     */
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

       Product product = new Product();

       product.setName(productDto.getName());
       product.setPrice(productDto.getPrice());
       product.setUpdatedAt(LocalDateTime.now());
       product.setCreatedAt(LocalDateTime.now());

       Product saveProduct = productDAO.insertProduct(product);

       ProductResponseDto productResponseDto = new ProductResponseDto();

       productResponseDto.setNumber(saveProduct.getNumber());//dao에서 save 결과를 리턴해주기때문에 넘버를 받을수있다
       productResponseDto.setName(saveProduct.getName());
       productResponseDto.setPrice(saveProduct.getPrice());
       productResponseDto.setStock(saveProduct.getStock());


       return productResponseDto;
    }

    /*
    *엔티티생성자를 가져온다
    *
    */
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {



        Product changeProductName = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setNumber(changeProductName.getNumber());
        productResponseDto.setName(changeProductName.getName());
        productResponseDto.setPrice(changeProductName.getPrice());
        productResponseDto.setStock(changeProductName.getStock());



        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        productDAO.deleteProduct(number);

    }
}
