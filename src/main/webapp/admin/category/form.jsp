<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.assignmentjspservlet.entity.Category" %>
<%
    Category obj = (Category) request.getAttribute("category");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors");

    if (errors == null) {
        errors = new HashMap<>();
    }
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
    <link rel="stylesheet" href="/css/form-custom.css">
    <title>Create new category</title>
</head>
<body>
<div class="container">
    <h1 class="text-center"><p><%= obj.getId() == 0 ? "Create new category" : "Edit category" %>
    </p>
    </h1>
    <a href="/admin/categories/list" class="btn btn-custom mb-5"><i class="bi bi-arrow-left"></i> Back to list</a>
    <% if (errors.size() > 0) { %>
    <div class="alert alert-danger" role="alert">
        Please enter information below.
    </div>
    <% } %>
    <div class="form-section">
        <form action="<%= obj.getId() == 0 ? "/admin/categories/create" : "/admin/categories/update" %>" method="post">
            <div class="mb-3">
                <label class="form-label">Name</label>
                <input type="hidden" name="id" value="<%= obj.getId() %>">
                <input type="text" name="name" class="form-control"
                       value="<%= obj.getName() != null ? obj.getName() : "" %>">
                <% if (errors.containsKey("name")) { %>
                <p class="text-danger mt-2"><%=errors.get("name")%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="text-center">
                <button type="submit"
                        class="btn btn-custom btn-submit-custom"><%= obj.getId() == 0 ? "Submit" : "Save" %>
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
