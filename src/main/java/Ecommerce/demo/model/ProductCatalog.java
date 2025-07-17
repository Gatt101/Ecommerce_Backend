package Ecommerce.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_catalog")
public class ProductCatalog {
    @Id
    private Long productId;
    private String productName;
    private Double price;
    private String description;
    // Add other fields as needed

    public ProductCatalog() {}

    public ProductCatalog(Long productId, String productName, Double price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} 