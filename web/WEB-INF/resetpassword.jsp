<%-- 
    Document   : resetpassword
    Created on : Aug 12, 2022, 6:27:02 PM
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
        <title>HOME nVentory - Reset Password</title>
    </head>
    <body>
        <%@ include file="header_basic.jsp" %>
        <div class="container-sm">
        <h1>Reset Password</h1>
        <p>Please enter your new password.</p>
        
        <form action ="" method="post">
            <div class="row">
                <div class="col-sm-2">
                    <label class="form-label">New Password: </label>
                </div>
                <div class="col-sm-3">
                    <input class="form-control" type="password" name="password"><br>
                </div>
            </div>
            <button type="submit" class="btn btn-success">Reset Password</button>
            <input type="hidden" name="action" value="reset">
        </form>
        </div>
    </body>
</html>

