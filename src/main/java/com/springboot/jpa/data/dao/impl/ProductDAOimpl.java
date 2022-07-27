package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductDAOimpl implements ProductDAO {

    private final ProductRepository productRepository;

//    i,s.u,d

   @Override
    public Product insertProduct(Product product){
       Product saveProduct = productRepository.save(product);
       return saveProduct;
   }

   @Override
    public Product selectProduct(Long number){
       Product selectProduct = productRepository.getReferenceById(number);
       return selectProduct;
   }

   @Override
   public Product updateProductName(Long number, String name) throws Exception{

        Optional<Product> selectProduct = productRepository.findById(number);

        Product updateProduct;
        if(selectProduct.isPresent()){

            Product product = selectProduct.get();

            product.setName(name);
            product.setUpdateAt(LocalDateTime.now());


            updateProduct = productRepository.save(product);
        }else{
            throw new Exception();
        }

      return updateProduct;
   }

    public void deleteProduct(Long number) throws Exception{

       Optional<Product> deleteProduct  = productRepository.findById(number);

       if(deleteProduct.isPresent()){
           Product product = deleteProduct.get();
           productRepository.delete(product);
       }else{
           throw  new Exception();
      }


    }


}
