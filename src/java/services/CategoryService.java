
package services;

import dataaccess.CategoryDB;
import dataaccess.DBUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;

/**
 *
 * @author Diego Maia
 */
public class CategoryService {
    
     public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categories = categoryDB.getAll();
        return categories;
    }
     
     public Category get(int  categoryID){
        CategoryDB categoryDB = new CategoryDB();
        Category category = null;   
        category = categoryDB.get(categoryID);        
        return category;    
        
    }
     
     public Category get(String  categoryName){
        CategoryDB categoryDB = new CategoryDB();
        Category category = null;   
         try {        
             category = categoryDB.get(categoryName);
         } catch (Exception ex) {
             Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
         }
        return category;    
        
    }
     public void insert(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
            et.begin();
            em.persist(category);
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }
     
     public void update(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        try{
            et.begin();
            em.merge(category);
            et.commit();
            
        }catch (Exception e){
            et.rollback();
        }finally{
            em.close();
        }
    }
    
}
