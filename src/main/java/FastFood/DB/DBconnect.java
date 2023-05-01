package FastFood.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnect {
	public static Connection getJDBCConnection()
    {
        String url = "jdbc:mysql://localhost:3306/fastfood";
        String user = "root";
        String password = "conbo2chan";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url, user,password);
        } catch (ClassNotFoundException ex) {
        	System.err.println("Loi o DB connect 1");
            //Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
        	System.err.println("Loi o DB connect 2");
           // Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//    public static void main(String[] args) throws SQLException
//    {
//    	Connection a = getJDBCConnection();
//    	String query = "select * from admin_cred";
//    	PreparedStatement ps = a.prepareStatement(query);
//    	ResultSet rs = ps.executeQuery();
//    	if(rs.next())
//    	{
//    		System.out.println("ok");
//    	}
//    }
}
