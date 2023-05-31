package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import FastFood.DB.DBconnect;
import FastFood.Entity.Carousel;

public class CarouselDAO {
	public int InsertNewCarousel(Carousel carousel)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q= "insert into carousel(image) values(?)";
		int k = 1;
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, carousel.getImage());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public List<Carousel> FindAllCarousel()
	{
		List<Carousel> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from carousel";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Carousel(rs.getInt("id"),rs.getString("image")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void DeleteCarouselById(Carousel carousel)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from carousel where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, carousel.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
