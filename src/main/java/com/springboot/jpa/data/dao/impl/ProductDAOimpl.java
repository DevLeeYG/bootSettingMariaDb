package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDAOimpl implements ProductDAO {
    private final ProductRepository productRepository;


//    @Autowired
//    public  ProductDAOimpl(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }

    @Override
    public Product inserProduct(Product product){
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number){
        Product selectedProduct = productRepository.getReferenceById(number);
        return selectedProduct;
    } //

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

    @Override
    public  void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()){
            Product product = selectedProduct.get();

            productRepository.delete(product);
        }else{
            throw new Exception();
        }


    }


}
