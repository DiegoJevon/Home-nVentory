
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Company;
import models.Role;
import models.User;

/**
 *
 * @author Diego Maia
 */
public class UsersDB {
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        }finally{
            em.close();
        }
    }
    
    public List<User> getAll(Company company) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<User> users = em.createNamedQuery("User.findAllByCompany",  User.class).setParameter("company", company).getResultList();
            return users;
        }finally{
            em.close();
        }
    }

    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        }finally{
            em.close();
        }
    }
    
    public User getByUUID(String uuid) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.createNamedQuery("User.findByUuid",  User.class).setParameter("uuid", uuid).getSingleResult();
            return user;
        }finally{
            em.close();
        }
    }

    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
            et.begin();
            em.persist(user);
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
            Role role = user.getRole();
            role.getUserList().remove(user);
            em.merge(role);
            Company company = user.getCompany();
            company.getUserList().remove(user);
            em.merge(company);
            et.begin();
            em.merge(user);
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }

    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{        
            et.begin();
            em.remove(em.merge(user));
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }
        
    
}