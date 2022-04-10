<%@ page import="com.example.assignmentjspservlet.entity.ShoppingCart" %>
<%@ page import="com.example.assignmentjspservlet.entity.CartItem" %>
<%@ page import="java.util.ArrayList" %>
<%
    ShoppingCart shoppingCart = (ShoppingCart) request.getAttribute("shoppingCart");
    ArrayList<CartItem> cartItems = shoppingCart.getListItems();
    float totalPrice = 0;
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="icon" href="/images/logo.png" type="image/x-icon" sizes="20x20">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/header-style.css">
    <link rel="stylesheet" href="/css/table-custom.css">
    <title>Shopping Cart</title>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>

    <%
        if (session.getAttribute("Message") != null) {
    %>
    <div class="alert alert-success" role="alert">
        <%= session.getAttribute("Message") %>
    </div>
    <%
            session.removeAttribute("Message");
        }
    %>
    <h1 class="text-start p-3">Shopping Cart <small>HaiLua</small></h1>
    <ul class="responsive-table">
        <%
            for (CartItem item : cartItems
            ) {
        %>
        <li class="table-row">
            <div class="col col-2"><img src="<%= item.getProductThumbnail() %>" width="80"></div>
            <div class="col col-4"><%= item.getProductName() %>
            </div>
            <div class="col col-1"><%= "$" + item.getUnitPrice() %>
            </div>
            <div class="col col-2">
                <form action="carts/update-product" class="d-flex justify-content-center">
                    <input type="hidden" name="productId" value="<%= item.getProductId()%>">
                    <input id="quantity_input" name="quantity" class="form-control" style="max-width: 100px" type="number"
                           value="<%= item.getQuantity() %>">
                    <button type="submit" class="btn btn-custom btn-custom-update">Update</button>
                </form>
            </div>
            <div class="col col-1">
                <a href="/carts/delete-from-cart?id=<%= item.getProductId() %>" type="button"
                   class="btn-action-icon btn-custom-delete btn-delete"><i class="bi bi-x-lg"></i></a>
            </div>
        </li>
        <% totalPrice += item.getUnitPrice() * item.getQuantity(); %>
        <%
            }
        %>
        <li class="table-row">
            <div class="col col-2"></div>
            <div class="col col-4"></div>
            <div class="col col-1"> Total: <%=" $ " + totalPrice%></div>
            <div class="col col-2">
                <a href="/products" class="btn btn-custom-checkout">Check Out <i class="bi bi-arrow-right"></i></a>
            </div>
            <div class="col col-1">
            </div>
        </li>
    </ul>
</div>
</body>
</html>

<style>
    .btn-custom-checkout {
        border-radius: 10px;
        transition: .3s;
    }

    .btn-custom-checkout i{
        transition: .3s;
    }

    .btn-custom-checkout:hover i{
        padding-left: 5px;
    }

    .btn-custom-update{
        background: #4c8084;
        color: #fff;
    }

    .btn-custom-update:hover{
        background: #387377;
        color: #fff;
    }
</style>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        let listDeleteButton = document.querySelectorAll('.btn-delete');
        for (let i = 0; i < listDeleteButton.length; i++) {
            listDeleteButton[i].addEventListener("click", function (event) {
                event.preventDefault();
                if (confirm("Are you sure?")) {
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            alert('Delete Success!');
                            window.location.reload();
                        }
                    }
                    xhr.open('DELETE', this.href, false);
                    xhr.send();
                }
            })
        }
    })
</script>
