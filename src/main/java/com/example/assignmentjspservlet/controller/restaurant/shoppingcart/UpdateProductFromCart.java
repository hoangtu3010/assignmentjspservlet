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

public class UpdateProductFromCart extends HttpServlet {
    private final GenericModel<Product> model;

    public UpdateProductFromCart() {
        model = new GenericModel<>(Product.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = 0;
        int quantity = 0;

        productId = Integer.parseInt(req.getParameter("productId"));
        quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = model.findById(productId);

        if (product == null){
            resp.getWriter().println("Product is not found.");
            return;
        }

        ShoppingCart shoppingCart = ShoppingCartFactory.getShoppingCartFromSession(req);
        shoppingCart.updateItemFromCart(product, quantity);
        ShoppingCartFactory.setShoppingCartToSession(req, shoppingCart);
        req.getSession().setAttribute("Message", "Update success!");
        resp.setStatus(200);
        resp.sendRedirect("/carts");
    }
}
