package FastFood.DAO;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import FastFood.DB.DBconnect;
import FastFood.Entity.Order_Product;

public class Order_ProductDAO {
	public List<Order_Product> GetListOrder_ProductByOrderId(int order_id)
	{
		List<Order_Product> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from order_product where order_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, order_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				list.add(new Order_Product(rs.getInt("id"),rs.getInt("product_id"),rs.getInt("order_id"),rs.getInt("quantity"),rs.getDouble("sub_total"),rs.getString("description"),rs.getString("product_name")));
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

	public int InsertNewOP(int id, int orderId, int sl, Double subtotal, String description, String name) {
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into order_product(product_id,order_id,quantity,sub_total,description,product_name) values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ps.setInt(2, orderId);
			ps.setInt(3, sl);
			ps.setDouble(4, subtotal);
			ps.setString(5,description);
			ps.setString(6,name);
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
