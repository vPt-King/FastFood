package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;

import FastFood.DB.DBconnect;
import FastFood.Entity.User_queries;

public class User_queriesDAO {
	public int InsertNewUser_queries(User_queries user_queries)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into user_queries(name,email,subject,message,date,seen) values(?,?,?,?,?,?)";
		try {
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String formattedDate = dateFormat.format(currentDate);
			java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate.replace("/", "-"));
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, user_queries.getName());
			ps.setString(2, user_queries.getEmail());
			ps.setString(3,user_queries.getSubject());
			ps.setString(4, user_queries.getMessage());
			ps.setDate(5, sqlDate);
			ps.setInt(6,user_queries.getSeen());
			k = ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println("err in InsertNewUser_queries");
			e.printStackTrace();
		}
		return k;
	}
	
	public List<User_queries> GetAllUser_queries()
	{
		Connection a = DBconnect.getJDBCConnection();
		List<User_queries> list = new ArrayList<>();
		String q = "select * from user_queries order by id asc";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			while(rs.next())
			{
				java.sql.Date date = rs.getDate("date");
				String dateString = dateFormat.format(date);
				User_queries uq = new User_queries(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("subject"),rs.getString("message"),dateString,rs.getInt("seen"));
				list.add(uq);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int ChangeSeenStatus(User_queries uq)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update user_queries set seen = 1 where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, uq.getId());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return k;
	}
	public int DeleteUser_queries(User_queries uq)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from user_queries where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, uq.getId());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public int ChangeAllSeenStatus()
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "UPDATE user_queries as a, user_queries as b SET a.seen = 1 where a.id = b.id;";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	
	public int DeleteAllUser_queries()
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from user_queries";
		try {
			PreparedStatement ps = a.prepareStatement(q);
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
