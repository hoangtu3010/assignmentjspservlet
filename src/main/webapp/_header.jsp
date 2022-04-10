<%@ page import="com.example.assignmentjspservlet.entity.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Account accountUser = (Account) session.getAttribute("currentAccount");
    Account accountAdmin = (Account) session.getAttribute("adminAccount");
    boolean isUserLoggedIn = false;
    boolean isAdminLoggedIn = false;
    if (accountUser != null) {
        isUserLoggedIn = true;
    }

    if (accountAdmin != null) {
        isAdminLoggedIn = true;
    }
%>
<ul class="nav justify-content-between" style="align-items: center;">
    <div class="logo">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="/"><img class="logo-img" src="/images/logo.png"
                                                                         alt=""></a>
        </li>
    </div>
    <div class="header-menu d-flex">
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="products">Products</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="carts">Cart</a>
        </li>
        <% if (isAdminLoggedIn) { %>
        <li class="nav-item">
            <a class="nav-link" href="admin/products/list">Products Manager</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="admin/categories/list">Categories Manager</a>
        </li>
        <% }%>
    </div>
    <div class="auth-button d-flex">
        <% if (isAdminLoggedIn) { %>
        <li class="nav-item">
            <a class="nav-link" href="accounts/admin-logout">Logout Admin</a>
        </li>
        <% } %>
        <% if (isUserLoggedIn) { %>
        <li class="nav-item">
            <a class="nav-link" href="accounts/user-logout">Logout User</a>
        </li>
        <% } else { %>
        <li class="nav-item">
            <a class="nav-link" href="accounts/login">Login</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="accounts/register">Register</a>
        </li>
        <% } %>
    </div>
</ul>
