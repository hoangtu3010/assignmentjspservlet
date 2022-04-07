package com.example.assignmentjspservlet.controller.product;

import com.example.assignmentjspservlet.entity.Category;
import com.example.assignmentjspservlet.entity.Product;
import com.example.assignmentjspservlet.model.GenericModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class CreateProductController extends HttpServlet {
    private static Product obj;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        obj = new Product(0,"", "", "", 0, 1, currentTime, currentTime, 0);
        GenericModel<Category> model = new GenericModel<>(Category.class);
        List<Category> listCategories = model.getAll();

        req.setAttribute("product", obj);
        req.setAttribute("listCategories", listCategories);
        req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        obj.setName(req.getParameter("name"));
        obj.setThumbnail(req.getParameter("thumbnail"));
        obj.setDescription(req.getParameter("description"));
        obj.setPrice(Double.parseDouble(req.getParameter("price")));
        obj.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        obj.setStatus(Integer.parseInt(req.getParameter("status")));

        if (!obj.isValid()){
            GenericModel<Category> model = new GenericModel<>(Category.class);
            List<Category> listCategories = model.getAll();
            req.setAttribute("product", obj);
            req.setAttribute("listCategories", listCategories);
            req.setAttribute("errors", obj.getErrors());
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            return;
        }

        GenericModel<Product> model = new GenericModel<>(Product.class);
        model.save(obj);
        resp.sendRedirect("/admin/products/list");
    }
}
