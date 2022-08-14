package services;
import dataaccess.UsersDB;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Company;
import models.Role;
import models.User;

/**
 *
 * @author Diego Maia
 */
public class AccountService {      
    
    public AccountService() {        
    }
        
    public User login(String email, String password) {
        UsersDB userDB = new UsersDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public User get(String email){
        UsersDB userDB = new UsersDB();
        User user = null;
        try {
            user = userDB.get(email);
            
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;       
        
    }
    public User getByUUID(String uuid){
        UsersDB userDB = new UsersDB();
        User user = null;
        try {
            user = userDB.getByUUID(uuid);
            
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;       
        
    }
    
      public List<User> getAll() throws Exception {
        UsersDB userDB = new UsersDB();
        List<User> users = userDB.getAll();
        return users;
    }
      
      public List<User> getAll(Company company) throws Exception {
        UsersDB userDB = new UsersDB();
        List<User> users = userDB.getAll(company);
        return users;
    }
      
      public void insert (String email, boolean active, String firstName, String lastName, String password, Role role, Company company) throws Exception{
          UsersDB userDB = new UsersDB();
          User user = new User();
          user.setEmail(email);
          user.setPassword(password);          
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setActive(active);
          user.setRole(role);
          user.setCompany(company);
          userDB.insert(user);
          
      }
          public void insert (User user) throws Exception{
          UsersDB userDB = new UsersDB();

          userDB.insert(user);
          
      }
            
        public void update (User user) throws Exception{
          UsersDB userDB = new UsersDB();

          userDB.update(user);
          
      }
            
      public void update (String email, boolean active, String firstName, String lastName, String password, Role role, Company company) throws Exception{
          UsersDB userDB = new UsersDB();
          User user = new User();
          user.setEmail(email);
          user.setPassword(password);          
          user.setFirstName(firstName);
          user.setLastName(lastName);
          user.setActive(active);
          user.setRole(role);
          user.setCompany(company);
          userDB.update(user);
          
      }
      
      
      public void delete(String email) throws Exception {
        UsersDB userDB = new UsersDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
      
      public User resetPassword(String email, String path, String url){
        try {
            UsersDB userDB = new UsersDB();
            String uuid = UUID.randomUUID().toString();
            String link = url + "?uuid=" + uuid;
            
            User user = userDB.get(email);
            user.setUuid(uuid);
            userDB.update(user);
                       
            String to = user.getEmail();
            String subject = "HOME nVentory Reset Password";
            String template = path + "/emailtemplates/resetpassword.html";
            
            
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", link);
            
            GmailService.sendMail(to, subject, template, tags);
            
            return user;
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean changePassword(String uuid, String password) {
       UsersDB userDB = new UsersDB();
        try {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public User newAccount(String email, String path, String url){
        try {
            UsersDB userDB = new UsersDB();
            String uuid = UUID.randomUUID().toString();
            String link = url + "?uuid=" + uuid;
            
            User user = userDB.get(email);
            user.setUuid(uuid);
            userDB.update(user);
                       
            String to = user.getEmail();
            String subject = "HOME nVentory New Account";
            String template = path + "/emailtemplates/activateaccount.html";
            
            
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());
            tags.put("link", link);
            
            GmailService.sendMail(to, subject, template, tags);
            
            return user;
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public User welcome(String email, String path){
        try {
            UsersDB userDB = new UsersDB();            
            User user = userDB.get(email);
                       
            String to = user.getEmail();
            String subject = "HOME nVentory New Account";
            String template = path + "/emailtemplates/welcome.html";
            
            
            HashMap<String, String> tags = new HashMap<>();
            tags.put("firstname", user.getFirstName());
            tags.put("lastname", user.getLastName());

            
            GmailService.sendMail(to, subject, template, tags);
            
            return user;
        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
      
}