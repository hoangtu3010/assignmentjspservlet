package com.example.assignmentjspservlet.controller.accounts;

import com.example.assignmentjspservlet.entity.Account;
import com.example.assignmentjspservlet.model.GenericModel;
import com.example.assignmentjspservlet.util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Account> accountModel = new GenericModel<>(Account.class);

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        Account account = accountModel.findByUserName(userName);

        if (account == null){
            resp.getWriter().println("Invalid information!");
            return;
        }

        boolean result = PasswordHash.checkPassword(password, account.getPassword(), account.getSalt());

        if (result){
            HttpSession session = req.getSession();

            if (account.getRole() == 99){
                session.setAttribute("adminAccount", account);
            }else {
                session.setAttribute("currentAccount", account);
            }

            resp.sendRedirect("/");
        }else {
            resp.getWriter().println("Login fail!");
        }
    }
}
