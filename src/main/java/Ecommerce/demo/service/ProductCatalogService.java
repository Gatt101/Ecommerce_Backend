package Ecommerce.demo.service;

import Ecommerce.demo.model.ProductCatalog;
import Ecommerce.demo.repository.ProductCatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogService {
    @Autowired
    private ProductCatalogRepo productCatalogRepo;

    public ProductCatalog getProductById(Long productId) {
        return productCatalogRepo.findByProductId(productId);
    }
} 