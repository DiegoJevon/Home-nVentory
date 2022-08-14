package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Company;
import models.Role;
import models.User;
import services.AccountService;
import services.CompanyService;
import services.RoleService;

/**
 *
 * @author Diego Maia
 */
public class UserAccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();        
        String email = (String) session.getAttribute("email");        
        AccountService as = new AccountService();
        User user = as.get(email);        
        session.setAttribute("user", user);              
        
         getServletContext().getRequestDispatcher("/WEB-INF/useraccount.jsp").forward(request,response);
        return;
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        AccountService as = new AccountService();
        RoleService rs = new RoleService();
        CompanyService compServ = new CompanyService();
        User user = null;
        Company company = null;
        
        Role role;
        try {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        User userAccount = as.get(email);
        company = userAccount.getCompany();

        boolean active = request.getParameter("active") != null;
            role = rs.get(2);
            
            user = new User(email, active, firstName, lastName, password, role, company, null);
            as.update(user);
        } catch (Exception ex) {
            Logger.getLogger(UserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("useraccount");
        return;

    }

}
