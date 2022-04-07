package com.example.assignmentjspservlet.controller.product;

import com.example.assignmentjspservlet.entity.Product;
import com.example.assignmentjspservlet.model.GenericModel;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Product> model  = new GenericModel<>(Product.class);

        List<Product> result = model.getAll();

        CollectionUtils.filter(result, o -> ((Product) o).getStatus() == 1 || ((Product) o).getStatus() == 2);

        req.setAttribute("listProducts", result);

        req.getRequestDispatcher("/admin/product/list.jsp").forward(req, resp);
    }
}
