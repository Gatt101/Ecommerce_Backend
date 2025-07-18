package Ecommerce.demo.Controller;

import Ecommerce.demo.model.Product;
import Ecommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders") // Changed to lowercase for consistency
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable String userId) {
        List<Product> products = productService.getProductsByUserId(userId);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/single")
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product product, Principal principal) {
        // The frontend no longer sends user_id; always use the authenticated user
        String username = principal.getName();
        product.setUserId(username); // Always set userId from Principal
        product.setViewedAt(product.getViewedAt() == null ? Instant.now() : product.getViewedAt());

        // Validate required fields
        if (product.getProductId() == null || product.getProductName() == null || product.getPrice() <= 0 || product.getQuantity() <= 0) {
            throw new IllegalArgumentException("productId, productName, price, and quantity are required and must be valid");
        }

        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<Product>> addMultipleProducts(@Validated @RequestBody List<Product> products, Principal principal) {
        // The frontend no longer sends user_id; always use the authenticated user
        String username = principal.getName();
        for (Product product : products) {
            product.setUserId(username); // Always set userId from Principal
            product.setViewedAt(product.getViewedAt() == null ? Instant.now() : product.getViewedAt());
            if (product.getProductId() == null || product.getProductName() == null || product.getPrice() <= 0 || product.getQuantity() <= 0) {
                throw new IllegalArgumentException("productId, productName, price, and quantity are required and must be valid for all products");
            }
        }
        List<Product> savedProducts = productService.addMultipleProducts(products);
        return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
    }
}