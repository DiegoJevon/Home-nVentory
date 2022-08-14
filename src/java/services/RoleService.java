package services;

import dataaccess.RolesDB;
import java.util.List;
import models.Role;

/**
 *
 * @author Diego Maia
 */
public class RoleService {
    
    public List<Role> getAll() throws Exception {
        RolesDB roleDB = new RolesDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    public Role get(int roleID) throws Exception {
        RolesDB roleDB = new RolesDB();
        Role role = roleDB.get(roleID);
        
        return role;
    }
    
    public Role get(String roleName) throws Exception {
        RolesDB roleDB = new RolesDB();
        Role role = roleDB.get(roleName);
        
        return role;
    }
    
}
