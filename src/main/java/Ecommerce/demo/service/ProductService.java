package Ecommerce.demo.service;

import Ecommerce.demo.model.Product;
import Ecommerce.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    public ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }



    public List<Product> getProductsByid(Long id) {
        return productRepo.findAllById(Collections.singleton(id));
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepo.findByuser_id(userId); // ✅ Fetch all products by userId
    }

    public List<Product> addMultipleProducts(List<Product> products) {
        return productRepo.saveAll(products);
    }
}
