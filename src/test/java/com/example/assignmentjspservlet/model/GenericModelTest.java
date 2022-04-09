package com.example.assignmentjspservlet.model;

import com.example.assignmentjspservlet.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenericModelTest {

    @Test
    void getPage() {
        GenericModel<Product> studentGenericModel = new GenericModel<>(Product.class);
        ArrayList<Product> products = (ArrayList<Product>) studentGenericModel.getPage(0, 1);
        for (Product st : products
        ) {
            System.out.println(st.toString());
        }
    }
}