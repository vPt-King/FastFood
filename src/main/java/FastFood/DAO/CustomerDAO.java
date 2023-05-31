package FastFood.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import FastFood.DB.DBconnect;
import FastFood.Entity.Customer;
import FastFood.Entity.User;
import FastFood.Entity.Whole;

public class CustomerDAO {
	public int InsertNewCustomer(Customer c)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into customer(name,phone,address,user_id,gmail) values(?,?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, c.getName());
			ps.setString(2, c.getPhone());
			ps.setString(3, c.getAddress());
			ps.setInt(4,c.getUser_id());
			ps.setString(5, c.getGmail());
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public List<Customer> GetAllCustomer()
	{
		List<Customer> customer = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				customer.add(new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"),rs.getString("gmail")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}
	public Customer GetCustomerDetail(Customer customer)
	{
		Customer u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, customer.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				u = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public int UpdateCustomer(Customer c)
	{
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "update customer set name = ?, phone=?,address=? where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, c.getName());
			ps.setString(2, c.getPhone());
			ps.setString(3, c.getAddress());
			ps.setInt(4, c.getId());
			k = ps.executeUpdate();
			ps.close();a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public int DeleteCustomerById(Customer c)
	{
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete from customer where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, c.getId());
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public Customer getCustomerByOrderId(int id)
	{
		Customer u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				u = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"),rs.getString("gmail"));
			}
			System.out.println(u.getGmail());
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public int DeleteCustomerByUserId(int id) {
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "delete FROM customer where customer.user_id = ? limit 100";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			k = ps.executeUpdate();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	public int CheckIfCustomerIsExistInOrder(int id) {
		int k = 0;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer,orders where customer.id = ? and orders.customer_id = customer.id";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				k = 1;
			}
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	public List<Customer> GetAllCustomerOfUser(int id) {
		
		List<Customer> customer = new ArrayList<>();
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer where user_id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				customer.add(new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"),rs.getString("gmail")));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

	public int InsertNewCustomerNoGmail(Customer c) {
		int k = 1;
		Connection a = DBconnect.getJDBCConnection();
		String q = "insert into customer(name,phone,address,user_id) values(?,?,?,?)";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setString(1, c.getName());
			ps.setString(2, c.getPhone());
			ps.setString(3, c.getAddress());
			ps.setInt(4,c.getUser_id());
			k=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	public Customer GetCustomerDetailById(int id)
	{
		Customer u = null;
		Connection a = DBconnect.getJDBCConnection();
		String q = "select * from customer where id = ?";
		try {
			PreparedStatement ps = a.prepareStatement(q);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				u = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("address"),rs.getInt("user_id"));
			}
			rs.close();
			ps.close();
			a.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
}
