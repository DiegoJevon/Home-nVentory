<%-- 
    Document   : admin
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
        <title>HOME nVentory - Admin Page</title>
    </head>
    <body>
        <%@ include file="navbar_login.jsp" %>
        
        <div class="container-fluid text-center mt-5">
            <a class="btn btn-secondary" href="admin?manage_users">Manage Users</a>
            <a class="btn btn-secondary" href="admin?manage_categories">Manage Categories</a>
            <a class="btn btn-secondary" href="admin?check_items">Check Items</a>
            <a class="btn btn-secondary" href="admin?report">Create Report</a>
        </div>
        
        <c:if test="${page == 'manage_users'}">   

    <div class="container-fluid mx-5 text-center">
        <div class="container-fluid mx-5 mt-3 text-center">
            <div class="container-sm">
            
            <div class="row">
                <div class="col-md">
                    
                    <h2>Manage Users</h2>

                    <table class="table table-hover table-light">
                        <tr>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Delete</th>
                            <th>Edit</th>
                        </tr>
                            <c:forEach items="${users}" var="user">
                            <tr>
                                <td>${user.email}</td>                           
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>
                                    <form action="admin?users" method="post">  
                                        <button type="submit" class="btn btn-danger">Delete</button>
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="email_delete" value="${user.email}">
                                    </form></td>
                                <td>
                                    <form action="admin?users" method="get"> 
                                        <button type="submit" class="btn btn-primary">Edit</button>
                                        <input type="hidden" name="action" value="edit" >
                                        <input type="hidden" name="email_edit" value="${user.email}" >
                                    </form></td>
                            </tr>                        
                        </c:forEach>                   
                    </table>
                </div>
                <div class="col-sm">
                    <h2>${message_click}</h2>
                    <form class="row gy-2 gx-3 align-items-center" action="admin" method="post">
                            <div class="col-md-3">
                                <label class="form-label">Email:</label>
                            </div>
                            <div class="col-sm-9">
                                <c:choose>
                                    <c:when test="${check}">
                                        <input class="form-control-plaintext"  type="email" name="email_save" value="${user.email}" readonly>
                                    </c:when>
                                    <c:otherwise>
                                        <input class="form-control"  type="email" name="email_save" placeholder="Email" value="${user.email}" required>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        <div class="col-md-3">
                            <label class="form-label">Password:</label>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control"  type="password" name="password_save" placeholder="Password" value="${user.password}" required>
                        </div>
                        <div class="col-md-3">
                        <label class="form-label">First Name:</label>
                        </div>
                        <div class="col-md-9">
                        <input class="form-control"  type="text" name="first_name_save" placeholder="First Name" value="${user.firstName}" required>
                        </div>
                        <div class="col-md-3">
                        <label class="form-label">Last Name:</label>
                        </div>
                        <div class="col-md-9">
                        <input class="form-control"  type="text" name="last_name_save" placeholder="Last Name" value="${user.lastName}" required>
                        </div>
                        <div class="row">
                            <div class="col">
                            <label class="form-label">Is Active?</label>
                            </div>
                            <div class="col">
                            <c:choose>
                                <c:when test="${user.active}">
                                    <input class="form-check-input" type="checkbox" name="active_save" value="${user.active}" checked>
                                </c:when>
                                <c:otherwise>
                                     <input class="form-check-input"  type="checkbox" name="active_save" value="${user.active}">
                                </c:otherwise>
                            </c:choose>
                            </div>
                            <div class="col">
                            </div>
                            <div class="col">
                            </div>
                        </div>
                        <div class="row-cols">
                            <div class="col">
                            <label class="form-label">Company</label>
                            </div>
                            <div class="col">
                                
                                    <c:if test="${userLogin.company.companyId != 1}">
                                            
                                            <input class="form-check-input" type="radio" name="company_save" value="${userLogin.company.companyName}" checked>
                                            <label class="form-label">${userLogin.company.companyName}</label>
                                            
                                    </c:if>
                                    <c:if test="${userLogin.company.companyId == 1}">
                                        
                                        <c:choose>
                                            <c:when test="${user.company.companyName == 'alpha'}">
                                            
                                            <input class="form-check-input" type="radio" name="company_save" value="system" >
                                            <label class="form-label">System</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="alpha" checked>
                                            <label class="form-label">Alpha</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="beta">
                                            <label class="form-label">Beta</label>
                                            
                                            </c:when>
                                            
                                            <c:when test="${user.company.companyName == 'beta'}">
                                            
                                            <input class="form-check-input" type="radio" name="company_save" value="system" >
                                            <label class="form-label">System</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="alpha">
                                            <label class="form-label">Alpha</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="beta" checked>
                                            <label class="form-label">Beta</label>
                                            
                                            </c:when>
                                            
                                            <c:when test="${user.company.companyName == 'system'}">
                                                
                                            <input class="form-check-input" type="radio" name="company_save" value="system" checked>
                                            <label class="form-label">System</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="alpha">
                                            <label class="form-label">Alpha</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="beta">
                                            <label class="form-label">Beta</label>
                                            
                                            </c:when>
                                            
                                            <c:otherwise>
                                        
                                            <input class="form-check-input" type="radio" name="company_save" value="system">
                                            <label class="form-label">System</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="alpha">
                                            <label class="form-label">Alpha</label>
                                            <input class="form-check-input" type="radio" name="company_save" value="beta">
                                            <label class="form-label">Beta</label>
                                            
                                            </c:otherwise>
                                            
                                        </c:choose>
                                        
                                    </c:if>
                                                             
                            </div>
                           
                            </div>
                                              
                        <div class="col-md-12">
                        <select class="form-select p-1 bg-light text-secondary-dark" name="role_save">
                            <c:if test="${userLogin.company.companyId == 1}">
                            <c:forEach items="${roles}" var="role">
                                <c:choose>                                    
                                        <c:when test="${role == user.role}">
                                            <option selected>${role.roleName}</option>
                                            </c:when>
                                            <c:otherwise>
                                            <option>${role.roleName}</option>
                                            </c:otherwise>                                   
                                </c:choose>
                            </c:forEach>
                            </c:if>
                            <c:if test="${userLogin.company.companyId != 1}">
                            <c:forEach items="${roles}" var="role">
                                <c:if test="${role.roleId != 1}">
                                    <c:choose>                                            
                                        <c:when test="${role == user.role}">
                                        <option selected>${role.roleName}</option>
                                        </c:when>
                                        <c:otherwise>
                                        <option>${role.roleName}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                            </c:if>
                        </select>
                        </div>
                        <div class="col-md-12 mt-3">
                        <button type="submit" class="btn btn-success">Save</button>
                        <c:choose>
                            <c:when test="${message_click == 'Add User'}">
                                <input type="hidden" name="action" value="insert">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="action" value="update">
                            </c:otherwise>
                        </c:choose>                    
                        <p>${save_message}</p>                                                 
                        </div>                                           
                        
                           
                    </form>
                </div> 
            </div>
        </div>
                        
        </div>

    </c:if>
    <c:if  test="${page == 'manage_categories'}"> 
            <div class="container-fluid text-center mx-5 mt-3">
                <div class="container-sm">
                <div class="row">
                    <div class="col-sm-7">
                        <h2>Manage Categories</h2>
                        <table class="table table-hover table-light mt-4">
                            <tr>
                                <th>Category</th>
                                <th>Edit</th>
                            </tr>
                                <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td>${category.categoryName}</td> 
                                    <td>
                                        <form action="admin" method="get"> 
                                            <button type="submit" class="btn btn-primary">Edit Category</button>
                                            <input type="hidden" name="action" value="cat_edit" >
                                            <input type="hidden" name="category_edit" value="${category.categoryId}" >
                                        </form></td>
                                </tr>                        
                            </c:forEach>                   
                        </table>
                    </div>
                    <div class="col-sm-4 ms-4">
                        <h2>${message_click_category}</h2>
                        <form action="admin" method="post">
                            <input class="form-control" type="text" name="category_save" placeholder="Category Name" value="${category.categoryName}"><br>                
                            <button type="submit" class="btn btn-success">Save</button>
                            <input type="hidden" name="action" value="save_category">
                            <input type="hidden" name="categoryID_save" value="${category.categoryId}">
                            <p>${save_message_category}</p>            
                        </form>
                    </div>
                </div>
                </div>
            </div>                        
    </c:if>
         <c:if  test="${page == 'check_items'}"> 
            <div class="container-fluid text-center mx-5 mt-3">
                <div class="container-sm">
                <div class="row">
                    
                        <input class="form-control" text="search" placeholder="Search item here" name="search" value="" id="myInput">
                    </div>
                     </div>
                <div class="container-sm">
                    <div class="col-sm">
                        <h2>Check Items</h2>
                        
                        <table class="table table-hover table-light mt-4">
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                                <th>Owner</th>
                                <th>Company</th>
                            </tr>
                            
                            
                                <c:forEach items="${items}" var="item">
                                    
                                    <c:if test="${item.owner.company == userLogin.company}">
                                        
                                        <tr>
                                            <tbody id="myList">
                                            <td>${item.itemName}</td>                                
                                            <td>${item.price}</td>                                
                                            <td>${item.owner.email}</td> 
                                            <td>${item.owner.company.companyName}</td> 
                                            
                                        </tr>
                                    </c:if>
                                        <c:if test="${userLogin.role.roleId == 1}">
                                        <tr>
                                            <tbody id="myList">
                                            <td>${item.itemName}</td>                                
                                            <td>${item.price}</td>                                
                                            <td>${item.owner.email}</td> 
                                            <td>${item.owner.company.companyName}</td>
                                            </tbody>
                                        </tr>
                                    </c:if>
                                        
                            </c:forEach>
                            </div> 
                        </table>
                        </div>
                </div>
            </div>
        </c:if>
        
        <c:if  test="${page == 'report'}"> 
            <div class="container-fluid text-center mx-5 mt-3">
                <div class="container-sm">
                <div class="row">
                    <div class ="col-sm">
                        <form action="" method="get">                            
                            <button type="submit" class="btn btn-success">Inventory Report</button>
                            <input type="hidden" name="action" value="user_inventory">                            
                         
                        </form>
                </div>
                    <div class="col-sm">
                        <form action="" method="get">                            
                             <button type="submit" class="btn btn-success">User Report</button>
                            <input type="hidden" name="action" value="user_list">                                 
                        </form>                        
                    </div> 
                </div>
                </div>
               
            </div>
        </c:if>
    </div>
                                  
    
        <script>
        $(document).ready(function(){
          $("#myInput").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myList tr").filter(function() {
              $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
          });
        });
        </script>
    </body>
</html>
