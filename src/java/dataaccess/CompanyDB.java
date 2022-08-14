package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Company;

/**
 *
 * @author Diego Maia
 */
public class CompanyDB {
    public List<Company> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Company> companies = em.createNamedQuery("Role.findAll", Company.class).getResultList();
            return companies;
        }finally{
            em.close();
        }
    }
    
    public Company get(int companyID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Company company = em.find(Company.class, companyID);
            return company;
        }finally{
            em.close();
        }        
    }
    
      public Company get(String companyName) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Company> companies = em.createNamedQuery("Company.findAll", Company.class).getResultList();            
            Company company = null;
            
            for (Company companyFind:companies){
                if(companyFind.getCompanyName().equals(companyName)){
                    company = companyFind;
                }
            }            
            return company;
        }finally{
            em.close();
        }
    }
}
