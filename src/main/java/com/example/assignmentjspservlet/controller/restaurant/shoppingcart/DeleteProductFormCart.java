package com.example.assignmentjspservlet.controller.restaurant.shoppingcart;

import com.example.assignmentjspservlet.entity.Product;
import com.example.assignmentjspservlet.entity.ShoppingCart;
import com.example.assignmentjspservlet.model.GenericModel;
import com.example.assignmentjspservlet.util.ShoppingCartFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductFormCart extends HttpServlet {
    private final GenericModel<Product> productModel;

    public DeleteProductFormCart() {
        productModel = new GenericModel<>(Product.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = 0;

        try {
            productId = Integer.parseInt(req.getParameter("id"));
        }catch (Exception e){
            resp.getWriter().println("Invalid parameter.");
            return;
        }

        Product product = productModel.findById(productId);

        if (product == null){
            resp.getWriter().println("Product is not found.");
            return;
        }

        ShoppingCart shoppingCart = ShoppingCartFactory.getShoppingCartFromSession(req);
        shoppingCart.removeItemFromCart(product);
        ShoppingCartFactory.setShoppingCartToSession(req, shoppingCart);
        req.getSession().setAttribute("Message", "Delete success!");
        resp.setStatus(200);
    }
}
