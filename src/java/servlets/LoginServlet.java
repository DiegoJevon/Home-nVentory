
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author Diego Maia
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(request.getParameter("logout") != null){
        session.invalidate();
        request.setAttribute("message","You have successfully logged out.");
         getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    return;
    }        
        String email = (String) session.getAttribute("email");
    
    if (email != null){
        response.sendRedirect("inventory");
    return;
    }        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();        
        String email =  request.getParameter("email");
        String password = request.getParameter("password"); 
        
                
        AccountService as = new AccountService();
        User user = as.login(email,password);
        
        
        if (user ==null){
            session.setAttribute("message","Invalid login!");            
        }else if (!user.getActive()){
            session.setAttribute("message","User is not active. Please contact the admin!");
        }else if(user.getRole().getRoleId() == 1 || user.getRole().getRoleId() == 3){
            session.setAttribute("email", email);  
            response.sendRedirect("admin");
            return;
        }else{
            session.setAttribute("email", email);  
            response.sendRedirect("inventory");
            return;
        }        
                      
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        return;

    }
}
