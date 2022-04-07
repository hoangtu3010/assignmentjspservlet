package com.example.assignmentjspservlet.controller.category;

import com.example.assignmentjspservlet.entity.Category;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCategoryController extends HttpServlet {
    private static Category obj;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj = new Category(0,"");
        req.setAttribute("category", obj);
        req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj.setName(req.getParameter("name"));

        if (!obj.isValid()){
            req.setAttribute("category", obj);
            req.setAttribute("errors", obj.getErrors());
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
            return;
        }

        GenericModel<Category> model = new GenericModel<>(Category.class);
        model.save(obj);
        resp.sendRedirect("/admin/categories/list");
    }
}
