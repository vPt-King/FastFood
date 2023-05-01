package FastFood.DAO;

import FastFood.DB.DBconnect;
import FastFood.Entity.Product;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class ProductDAO {
	public List<Product> FindAllProduct()
	{
		List<Product> list = new ArrayList();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from product";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Product(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getDouble("price"),rs.getInt("category_id"),rs.getString("image")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public int InsertNewProduct(Product p)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into product(name,description,quantity,price,category_id,image) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, p.getName());
			ps.setString(2, p.getDescription());
			ps.setInt(3, p.getQuantity());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getCategory_id());
			ps.setString(6, p.getImage());
		    k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public int CheckIfProductIsExist(Product p)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from product where name = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, p.getName());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				rs.close();ps.close();a.close();
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public Product FindProductDetail(Product p)
	{
		Product pro = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from product where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, p.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				pro = new Product(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getInt("quantity"),rs.getDouble("price"),rs.getInt("category_id"),rs.getString("image"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pro;
	}
	
	public int EditProductById(Product p)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update product set name = ?, description = ?, quantity = ?, price = ?, category_id = ?,image = ? where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, p.getName());
			ps.setString(2, p.getDescription());
			ps.setInt(3, p.getQuantity());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getCategory_id());
			ps.setString(6, p.getImage());
			ps.setInt(7, p.getId());
		    k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public int DeleteProductById(Product p)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from product where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, p.getId());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
}
