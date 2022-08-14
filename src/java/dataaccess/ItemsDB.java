package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Company;
import models.Item;
import models.User;

/**
 *
 * @author Diego Maia
 */
public class ItemsDB {
    
     public Item get(int itemID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Item item = em.find(Item.class, itemID);
            return item;
        }finally{
            em.close();
        }
    }
     
     public List<Item> getAll(String owner) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, owner);
            List<Item> items = user.getItemList();
            return items;
        }finally{
            em.close();
        }
    }
      public List<Item> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Item> items = em.createNamedQuery("Item.findAll",  Item.class).getResultList();
            return items;
        }finally{
            em.close();
        }
    }
     
     
     
     public void insert(Item item) throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
           User user = item.getOwner();
          user.getItemList().add(item);
          et.begin();
          em.persist(item);
          em.merge(user);
          et.commit();  
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }
     
     public void delete(Item item) throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
            User user = item.getOwner();
            user.getItemList().remove(item);
            em.merge(user);
            Category category = item.getCategory();
            category.getItemList().remove(item);
            em.merge(category);
            et.begin();
            em.remove(em.merge(item));
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
     }
     
     public void update(Item item) throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{           
            Category category = item.getCategory();
            category.getItemList().remove(item);
            em.merge(category);
            et.begin();
            em.merge(item);
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
     }
    
}
