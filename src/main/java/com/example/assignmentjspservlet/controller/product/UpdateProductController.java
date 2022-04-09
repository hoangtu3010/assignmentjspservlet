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

public class UpdateProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Product> modelProduct = new GenericModel<>(Product.class);
        int id = Integer.parseInt(req.getParameter("id"));
        Product obj = modelProduct.findById(id);
        if (obj == null){
            resp.setStatus(404);
            resp.getWriter().println("Not found");
        }else {
            GenericModel<Category> modelCategory = new GenericModel<>(Category.class);
            List<Category> listCategories = modelCategory.getAll();
            req.setAttribute("product", obj);
            req.setAttribute("listCategories", listCategories);
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        GenericModel<Product> modelProduct = new GenericModel<>(Product.class);
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        Product obj = new Product();
        int id = Integer.parseInt(req.getParameter("id"));
        Product productGetById = modelProduct.findById(id);
        obj.setId(id);
        obj.setName(req.getParameter("name"));
        obj.setThumbnail(req.getParameter("thumbnail"));
        obj.setDescription(req.getParameter("description"));
        obj.setPrice(Double.parseDouble(req.getParameter("price")));
        obj.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        obj.setStatus(Integer.parseInt(req.getParameter("status")));
        obj.setCreatedAt(productGetById.getCreatedAt());
        obj.setLastModified(currentTime);

        if (!obj.isValid()){
            GenericModel<Category> model = new GenericModel<>(Category.class);
            List<Category> listCategories = model.getAll();
            req.setAttribute("product", productGetById);
            req.setAttribute("listCategories", listCategories);
            req.setAttribute("errors", obj.getErrors());
            req.getRequestDispatcher("/admin/product/form.jsp").forward(req, resp);
            return;
        }

        modelProduct.update(id, obj);
        resp.sendRedirect("/admin/products/list");
    }
}
