<%-- 
    Document   : login
    Created on : Aug 02, 2022, 3:00:15 PM
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
        <title>HOME nVentory - Login</title>
    </head>
    <body>
        <%@ include file="header_basic.jsp" %>

        <div class="container-sm">
            <h2>Login</h2>
        <form action="" method="post">

            <input class="form-control" type="text" name="email" value="" placeholder="Email"><br>
            
            <input class="form-control" type="password" name="password" value="" placeholder="Password"><br>
            <div class="col mt-2">
                <button type="submit" class="btn btn-success">Submit</button>
                <a class="btn btn-primary link" href="createaccount">Create New Account</a>                
            </div>
            
        </form>
            <div class="row">
             <p>Did you forget your password?</p>
             <div class="col-sm">  
             <form action="reset" method="get">                
            <button type="submit" class="btn btn-danger">Forgot my password</button>
              </div>
              </form>
            </div>
            <p>${message}</p>
        </div>
    </body>
</html>
