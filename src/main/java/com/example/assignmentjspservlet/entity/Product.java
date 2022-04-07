package com.example.assignmentjspservlet.entity;

import com.example.assignmentjspservlet.annotation.Column;
import com.example.assignmentjspservlet.annotation.ForeignKey;
import com.example.assignmentjspservlet.annotation.Id;
import com.example.assignmentjspservlet.annotation.Table;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

@Table(name = "products")
public class Product {
    @Id(autoIncrement = true)
    @Column(name = "id", type = "INT")
    private int id;

    @Column(name = "name", type = "VARCHAR(255)")
    private String name;

    @Column(name = "thumbnail", type = "TEXT")
    private String thumbnail;

    @Column(name = "description", type = "TEXT")
    private String description;

    @Column(name = "price", type = "DOUBLE")
    private double price;

    @Column(name = "status", type = "INT")
    private int status;

    @Column(name = "createdAt", type = "DATETIME")
    private Timestamp createdAt;

    @Column(name = "lastModified", type = "DATETIME")
    private Timestamp lastModified;

    @ForeignKey(referenceTable = "categories", referenceColumn = "id")
    @Column(name = "categoryId", type = "INT")
    private int categoryId;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                ", categoryId=" + categoryId +
                '}';
    }

    public Product() {
    }

    public Product(int id, String name, String thumbnail, String description, double price, int status, Timestamp createdAt, Timestamp lastModified, int categoryId) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.categoryId = categoryId;
    }

    public Product(String name, String thumbnail, String description, double price, int status, Timestamp createdAt, Timestamp lastModified, int categoryId) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModified = lastModified;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private HashMap<String, String> errors;

    private void checkValid(){
        this.errors = new HashMap<>();

        if (this.name == null || this.name.length() <= 7){
            this.errors.put("name", "Name must be longer than 7 characters");
        }

        if(this.thumbnail == null || this.thumbnail.length() == 0){
            this.errors.put("thumbnail", "Thumbnail is required");
        }

        if(this.price <= 0){
            this.errors.put("price", "Price is required");
        }

        if(this.categoryId <= 0){
            this.errors.put("categoryId", "Category is required");
        }
    }

    public HashMap<String, String> getErrors() {
        checkValid();
        return errors;
    }

    public boolean isValid(){
        checkValid();
        return errors == null || errors.size() == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
