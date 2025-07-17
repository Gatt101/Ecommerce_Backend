package Ecommerce.demo.Controller;

import Ecommerce.demo.model.Product;
import Ecommerce.demo.service.ProductService;
import Ecommerce.demo.service.UserService;
import Ecommerce.demo.service.ProductCatalogService;
import Ecommerce.demo.model.ProductCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/Orders/{id}")
    public List<Product> getProducts(@PathVariable String id) {
        return productService.getProductsByUserId(id); 
    }

    @PostMapping("/Orders")
    public Product addProduct(@RequestBody Product product, Principal principal) {
        try {
            System.out.println("Received Order: " + product);
    
            if (product != null) {
                String username = principal.getName();
                String name = userService.getNameByUsername(username);
                product.setUserId(name);
                product.setViewedAt(Instant.now());
    
                if (product.getProductId() != null) {
                    ProductCatalog catalog = productCatalogService.getProductById(product.getProductId());
                    if (catalog != null) {
                        product.setProductName(catalog.getProductName());
                        product.setPrice(catalog.getPrice());
                    } else {
                        System.out.println("Error: Product catalog entry not found for productId: " + product.getProductId());
                    }
                } else {
                    System.out.println("Error: productId is null for product: " + product);
                }
                return productService.addProduct(product);
            } else {
                System.out.println("Error: Received null product");
                return null;
            }
        } catch (Throwable e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    @PostMapping("/Orders/multiple")
    public List<Product> addMultipleProducts(@RequestBody List<Product> products, Principal principal) {
        try {
            System.out.println("Received Orders: " + products);
    
            if (products != null) {
                String username = principal.getName();
                String name = userService.getNameByUsername(username);
                for (Product product : products) {
                    product.setUserId(name);
                    product.setViewedAt(Instant.now());
    
                    if (product.getProductId() != null) {
                        ProductCatalog catalog = productCatalogService.getProductById(product.getProductId());
                        if (catalog != null) {
                            product.setProductName(catalog.getProductName());
                            product.setPrice(catalog.getPrice());
                        } else {
                            System.out.println("Error: Product catalog entry not found for productId: " + product.getProductId());
                        }
                    } else {
                        System.out.println("Error: productId is null for product: " + product);
                    }
                }
                return productService.addMultipleProducts(products);
            } else {
                System.out.println("Error: Received null products");
                return null;
            }
        } catch (Throwable e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}