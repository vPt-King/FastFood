package FastFood.Service;
import FastFood.Entity.*;
import FastFood.DAO.ProductDAO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class SortProduct {

	public List<Product> SortTheLowToMaxPrice() {
		ProductDAO pDAO = new ProductDAO();
        List<Product> products = pDAO.FindAllProduct ();
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        return products;
    } 
	public List<Product> SortTheMaxToLowPrice() {
		ProductDAO pDAO = new ProductDAO();
        List<Product> products = pDAO.FindAllProduct ();
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        return products;
    } 
}
