<%-- 
    Document   : inventory
    Created on : Aug 02, 2022, 3:00:15 PM
    Author     : Diego Maia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory - Inventory</title>
    </head>
    <body>
        <%@ include file="navbar_login.jsp" %>

        <div class="container text-center mt-5">
            <div class="row">            
                <div class="col-sm-8">
                    <h2>Inventory for <small class="text-muted">${firstName} ${lastName}</small></h2>
                        <table class="table table-hover table-light mt-4">
                            <tr>
                                <th>Category</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th></th>
                                <th></th>                 
                            </tr>
                            <c:forEach items="${items}" var="item">
                                <tr>
                                    <td>${item.category.categoryName}</td>
                                    <td>${item.itemName}</td>
                                    <td>${item.price}</td>
                                    <td>
                                        <form action="inventory" method="post">
                                            <button type="submit" class="btn btn-danger">Delete</button>
                                            <input type="hidden" name="action" value="delete">
                                            <input type="hidden" name="item_delete" value="${item.itemId}">
                                        </form></td>
                                    <td>
                                    <form action="inventory" method="get">                            
                                       <button type="submit" class="btn btn-primary">Edit</button>
                                        <input type="hidden" name="action" value="edit">
                                        <input type="hidden" name="item_edit" value="${item.itemId}">   
                                    </form></td>
                                </tr>                
                            </c:forEach>              
                         </table>
                </div>
                    <div class="col-sm-3 ms-5">
                        <h2>${message_item_click}</h2>
                        <form class="row g-1" action="inventory" method="post">
                                <label class="col-sm-2 col-form-label">Category: </label>
                                    <select class="form-select p-1 bg-light text-secondary-dark" name="selected_category">
                                        <c:forEach items="${categories}" var="category">
                                            <c:choose>
                                                <c:when test="${category == item.category}">
                                                    <option selected value="${category.categoryId}">${category.categoryName}</option> 
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${category.categoryId}">${category.categoryName}</option> 
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>

                                <label class="col-sm-2 col-form-label">Name: </label>
                                <input class="form-control" type="text" name="item" value="${item.itemName}" placeholder="Item name" required><br>
                                <label class="col-sm-2 col-form-label">Price: </label>
                                <input class="form-control" type="" name="price" value="${item.price}" placeholder="Item price" required><br>
                                <button type="submit" class="btn btn-success">Save</button>
                                <input type="hidden" name="action" value="insert">
                                <input type="hidden" name="item_id" value="${item.itemId}">

                        </form>
                        <p class="text-secondary-dark">${message_item}</p>
                    </div>
            </div>
        </div>
    </body>
</html>
