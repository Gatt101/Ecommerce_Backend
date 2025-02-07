package Ecommerce.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_product_history") // ✅ Ensure table name is correct
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // ✅ Proper Foreign Key Mapping
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // ✅ Reference User Entity

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "viewed_at", nullable = false)
    private Instant viewedAt;

    // ✅ Constructors
    public Product() {}

    public Product(User user, Long productId, String productName, Double price, Integer quantity, Instant viewedAt) {
        this.user = user;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.viewedAt = viewedAt;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Instant getViewedAt() { return viewedAt; }
    public void setViewedAt(Instant viewedAt) { this.viewedAt = viewedAt; }
}
