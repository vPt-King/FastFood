package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FastFood.DB.DBconnect;
import FastFood.Entity.Contact_details;


public class Contact_detailsDAO {
	public Contact_details GetContact_detailsById(int id)
	{
		Contact_details contact_details = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from contact_details where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				contact_details = new Contact_details(rs.getInt("id"),rs.getString("address"),rs.getString("gmap"),rs.getString("pn1"),rs.getString("pn2"),rs.getString("email"),rs.getString("fb"),rs.getString("insta"),rs.getString("tw"),rs.getString("iframe"));
			}
			a.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error in GetSettingsById");
		}
		return contact_details;
	}
	
	public void UpdateContact_details(Contact_details recontact_details)
	{
		Contact_details contact_details = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update contact_details set address=?,gmap=?,pn1=?,pn2=?,email=?,fb=?,insta=?,tw=?,iframe=? where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, recontact_details.getAddress());
			ps.setString(2, recontact_details.getGmap());
			ps.setString(3, recontact_details.getPn1());
			ps.setString(4, recontact_details.getPn2());
			ps.setString(5, recontact_details.getEmail());
			ps.setString(6, recontact_details.getFb());
			ps.setString(7, recontact_details.getInsta());
			ps.setString(8, recontact_details.getTw());
			ps.setString(9, recontact_details.getIframe());
			ps.setInt(10, recontact_details.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("error in update contact_details");
			e.printStackTrace();
		}
		
	}
}
