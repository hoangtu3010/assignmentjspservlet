<%@ page import="com.example.assignmentjspservlet.entity.Product" %>
<%@ page import="java.util.List" %>
<%
    int currentPage = (Integer) request.getAttribute("currentPage");
    int noOfPages = (Integer) request.getAttribute("noOfPages");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/pagination-style.css">
    <title>List Products</title>
</head>
<body>
<div class="container">
    <jsp:include page="_header.jsp"/>
    <div class="row mt-5 p-4">
        <%
            for (Product p : (List<Product>) request.getAttribute("productList")
            ) {
        %>
        <div class="col-md-3">
            <div class="product-wrapper mb-45 text-center">
                <div class="product-img">
                    <a href="#" data-abc="true"> <img src="<%= p.getThumbnail() %>" alt=""></a>
                    <span class="price-tag text-center"><%= "$" + p.getPrice() %></span>
                    <span class="card-title text-center"> <%= p.getName() %> </span>
                    <div class="product-action">
                        <div class="product-action-style">
                            <a href="carts/add-to-cart?productId=<%=p.getId()%>&quantity=1"> <i class="bi bi-cart3"></i> </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="pagination-section">
        <a href="/products?page=<%= currentPage - 1 %>" class="<%=currentPage == 1 ? "non-active" : ""%>">Previous</a>

        <% for (int i = 0; i < noOfPages; i++) { %>
        <a class="<%=currentPage == i + 1 ? "active" : ""%>" href="/products?page=<%= i + 1 %>"><%=i + 1%></a>
        <% } %>

        <a href="/products?page=<%= currentPage + 1 %>" class="<%=currentPage == noOfPages ? "non-active" : ""%>">Next</a>
    </div>
</div>
</body>
</html>

<style>
    body {
        vertical-align: middle;
        display: flex;
    }

    .product-wrapper,
    .product-img {
        overflow: hidden;
        position: relative;
    }

    .product-img{
        height: 350px;
    }

    .product-img img{
        width: 100%;
        height: 100%;
        object-fit: fill;
    }

    .mb-45 {
        margin-bottom: 45px
    }

    .product-action {
        bottom: 0;
        left: 0;
        opacity: 0;
        position: absolute;
        right: 0;
        text-align: center;
        transition: all 0.6s ease 0s;
    }

    .product-wrapper {
        border-radius: 10px
    }

    .product-img > .price-tag {
        background-color: #fff;
        box-shadow: 0 0 8px 1.7px rgba(0, 0, 0, 0.06);
        color: #333;
        border-radius: 5px;
        display: inline-block;
        font-size: 12px;
        font-weight: 500;
        left: 20px;
        letter-spacing: 1px;
        padding: 3px 12px;
        position: absolute;
        text-align: center;
        text-transform: uppercase;
        top: 20px
    }

    .product-img > .category-tag {
        background-color: #fff;
        box-shadow: 0 0 8px 1.7px rgba(0, 0, 0, 0.06);
        color: #333;
        border-radius: 5px;
        display: inline-block;
        font-size: 12px;
        font-weight: 600;
        right: 20px;
        letter-spacing: 1px;
        padding: 3px 12px;
        position: absolute;
        text-align: center;
        text-transform: uppercase;
        top: 20px
    }

    .product-img > .card-title{
        position: absolute;
        bottom: 20px;
        background-color: #fff;
        color: #333;
        display: inline-block;
        left: 50%;
        transform: translateX(-50%);
        width: 100%;
        padding: 10px;
        transition: .3s;
    }

    .product-action-style {
        background-color: #fff;
        box-shadow: 0 0 8px 1.7px rgba(0, 0, 0, 0.06);
        display: inline-block;
        padding: 16px 2px 12px;
        border-radius: 10px;
        transition: .3s;
    }

    .product-action-style:hover {
        background-color: #edc54dcf;
    }

    .product-action-style > a {
        color: #979797;
        line-height: 1;
        padding: 0 21px;
        position: relative
    }

    .product-action-style > a i{
        font-size: 20px;
    }

    .product-action-style:hover > a i{
        color: #fff;
    }

    .product-wrapper:hover .product-action {
        bottom: 20px;
        opacity: 1;
    }

    .product-wrapper:hover .card-title{
        bottom: 80px;
    }
</style>