package com.example.assignmentjspservlet.controller.category;

import com.example.assignmentjspservlet.entity.Category;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListCategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Category> model  = new GenericModel<>(Category.class);

        List<Category> listCategories = model.getAll();

        req.setAttribute("listCategories", listCategories);

        req.getRequestDispatcher("/admin/category/list.jsp").forward(req, resp);
    }
}
