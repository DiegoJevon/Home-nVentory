package services;

import dataaccess.ItemsDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;



/**
 *
 * @author Diego Maia
 */
public class InventoryService {

    
    public InventoryService() {

    }



    public List<Item> getAll(String email) throws Exception {
        ItemsDB itemDB = new ItemsDB();
        List<Item> items = itemDB.getAll(email);
        return items;
    }
    
    public List<Item> getAll() throws Exception {
        ItemsDB itemDB = new ItemsDB();
        List<Item> items = itemDB.getAll();
        return items;
    }
    public Item get(int itemID) throws Exception{
        ItemsDB itemDB = new ItemsDB();
        Item item = itemDB.get(itemID);
        return item;
    }
    
    public void insert (User owner, Integer itemID, String itemName, double price, Category category) throws Exception{
        ItemsDB itemDB   = new ItemsDB();
        Item item = new Item();
        item.setItemId(itemID);
        item.setItemName(itemName);
        item.setPrice(price);
        item.setOwner(owner);
        item.setCategory(category);
        itemDB.insert(item);        
        
    }
    
    public void delete (int itemID) throws Exception{
        ItemsDB itemDB = new ItemsDB();
        Item item = itemDB.get(itemID);
        itemDB.delete(item);

    }
    
    public void update (User owner, Integer itemID, String itemName, double price, Category category) throws Exception{
        ItemsDB itemDB = new ItemsDB();
        Item item = new Item();
        item.setItemId(itemID);
        item.setItemName(itemName);
        item.setPrice(price);
        item.setOwner(owner);
        item.setCategory(category);
        itemDB.update(item);

    }
    
   
}
