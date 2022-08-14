package servlets;

import dataaccess.CategoryDB;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.CategoryService;
import services.InventoryService;

/**
 *
 * @author Diego Maia
 */
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           HttpSession session = request.getSession();
        
            String email = (String) session.getAttribute("email");                
            
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        User user = null;   
        List<Item> items= null;
        CategoryService cs = new CategoryService();
        List<Category> categories = null;
        
        
        
        try {
            if(session.getAttribute("message_item_click")==null){
            request.setAttribute("message_item_click", "Add Item");
            request.setAttribute("check",false);
            }
            user = as.get(email);
            request.setAttribute("firstName",user.getFirstName());
            request.setAttribute("lastName",user.getLastName());
            
            items = user.getItemList();
            request.setAttribute("items", items);
            categories = cs.getAll();
            request.setAttribute("categories",categories);
            
            String action = request.getParameter("action");
            Item item = null;

               if (action != null){
                    switch(action){
                    case "edit":
                        request.setAttribute("message_item_click", "Edit Item");
                        request.setAttribute("check",true);
                        String itemEdit = request.getParameter("item_edit");
                        int itemEditID = Integer.parseInt(itemEdit);
                        item = is.get(itemEditID);
                        request.setAttribute("item",item);
                        break;
                       
                    }
                }
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }                
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request,response);
        return;
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
         String action = request.getParameter("action");        
        
        AccountService as = new AccountService();
        User user = null;
        CategoryDB categoryDB = new CategoryDB();
        Category category = null;
        request.setAttribute("message_added", "");
        InventoryService is = new InventoryService();
        int itemID=0;
        
        try {
             user = as.get(email);
            switch(action){
                case "insert":
                    String categorySelected = request.getParameter("selected_category");
                    int categoryID = Integer.parseInt(categorySelected);
                    String itemName = request.getParameter("item");
                    double price = Double.parseDouble(request.getParameter("price"));                   
                    category = categoryDB.get(categoryID);
                    
                
                    try {
                        try{
                            itemID = Integer.parseInt(request.getParameter("item_id"));
                        }catch (Exception e){
                            itemID = 0;
                        }
                                                
                        if(is.get(itemID)!=null){
                            
                            session.setAttribute("message_item", "Item has been edited!");
                            is.update(user, itemID, itemName, price, category);
                            break;                            
                        }else{
                            itemID=0;
                            session.setAttribute("message_item", "Item has been added!");
                            is.insert(user, itemID, itemName, price, category);
                        }                        
                    } catch (Exception ex) {
                        Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    break;
                    
                case "delete":
                    String item = request.getParameter("item_delete");
                    int selectedItemID = Integer.parseInt(item);                    
                    Item selectedItem = is.get(selectedItemID);
                    if (selectedItem.getOwner().getEmail().equals(user.getEmail())){
                        is.delete(selectedItemID);
                        session.setAttribute("message_item", "Item has been deleted!"); 
                    }else{
                        session.setAttribute("message_item", "User not allowed to delete this item!");
                    }                    
                     break;
            }                        
                       
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        response.sendRedirect("inventory");
        return;
        
        
        
    }
}
