package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import FastFood.DB.DBconnect;
import FastFood.Entity.Team_details;

public class Team_detailsDAO {
	public void InsertTeamDetails(String name, String picture)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into team_details(name,picture) values(?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, name);
			ps.setString(2, picture);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Team_details> GetAllTeam_detailsMember()
	{
		List<Team_details> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from team_details";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Team_details(rs.getInt("id"),rs.getString("name"),rs.getString("picture")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			System.err.println("err in get all team_details");
			e.printStackTrace();
		}
		return list;
	}
	
	public void DeleteTeam_detailsById(int id)
	{
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from team_details where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1,id);
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
