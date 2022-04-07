package com.example.assignmentjspservlet.controller.product;

import com.example.assignmentjspservlet.entity.Product;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Product> model = new GenericModel<>(Product.class);
        int id = Integer.parseInt(req.getParameter("id"));
        Product productGetById = model.findById(id);
        productGetById.setStatus(3);
        model.update(id, productGetById);
        resp.sendRedirect("/admin/products/list");
    }
}
