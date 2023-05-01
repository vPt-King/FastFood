package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import FastFood.DB.DBconnect;
import FastFood.Entity.Category;

public class CategoryDAO {
	public Category FindCategoryById(int id)
	{
		Category category = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from category where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				category = new Category(rs.getInt("id"),rs.getString("name"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
		
	}
	public List<Category> FindAllCategroy()
	{
		Connection a = DBconnect.getJDBCConnection();
		List<Category> list = new ArrayList<>();
		String q = "select * from category";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Category(rs.getInt("id"),rs.getString("name")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("err in find all category");
			e.printStackTrace();
		}
		return list;
	}
	public int InsertNewCategory(Category category)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into category(name) values(?)";
		int k = 1;
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, category.getName());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("err in insert new category");
			e.printStackTrace();
		}
		return k;
	}
	
	public int CheckIfCategoryIsExist(Category category)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from category where name = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,category.getName());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				k = 1;
			}
			else {
				k = 0;
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public int EditCategory(Category category)
	{
		int check = CheckIfCategoryIsExist(category);
		if(check == 1)
		{
			return 0;
		}
		else {
			Connection a = DBconnect.getJDBCConnection();
			String q = "update category set name = ? where id = ?";
			try {
				PreparedStatement ps = a.prepareStatement(q);
				ps.setString(1, category.getName());
				ps.setInt(2, category.getId());
				int k = ps.executeUpdate();
				ps.close();
				a.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}
	
	public int DeleteCategory(Category category)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from category where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, category.getId());
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
