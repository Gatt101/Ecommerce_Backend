package Ecommerce.demo.Controller;

import Ecommerce.demo.model.Product;
import Ecommerce.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController // Base path for cleaner URLs
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/Orders/{id}")
    public List<Product> getProducts(@PathVariable Long id) {
//        return productService.getProductsByid(id); // Fix: Use getProductsByid instead of getProductsByUserId
        return productService.getProductsByUserId(id); // ✅ Fetch all products by userId
    }

    @PostMapping("/Orders")
    public Product addProduct(@RequestBody Product product) {
        try {
            // ✅ Log request for debugging
            System.out.println("Received Order: " + product);

            // ✅ Ensure the order is saved correctly
            if (product != null) {
                product.setViewedAt(Instant.now()); // Set timestamp
                return productService.addProduct(product);
            } else {
                System.out.println("Error: Received null product");
                return null;
            }
        } catch (Exception e) {
            // ✅ Log error for debugging
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/Orders/multiple")
    public List<Product> addMultipleProducts(@RequestBody List<Product> products) { // Fix: Use @RequestBody instead of @PathVariable
        try {
            // ✅ Log request for debugging
            System.out.println("Received Orders: " + products);

            // ✅ Ensure the orders are saved correctly
            if (products != null) {
                for (Product product : products) {
                    product.setViewedAt(Instant.now()); // Set timestamp
                }
                return productService.addMultipleProducts(products);
            } else {
                System.out.println("Error: Received null products");
                return null;
            }
        } catch (Exception e) {
            // ✅ Log error for debugging
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
