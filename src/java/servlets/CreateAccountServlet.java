package servlets;

import services.AccountService;
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
import services.CompanyService;
import services.RoleService;

/**
 *
 * @author Diego Maia
 */
public class CreateAccountServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();        
        AccountService as = new AccountService();        
        String uuid = request.getParameter("uuid");
                
        try{
            User user = as.getByUUID(uuid);
           if (user != null){
               user.setUuid(null);
               user.setActive(true);
                as.update(user);
                session.setAttribute("message", "Account has been activated!");
                String path = getServletContext().getRealPath("/WEB-INF");
                as.welcome(user.getEmail(), path);
                response.sendRedirect("login");
                return;
           }
        }catch (Exception e){
            
        }
            
        
          getServletContext().getRequestDispatcher("/WEB-INF/createaccount.jsp").forward(request,response);
        return;
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        
        try {
            String email = request.getParameter("new_email");
            String password = request.getParameter("new_password");
            String firstName = request.getParameter("new_first_name");
            String lastName = request.getParameter("new_last_name");
            String companyName = request.getParameter("company");
                                    
            
            AccountService as = new AccountService();
            RoleService rs = new RoleService();
            CompanyService compServ = new CompanyService();
            Role role = rs.get(2);
            Company company = compServ.get(companyName);
            if (as.get(email) != null){
                session.setAttribute("new_account_message", "This email has been used already!");
                
            }else{
                as.insert(email, false, firstName, lastName, password, role, company);
                String path = getServletContext().getRealPath("/WEB-INF");
                String url = request.getRequestURL().toString();
                as.newAccount(email, path, url);
                session.setAttribute("new_account_message", "Account created. Please check your email to activate your account!");
            }            
            
            
                       
            
        } catch (Exception ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("createaccount");        
        return;
        
    }

}
