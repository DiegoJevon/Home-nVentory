<%-- 
    Document   : navbar_login
    Created on : Aug 5, 2022, 9:26:25 AM
    Author     : Diego Maia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
        <header>
            
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="login" class="navbar-brand">HOME nVentory</a>
            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav">
                    <a class="nav-item nav-link text-light" href="useraccount">User Profile</a>
                    <a class="nav-item nav-link text-light" href="inventory">Inventory</a>                    
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-light" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                          Admin
                        </a>
                        <ul class="dropdown-menu bg-dark">
                          <li><a class="dropdown-item text-light" href="admin?manage_users">Manage Users</a></li>
                          <li><hr class="dropdown-divider"></li>
                          <li><a class="dropdown-item text-light" href="admin?manage_categories">Manage Categories</a></li>
                          <li><hr class="dropdown-divider"></li>
                          <li><a class="dropdown-item text-light" href="admin?check_items">Check Items</a></li>
                          <li><hr class="dropdown-divider"></li>
                          <li><a class="dropdown-item text-light" href="admin?report">Reports</a></li>
                        </ul>
                </div>
                <div class="navbar-nav ms-auto">
                    <a class="nav-item nav-link text-light" href="login?logout">Logout</a>
                </div>
            </div>
        </div>
    </nav>
        </header>
</html>
