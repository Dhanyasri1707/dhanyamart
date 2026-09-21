  package com.dhanyamart.dhanyamart;

import jakarta.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String username;
    private int quantity;

    public Cart() {
    }

    public Cart(Long productId, String username, int quantity) {
        this.productId = productId;
        this.username = username;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getUsername() {
        return username;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}