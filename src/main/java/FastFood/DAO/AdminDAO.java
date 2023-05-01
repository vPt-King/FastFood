package FastFood.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FastFood.DB.DBconnect;
import FastFood.Entity.Admin;

public class AdminDAO {
	public Admin GetAdminAccount(Admin adminInp)
	{
		Admin admin = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from admin where gmail = ? and password = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,adminInp.getGmail());
			ps.setString(2, adminInp.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				admin = new Admin(rs.getInt("id"),rs.getString("gmail"),rs.getString("name"),rs.getString("password"),rs.getDate("dateOfBirth"),rs.getString("address"),rs.getString("phone"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("loi o get admin account");
			e.printStackTrace();
		}
		return admin;
	}
	
	public int InsertNewAdmin(Admin admin)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into admin(gmail,name,password,dateOfBirth,address,phone) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,admin.getGmail());
			ps.setString(2,admin.getName());
			ps.setString(3, admin.getPassword());
			java.sql.Date sqlDate = new java.sql.Date(admin.getDateOfBirth().getTime());
			ps.setDate(4, sqlDate);
			ps.setString(5, admin.getAddress());
			ps.setString(6, admin.getPhone());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
			return k;
		} catch (SQLException e) {
			System.err.println("loi insert new admin");
			e.printStackTrace();
			return 0;
		}
	}
	
	public int CheckIfAdminIsExist(Admin admin)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from admin where gmail = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,admin.getGmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				rs.close();
				ps.close();
				a.close();
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
