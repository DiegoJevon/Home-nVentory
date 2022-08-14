<%-- 
    Document   : useraccount
    Created on : Aug 3, 2022, 8:02:00 PM
    Author     : Diego Maia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME nVentory - New Account - User Account</title>
    </head>
    <body>
         <%@ include file="navbar_login.jsp" %>
         
         <div class="container-sm">
        
            <h2>Welcome <small class="text-muted">${user.firstName} ${user.lastName}</small>!</h2>

            <form actoun-="useraccount" method="post">
                <label class="form-label">Email: </label>
                <input class="form-control-plaintext" type="test" name="email" value="${user.email}" readonly><br>
                <label class="form-label">Password: </label>
                <input class="form-control" type="password" name="password" value="${user.password}" required><br>
                <label class="form-label">First Name: </label>
                <input class="form-control" type="text" name="first_name" value="${user.firstName}"required><br>
                <label class="form-label">Last Name: </label>
                <input class="form-control" type="text" name="last_name" value="${user.lastName}"required><br>
                <label class="form-label">Is Active?</label>
                <input class="form-check-input mt-1" type="checkbox" name="active" value="${user.active}" checked=""><br>
                <button type="submit" class="btn btn-success">Save</button>
            </form>          
        </div>
    </body>
</html>
