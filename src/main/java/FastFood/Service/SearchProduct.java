package FastFood.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import FastFood.DAO.CategoryDAO;
import FastFood.DAO.ProductDAO;
import FastFood.Entity.Category;
import FastFood.Entity.Product;

public class SearchProduct {
	public Product SearchProductById(int product_id)
	{
		ProductDAO pDAO = new ProductDAO();
		return pDAO.FindProductById(product_id);
	}
	public List<Product> SearchProductByAString(String string)
	{
		ProductDAO pDAO = new ProductDAO();
		List<Product> ListProduct = pDAO.FindAllProduct();
		List<Product> res = new ArrayList<>();
		for(Product p : ListProduct)
		{
			if(p.getName().toLowerCase().contains(string.toLowerCase()))
			{
				res.add(p);
			}
		}
		return res;
	}
	
	public List<Product> SearchProductByCategory(String Category_name)
	{
		ProductDAO pDAO = new ProductDAO();
		List<Product> ListProduct = pDAO.FindAllProduct();
		CategoryDAO cDAO = new CategoryDAO();
		List<Category> ListCategory = cDAO.FindAllCategroy();
		List<Product> res = new ArrayList<>();
		int category_id = -1;
		for(Category c : ListCategory)
		{
			if(c.getName().equalsIgnoreCase(Category_name))
			{
				category_id = c.getId();
				break;
			}
		}
		
		for(Product p : ListProduct)
		{
			if(p.getCategory_id() == category_id)
			{
				res.add(p);
			}
		}
		return res;
	}
	public List<Product> findClosestProducts(double targetPrice) {
		ProductDAO pDAO = new ProductDAO();
        List<Product> products = pDAO.FindAllProduct();
        Collections.sort(products, new Comparator<Product>() {
            public int compare(Product p1, Product p2) {
                double difference1 = Math.abs(p1.getPrice() - targetPrice);
                double difference2 = Math.abs(p2.getPrice() - targetPrice);
                return Double.compare(difference1, difference2);
            }
        });
        return products;
    } 
}
