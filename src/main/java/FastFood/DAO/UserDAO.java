package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import FastFood.DB.DBconnect;
import FastFood.Entity.Product;
import FastFood.Entity.User;

public class UserDAO {
	public int InsertNewUser(User user)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into user(name,created_at,gmail,password,phone,address) values(?,?,?,?,?,?)";
		try {
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String formattedDate = dateFormat.format(currentDate);
			java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate.replace("/", "-"));
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, user.getName());
			ps.setDate(2, sqlDate);
			ps.setString(3, user.getGmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getAddress());
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public List<User> GetAllUser()
	{
		List<User> user = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from user";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			while(rs.next())
			{
				java.sql.Date date = rs.getDate("created_at");
				String dateString = dateFormat.format(date);
				user.add(new User(rs.getInt("id"),rs.getString("name"),dateString,rs.getString("gmail"),rs.getString("password"),rs.getString("phone"),rs.getString("address")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public User GetUserDetail(User user)
	{
		User u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from user where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			if(rs.next())
			{
				java.sql.Date date = rs.getDate("created_at");
				String dateString = dateFormat.format(date);
				u = new User(rs.getInt("id"),rs.getString("name"),dateString,rs.getString("gmail"),rs.getString("password"),rs.getString("phone"),rs.getString("address"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	public User FindUserById(int id)
	{
		User u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from user where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			if(rs.next())
			{
				java.sql.Date date = rs.getDate("created_at");
				String dateString = dateFormat.format(date);
				u = new User(rs.getInt("id"),rs.getString("name"),dateString,rs.getString("gmail"),rs.getString("password"),rs.getString("phone"),rs.getString("address"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public int UpdateUser(User user)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update user set name = ?, password=? , phone=?,address=? where id = ?";
		try {
			System.out.println(user.getName());
			System.out.println(user.getPassword());
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3,user.getPhone());
			ps.setString(4, user.getAddress());
			ps.setInt(5, user.getId());
			k = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return k;
	}
	
	public int DeleteUserById(User user)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from user where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, user.getId());
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
