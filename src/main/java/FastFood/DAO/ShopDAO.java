package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import FastFood.DB.DBconnect;
import FastFood.Entity.Shop;
import FastFood.Entity.User;

public class ShopDAO {
	public Shop GetShop()
	{
		Shop shop = null;
		
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from shop where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				shop = new Shop(rs.getInt("id"),rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("gmail"),rs.getInt("tax"),rs.getDouble("shipping"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}
}
