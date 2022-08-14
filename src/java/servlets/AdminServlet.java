
package servlets;

import dataaccess.CategoryDB;
import dataaccess.UsersDB;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Company;
import models.Item;
import models.Role;
import models.User;
import services.AccountService;
import services.CategoryService;
import services.CompanyService;
import services.InventoryService;
import services.RoleService;


/**
 *
 * @author Diego Maia
 */
public class AdminServlet extends HttpServlet {

       @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
       
       if (request.getParameter("manage_users") != null){
           session.setAttribute("page", "manage_users");
       }else if (request.getParameter("manage_categories") != null){
           session.setAttribute("page", "manage_categories");
       }else if (request.getParameter("check_items") != null){
           session.setAttribute("page", "check_items");
       }else if (request.getParameter("report") != null){
           session.setAttribute("page", "report");
       }
                
        AccountService as = new AccountService();        
        List<User> users= null;
        RoleService rs = new RoleService();
        List<Role> roles = null;
        CategoryService cs = new CategoryService();
        List<Category> categories = null;
        CompanyService compServ = new CompanyService();
        List<Company> companies = null;
        try {
            if(session.getAttribute("message_click")==null){
            request.setAttribute("message_click", "Add User");
            request.setAttribute("check",false);
            }
            if(session.getAttribute("message_click_category")==null){
            request.setAttribute("message_click_category", "Add Category");
            }
            
            
            UsersDB userDB = new UsersDB();
            String email = (String) session.getAttribute("email");
            User userLogin = userDB.get(email);
            
            if (userLogin.getCompany().getCompanyId() == 2 || userLogin.getCompany().getCompanyId() == 3){
            users = as.getAll(userLogin.getCompany());
            } else{
               users = as.getAll(); 
            }            
            request.setAttribute("users", users);
            roles = rs.getAll();
            request.setAttribute("roles", roles);
            categories=cs.getAll();
            request.setAttribute("categories", categories);
            companies=compServ.getAll();
            request.setAttribute("companies", companies);
            
            InventoryService is = new InventoryService();
            List<Item> items= is.getAll();
            request.setAttribute("items", items);
            request.setAttribute("userLogin", userLogin);
            
            
            

            
            String action = request.getParameter("action");
            User user = null;
            Category category = null;

               if (action != null){
                OutputStream o=null;
                List<String> output=null;                   
                    switch(action){
                    case "edit":
                        request.setAttribute("message_click", "Edit User");
                        request.setAttribute("check",true);
                        String usernameEdit = request.getParameter("email_edit");
                        user = as.get(usernameEdit);
                        request.setAttribute("user",user);
                        break;
                    
                    case "cat_edit":
                        request.setAttribute("message_click_category", "Edit User");
                        request.setAttribute("check_category",true);
                        int categoryID = Integer.parseInt(request.getParameter("category_edit"));
                        category = cs.get(categoryID);
                        request.setAttribute("category",category);
                        break;
                        
                    case "user_list":                    
                    output = new ArrayList<String>();                    
                    String line="";
                                                            
                    response.setContentType("user_report.xls");
                    response.setHeader("Content-Disposition", "attachment; filename=user_report.xls");                    
                    o = response.getOutputStream();
                                      
                        for (User userList: users){                            
                            String fullName = userList.getLastName()+", "+userList.getFirstName();
                            items = is.getAll(userList.getEmail());
                            line = String.format("%s;%s\n",fullName,String.valueOf(items.size()));
                            output.add(line);
                        }
                        Collections.sort(output);
                        String head = String.format("%s;%s\n","Name","Number of Items");
                        output.add(0,head);
                        
                        for(int i = 0; i<output.size(); i++){
                            o.write(output.get(i).getBytes());
                        }
                        o.flush();
                        o.close(); 
                        
                        break;
                        
                    case "user_inventory":
                    output = new ArrayList<String>();                    
                    String lineInventory="";

                    response.setContentType("inventory_report.xls");
                    response.setHeader("Content-Disposition", "attachment; filename=inventory_report.xls");                    
                    o = response.getOutputStream();
                    
                        for (User userList: users){
                            double price=0;                            
                            String fullName = userList.getLastName()+", "+userList.getFirstName();
                            items = is.getAll(userList.getEmail());
                            for (Item itemUser : items){
                                price +=itemUser.getPrice();
                            }
                            lineInventory = String.format("%s;%s;%s\n",fullName,String.valueOf(items.size()),String.valueOf(price));
                            output.add(lineInventory);
                        }
                        Collections.sort(output);
                        String header = String.format("%s;%s;%s\n","Name","Number of Items","Total Price");
                        output.add(0,header);

                        for(int i = 0; i<output.size(); i++){
                            o.write(output.get(i).getBytes());
                        }
                        o.flush();
                        o.close();                      
                    }
                     
                }
           } catch (Exception ex) {
               Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
              
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request,response);
        return;
               
    
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
               
