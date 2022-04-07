package com.example.assignmentjspservlet.controller.category;

import com.example.assignmentjspservlet.entity.Category;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCategoryController extends HttpServlet {
    private final GenericModel<Category> model = new GenericModel<>(Category.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Category obj = model.findById(id);
        if (obj == null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            req.setAttribute("category", obj);
            req.getRequestDispatcher("/admin/category/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Category obj = new Category();
        int id = Integer.parseInt(req.getParameter("id"));
        obj.setId(id);
        obj.setName(req.getParameter("name"));
        model.update(id, obj);
        resp.sendRedirect("/admin/categories/list");
    }
}
