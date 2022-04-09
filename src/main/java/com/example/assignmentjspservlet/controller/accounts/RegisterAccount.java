package com.example.assignmentjspservlet.controller.accounts;

import com.example.assignmentjspservlet.entity.Account;
import com.example.assignmentjspservlet.model.GenericModel;
import com.example.assignmentjspservlet.util.PasswordHash;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

public class RegisterAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericModel<Account> accountModel = new GenericModel<>(Account.class);

        String userName = req.getParameter("userName");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");

        // Validate data
        Account account = new Account();
        account.setUserName(userName);
        account.setFullName(fullName);
        String salt = PasswordHash.generateSalt();
        account.setSalt(salt);
        String passwordHash = PasswordHash.getMd5(password, salt);
        account.setPassword(passwordHash);
        account.setCreatedAt(Calendar.getInstance().getTime().toString());
        account.setStatus(1);
        account.setRole(1);
        boolean result = accountModel.save(account);
        if (result){
            resp.sendRedirect("/accounts/login");
        }else {
            resp.getWriter().println("Errors occurs, please try again later!");
        }
    }
}