        String action = request.getParameter("action");
        String loggedEmail = (String) session.getAttribute("email");

        AccountService as = new AccountService();
        RoleService rs = new RoleService();
        CompanyService compServ = new CompanyService();
        UsersDB userDB = new UsersDB();
        InventoryService is = new InventoryService();
        
        List<User> users = null;
        List<Item> items = null;
        
        
        Category category;
        CategoryService cs = new CategoryService();
           try {
                String password = request.getParameter("password_save");
                    String email = request.getParameter("email_save");
                    String firstName = request.getParameter("first_name_save");
                    String lastName = request.getParameter("last_name_save");
                    boolean active = request.getParameter("active_save") != null;
                    String roleSave = request.getParameter("role_save");
                    String companySave = request.getParameter("company_save");
                    Company company = compServ.get(companySave);
                    Role role = rs.get(roleSave);
                    
                    User userLogin = userDB.get(loggedEmail);
                    
                    if (userLogin.getCompany().getCompanyId() == 2 || userLogin.getCompany().getCompanyId() == 3){
                    users = as.getAll(userLogin.getCompany());
                    } else{
                       users = as.getAll(); 
                    } 
                OutputStream o=null;
                List<String> output=null;
                switch(action){
                case "insert":
                   if (userDB.get(email) != null){
                        session.setAttribute("save_message", "Email is already been used!");
                        break;
                    }
                    as.insert(email, active, firstName, lastName, password, role, company);
                    session.setAttribute("save_message", "User has been created!");
                                   
                   break;
                case "update":
                    as.update(email, active, firstName, lastName, password, role, company);
                    session.setAttribute("message_click",null);
                    session.setAttribute("save_message", "User has been updated!");
                                      
                   break;   
                case "delete":                    
                    String emailDelete = request.getParameter("email_delete");
                    if (as.get(emailDelete).getEmail().equals(loggedEmail)){
                        session.setAttribute("save_message", "Not allowed delete this user.");
                        break;
                    }
                    as.delete(emailDelete);
                    session.setAttribute("message_click",null);
                    session.setAttribute("save_message", "User has been deleted!");
                    break;
                    
                case "save_category":                    
                    String categorySave = request.getParameter("category_save");
                    int categoryID = 0;
                    try{
                        categoryID = Integer.parseInt(request.getParameter("categoryID_save"));
                    }catch (Exception e){
                        categoryID = 0;
                    }                    
                    if(categoryID==0){
                        CategoryDB categoryDB = new CategoryDB();
                        if(categoryDB.get(categorySave)!=null){
                            session.setAttribute("save_message_category", "Category has already exist!");
                            break;
                        }
                        category = new Category(categoryID,categorySave);
                        cs.insert(category);
                        session.setAttribute("save_message_category", "Category has been created!");
                    }else{
                        category = new Category(categoryID,categorySave);                        
                        cs.update(category);
                    }
                   session.setAttribute("message_click_category",null);
                   session.setAttribute("save_message_category", "Category has been updated!");
                   break;   
                }
           } catch (Exception ex) {
               Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           response.sendRedirect("admin");
           return;
    }

}
