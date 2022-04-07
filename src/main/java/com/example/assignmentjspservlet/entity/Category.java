package com.example.assignmentjspservlet.entity;

import com.example.assignmentjspservlet.annotation.Column;
import com.example.assignmentjspservlet.annotation.Id;
import com.example.assignmentjspservlet.annotation.Table;

import java.util.HashMap;

@Table(name = "categories")
public class Category {
    @Id(autoIncrement = true)
    @Column(name = "id", type = "INT")
    private int id;
    @Column(name = "name", type = "VARCHAR(250)")
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    private HashMap<String, String> errors;

    private void checkValid(){
        this.errors = new HashMap<>();

        if (this.name == null || this.name.length() == 0){
            this.errors.put("name", "Name is required.");
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
}
