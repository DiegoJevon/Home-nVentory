<%-- 
    Document   : createaccount
    Created on : Aug 3, 2022, 5:36:39 PM
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
        <title>HOME nVentory - New Account</title>
    </head>
    <body>
        <%@ include file="header_basic.jsp" %>
        
        <div class="container-sm">
            <h2>Create Your Account</h2>

            <form action="" method="post">
                <label class="form-label">Email: </label>
                <input class="form-control" type="email" name="new_email" value="" required><br>
                <label class="form-label">Password: </label>
                <input class="form-control" type="password" name="new_password" value="" required><br>
                <label class="form-label">First Name: </label>
                <input class="form-control" type="text" name="new_first_name" value="" required><br>
                <label class="form-label">Last Name: </label>
                <input class="form-control" type="text" name="new_last_name" value="" required><br>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="company" value="alpha" checked>
                    <label class="form-label">Alpha</label><br>
                    <input class="form-check-input" type="radio" name="company" value="beta">
                    <label class="form-label">Beta</label><br>
                </div>
                <div class="col mt-2">
               <button type="submit" class="btn btn-success">Create Account</button>
                    <a class="btn btn-primary link" href="login">Sign in</a> 
               </div>
                ${new_account_message}
            </form>                
        </div>
    </body>
</html>
