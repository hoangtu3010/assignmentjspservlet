<%@ page import="java.util.List" %>
<%@ page import="com.example.assignmentjspservlet.entity.Category" %>
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
    <title>List Categories</title>
</head>
<body>
<div class="container">
    <h1>List Categories</h1>
    <div class="d-flex justify-content-end mb-5">
        <a class="btn button-custom" href="/admin/categories/create"><i class="bi bi-plus-circle"></i> Create</a>
    </div>
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-3">Id</div>
            <div class="col col-4">Name</div>
            <div class="col col-3">Action</div>
        </li>
        <%
            for (Category p : (List<Category>) request.getAttribute("listCategories")
            ) {
        %>
        <li class="table-row">
            <div class="col col-3"><%= p.getId() %></div>
            <div class="col col-4"><%= p.getName() %></div>
            <div class="col col-3">
                <a href="/admin/categories/update?id=<%= p.getId() %>" type="button" class="btn-action-icon btn-custom-edit"><i
                        class="bi bi-pencil-square"></i></a>
                <a href="/admin/categories/delete?id=<%= p.getId() %>" type="button" class="btn-action-icon btn-custom-delete btn-delete"><i
                        class="bi bi-trash3"></i></a>
            </div>
        </li>
        <%
            }
        %>
    </ul>
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