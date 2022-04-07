<%@ page import="com.example.assignmentjspservlet.entity.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.example.assignmentjspservlet.entity.Category" %>
<%@ page import="java.util.List" %>
<%
    Product obj = (Product) request.getAttribute("product");
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="/css/form-custom.css">
    <title>Create new products</title>
</head>
<body>
<div class="container">
    <h1 class="text-center"><p><%= obj.getId() == 0 ? "Create new product" : "Edit product" %>
    </p>
    </h1>
    <a href="/admin/products/list" class="btn btn-custom mb-5"><i class="bi bi-arrow-left"></i> Back to list</a>
        <% if (errors.size() > 0) { %>
    <div class="alert alert-danger" role="alert">
        Please enter information below.
    </div>
        <% } %>
    <div class="form-section">
        <form action="<%= obj.getId() == 0 ? "/admin/products/create" : "/admin/products/update" %>" method="post">
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
            <div class="mb-3">
                <label class="form-label">Thumbnail</label>
                <input type="text" name="thumbnail" class="form-control"
                       value="<%= obj.getThumbnail() != null ? obj.getThumbnail() : "" %>">
                <% if (errors.containsKey("thumbnail")) { %>
                <p class="text-danger mt-2"><%=errors.get("thumbnail")%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="mb-3">
                <label class="form-label">Description</label>
                <input type="text" name="description" class="form-control"
                       value="<%= obj.getDescription() != null ? obj.getDescription() : "" %>">
                <% if (errors.containsKey("description")) { %>
                <p class="text-danger mt-2"><%=errors.get("description")%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="mb-3">
                <label class="form-label">Price</label>
                <input type="number" name="price" class="form-control" aria-describedby="emailHelp"
                       value="<%= obj.getPrice() != 0 ? obj.getPrice() : 0 %>">
                <% if (errors.containsKey("price")) { %>
                <p class="text-danger mt-2"><%=errors.get("price")%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="input-group mb-3">
                <label class="input-group-text" for="inputSelectCategory">Category</label>
                <select class="form-select" name="categoryId" id="inputSelectCategory">
                    <% for (Category category : (List<Category>) request.getAttribute("listCategories")
                    ) { %>
                    <option value="<%= category.getId() != 0 ? category.getId() : 0 %>" <%=obj.getCategoryId() == category.getId() ? "selected" : ""%>><%=category.getName()%>
                    </option>
                    <% } %>
                </select>
                <% if (errors.containsKey("categoryId")) { %>
                <p class="text-danger mt-2"><%=errors.get("categoryId")%>
                </p>
                <%
                    }
                %>
            </div>
            <div class="input-group mb-3">
                <label class="input-group-text" for="inputSelectStatus">Status</label>
                <select class="form-select" name="status" id="inputSelectStatus">
                    <% for (int i = 0; i < 2; i++
                    ) { %>
                    <option value="<%= i + 1 %>" <%=obj.getStatus() == i + 1 ? "selected" : ""%>><%= i + 1 == 1 ? "On Sale" : "Stop selling"%>
                    </option>
                    <% } %>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-custom btn-submit-custom"><%= obj.getId() == 0 ? "Submit" : "Save" %>
                </button>
            </div>

        </form>
    </div>
</body>
</html>
