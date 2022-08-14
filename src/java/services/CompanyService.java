package services;

import dataaccess.CompanyDB;
import java.util.List;
import models.Company;

/**
 *
 * @author Diego Maia
 */
public class CompanyService {
    
    public List<Company> getAll() throws Exception {
        CompanyDB companyDB = new CompanyDB();
        List<Company> companies = companyDB.getAll();
        return companies;
    }
    
    public Company get(int companyID) throws Exception {
        CompanyDB companyDB = new CompanyDB();
        Company company = companyDB.get(companyID);
        
        return company;
    }
    
    public Company get(String companyName) throws Exception {
        CompanyDB companyDB = new CompanyDB();
        Company company = companyDB.get(companyName);
        
        return company;
    }
    
}
