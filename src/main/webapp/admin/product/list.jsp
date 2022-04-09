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
    <link rel="stylesheet" href="/css/table-custom.css">
    <link rel="stylesheet" href="/css/pagination-style.css">
    <title>List Products</title>
</head>
<body>
<div class="container">
    <h1>List Products <small>Foods</small></h1>
    <div class="d-flex justify-content-end mb-5">
        <a class="btn button-custom" href="/admin/products/create"><i class="bi bi-plus-circle"></i> Create</a>
    </div>
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Id</div>
            <div class="col col-1">Thumbnail</div>
            <div class="col col-1">Name</div>
            <div class="col col-1">Description</div>
            <div class="col col-1">Price</div>
            <div class="col col-1">Status</div>
            <div class="col col-1">Created At</div>
            <div class="col col-1">Last Modified</div>
            <div class="col col-1">Category</div>
            <div class="col col-1">Action</div>
        </li>
        <%
            for (Product p : (List<Product>) request.getAttribute("listProducts")
            ) {
        %>
        <li class="table-row">
            <div class="col col-1"><%= p.getId() %></div>
            <div class="col col-1"><img src="<%= p.getThumbnail() %>" width="80"></div>
            <div class="col col-1"><%= p.getName() %></div>
            <div class="col col-1"><%= p.getDescription() %></div>
            <div class="col col-1"><%= "$" + p.getPrice() %></div>
            <div class="col col-1"><span class="<%= p.getStatus() == 1 ? "status-on-selling" : "status-stop-selling" %>"><%= p.getStatus() == 1 ? "On Sale" : p.getStatus() == 2 ? "Stop selling" : "Deleted" %></span></div>
            <div class="col col-1"><%= p.getCreatedAt() %></div>
            <div class="col col-1"><%= p.getLastModified() %></div>
            <div class="col col-1"><%= p.getCategoryId() %></div>
            <div class="col col-1">
                <a href="/admin/products/update?id=<%= p.getId() %>" type="button" class="btn-action-icon btn-custom-edit"><i
                        class="bi bi-pencil-square"></i></a>
                <a href="/admin/products/delete?id=<%= p.getId() %>" type="button" class="btn-action-icon btn-custom-delete btn-delete"><i
                        class="bi bi-trash3"></i></a>
            </div>
        </li>
        <%
            }
        %>
    </ul>
    <div class="pagination-section">
        <a href="/admin/products/list?page=<%= currentPage - 1 %>" class="<%=currentPage == 1 ? "non-active" : ""%>">Previous</a>

        <% for (int i = 0; i < noOfPages; i++) { %>
        <a class="<%=currentPage == i + 1 ? "active" : ""%>" href="/admin/products/list?page=<%= i + 1 %>"><%=i + 1%></a>
        <% } %>

        <a href="/admin/products/list?page=<%= currentPage + 1 %>" class="<%=currentPage == noOfPages ? "non-active" : ""%>">Next</a>
    </div>
</div>
</body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        let listDeleteButton = document.querySelectorAll('.btn-delete');
        for (let i = 0; i < listDeleteButton.length; i++){
            listDeleteButton[i].addEventListener("click", function (event){
                event.preventDefault();
                if (confirm("Are you sure?")){
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function (){
                        if (xhr.readyState === 4 && xhr.status === 200){
                            alert('Delete Success!');
                            window.location.reload();
                        }
                    }
                    xhr.open('GET', this.href, false);
                    xhr.send();
                }
            })
        }
    })
</script>