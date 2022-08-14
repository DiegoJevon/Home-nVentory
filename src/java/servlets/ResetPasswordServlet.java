package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;

/**
 *
 * @author Diego Maia
 */
public class ResetPasswordServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean uuid = request.getParameter("uuid") !=null;
        AccountService as = new AccountService();
            
        if (uuid){
            try{
                String uuidWeb = request.getParameter("uuid");
                User user = as.getByUUID(uuidWeb);
                if (user != null){
                    getServletContext().getRequestDispatcher("/WEB-INF/resetpassword.jsp").forward(request, response);
                    return;
                }
            } catch (Exception e){
                response.sendRedirect("login");
                return;
            }    
                       
        }                
        getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        String email = request.getParameter("email");
        
        
        // save email to a cookie
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(cookie);
        
        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        String action = request.getParameter("action");
        
        switch(action){
            case "email":
                as.resetPassword(email, path, url);
        
                request.setAttribute("uuid", true);
                response.sendRedirect("login");
                
                return;
            case "reset":             
        
                try {
                    String password = request.getParameter("password");
                    String uuidEmail = request.getParameter("uuid");
                    User user = as.getByUUID(uuidEmail);
                    user.setPassword(password);
                    user.setUuid(null);
                    as.update(user);
                    request.setAttribute("message", "Password has been changed!");
                    response.sendRedirect("login");
                    return;
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }                
        }         
            as.resetPassword(email, path, url);

            request.setAttribute("uuid", true);
            response.sendRedirect("login");
            return;
    }
}
