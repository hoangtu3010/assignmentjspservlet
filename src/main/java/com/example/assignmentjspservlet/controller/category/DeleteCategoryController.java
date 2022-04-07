package com.example.assignmentjspservlet.controller.category;

import com.example.assignmentjspservlet.entity.Category;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Category> obj = new GenericModel<>(Category.class);
        int id = Integer.parseInt(req.getParameter("id"));
        obj.delete(id);
        resp.sendRedirect("/admin/categories/list");
    }
}
