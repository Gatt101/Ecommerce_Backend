package Ecommerce.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document(collection = "products")
public class Product {
    @MongoId
    private String id; 

    @JsonProperty("productId") 
    private String productId;

    @JsonProperty("productName") 
    private String productName;

    private double price;

    @JsonProperty("user_id") 
    private String userId;

    @JsonProperty("viewedAt")
    private Instant viewedAt;

    private int quantity;

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Instant getViewedAt() { return viewedAt; }
    public void setViewedAt(Instant viewedAt) { this.viewedAt = viewedAt; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Product{id='" + id + "', productId='" + productId + "', productName='" + productName + "', price=" + price + ", userId='" + userId + "', viewedAt=" + viewedAt + ", quantity=" + quantity + "}";
    }
}