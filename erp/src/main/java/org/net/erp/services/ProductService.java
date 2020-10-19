package org.net.erp.services;
import java.util.List;

import org.net.erp.model.Product;
 
public interface ProductService {

	void save(Product product);
    
    List<Product> listAll();
    
    void deleteProduct(int id);
    
    Product getProductById(int id);
     
    int checkProductEntries(int id);
 
}
