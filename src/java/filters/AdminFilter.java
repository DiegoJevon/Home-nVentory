package filters;

import dataaccess.UsersDB;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Diego Maia
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String email = (String)session.getAttribute("email");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
         if (email == null){
            httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("login");
            return;  
            }       
        
        UsersDB userDB = new UsersDB();
        User user;
        try {
            user = userDB.get(email);
            int roleID = user.getRole().getRoleId();            
            
            if(roleID != 1 && roleID != 3){
            httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("inventory");
            return;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }        
          
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
