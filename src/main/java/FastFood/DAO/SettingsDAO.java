package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import FastFood.DB.DBconnect;
import FastFood.Entity.Settings;

public class SettingsDAO {
	public Settings GetSettingsById(int id)
	{
		Settings settings = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from settings where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				settings = new Settings(rs.getInt("id"),rs.getString("site_title"),rs.getString("site_about"),rs.getInt("shutdown"));
			}
			a.close();
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error in GetSettingsById");
		}
		return settings;
	}
	
	public void UpdateSettings(Settings resettings)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "update settings set site_title = ?, site_about = ?, shutdown = ? where id = ?";
		try {
			System.out.println("site_title: " + resettings.getShutdown());
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1,resettings.getSite_title());
			ps.setString(2,resettings.getSite_about());
			ps.setInt(3, resettings.getShutdown());
			ps.setInt(4, resettings.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("update settings error");
			e.printStackTrace();
		}
	}
}
