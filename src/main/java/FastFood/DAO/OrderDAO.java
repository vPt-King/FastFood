package FastFood.DAO;
import java.util.List;
import FastFood.DB.DBconnect;
import FastFood.Entity.Customer;
import FastFood.Entity.Order;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class OrderDAO {
	public List<Order> GetAllOrders()
	{
		List<Order> order = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from orders";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			while(rs.next())
			{
				java.sql.Date date = rs.getDate("started_date");
				java.sql.Date date1 = rs.getDate("due_date");
				String dateString = dateFormat.format(date);
				String dateString1 = dateFormat.format(date1);
				order.add(new Order(rs.getInt("id"),rs.getInt("customer_id"),rs.getString("customer_name"),rs.getInt("quantity"),dateString,dateString1,rs.getDouble("sub_total"),rs.getDouble("total"),rs.getInt("status")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	public void ChangeStatusById(Order r)
	{
		Connection a=DBconnect.getJDBCConnection();
		String q = "update orders set status = ? where id = ?";
		try {

			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, r.getStatus());
			ps.setInt(2,r.getId());
			int k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Order GetCustomerIdByOrderId(int id)
	{
		Order u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from orders where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				//u = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"),rs.getString("gmail"));
				u = new Order(rs.getInt("customer_id"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	public Order GetOrderById(int id)
	{
		Order u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from orders where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			if(rs.next())
			{
				java.sql.Date date = rs.getDate("started_date");
				java.sql.Date date1 = rs.getDate("due_date");
				String dateString = dateFormat.format(date);
				String dateString1 = dateFormat.format(date1);
				u = new Order(rs.getInt("id"), rs.getInt("customer_id"),rs.getString("customer_name"),rs.getInt("quantity"),dateString,dateString1,rs.getDouble("sub_total"),rs.getDouble("total"),rs.getInt("status"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	public int DeleteOrderById(Order o) {
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete FROM orders where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, o.getId());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public int InsertNewOrder(int customer_id, String customer_name, int quantity, String started_date, String due_date,
			double subTotal, double total) {
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into orders(customer_id,customer_name,quantity, started_date,due_date,sub_total,total,status) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, customer_id);
			ps.setString(2, customer_name);
			ps.setInt(3, quantity);
			ps.setString(4, started_date);
			ps.setString(5, due_date);
			ps.setDouble(6, subTotal);
			ps.setDouble(7, total);
			ps.setInt(8, 0);
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			k = 0;
			e.printStackTrace();
		}
		return k;
	}
	public int getOrderId(int customer_id, String customer_name, int quantity, String started_date, String due_date,
			double subTotal, double total) {
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from orders where customer_id = ? and customer_name = ? and quantity = ? and started_date = ? and due_date = ? and sub_total= ? and total= ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, customer_id);
			ps.setString(2, customer_name);
			ps.setInt(3, quantity);
			ps.setString(4, started_date);
			ps.setString(5, due_date);
			ps.setDouble(6, subTotal);
			ps.setDouble(7, total);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				k = rs.getInt("id");
			}
			ps.close();
			a.close();
		} catch (SQLException e) {
			k = 0;
			e.printStackTrace();
		}
		return k;
	}
	public List<Order> GetOrderByCustomerId(int id) {
		List<Order> list = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from orders where customer_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			while(rs.next())
			{
				java.sql.Date date = rs.getDate("started_date");
				java.sql.Date date1 = rs.getDate("due_date");
				String dateString = dateFormat.format(date);
				String dateString1 = dateFormat.format(date1);
				list.add(new Order(rs.getInt("id"), rs.getInt("customer_id"),rs.getString("customer_name"),rs.getInt("quantity"),dateString,dateString1,rs.getDouble("sub_total"),rs.getDouble("total"),rs.getInt("status")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
