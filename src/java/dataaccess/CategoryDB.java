package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Category;

/**
 *
 * @author Diego Maia
 */
public class CategoryDB {
    
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        }finally{
            em.close();
        }
    }
    
    public Category get(int categoryID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Category user = em.find(Category.class, categoryID);
            return user;
        }finally{
            em.close();
        }
       
    } 
    public Category get(String categoryName) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();            
            Category category = null;
            
            for (Category categoryFind:categories){
                if(categoryFind.getCategoryName().equals(categoryName)){
                    category = categoryFind;
                }
            }            
            return category;
        }finally{
            em.close();
        }
    }
    
}
