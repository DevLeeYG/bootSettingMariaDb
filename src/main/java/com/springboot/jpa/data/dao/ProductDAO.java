package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.Product;

public interface ProductDAO {
    Product insertProduct(Product product); // 엔티티의 정보 그대로 데이터베이스에 넣을것

    Product selectProduct(Long number); //

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;


}
