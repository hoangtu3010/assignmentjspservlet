package com.example.assignmentjspservlet.controller.restaurant;

import com.example.assignmentjspservlet.entity.Product;
import com.example.assignmentjspservlet.model.GenericModel;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListProductOnSell extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4;
        if (req.getParameter("page") != null){
            page = Integer.parseInt(req.getParameter("page"));
        }

        GenericModel<Product> model  = new GenericModel<>(Product.class);

        List<Product> result = model.getPage((page - 1) * recordsPerPage, recordsPerPage);

        CollectionUtils.filter(result, o -> ((Product) o).getStatus() == 1);

        int noOfRecords = model.getNoOfRecords();
        int noOfPages = (int)Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("productList", result);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/productlist.jsp").forward(req, resp);
    }
}
