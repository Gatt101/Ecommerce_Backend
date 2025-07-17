package Ecommerce.demo.repository;

import Ecommerce.demo.model.ProductCatalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatalogRepo extends MongoRepository<ProductCatalog, Long> {
    ProductCatalog findByProductId(Long productId);
} 