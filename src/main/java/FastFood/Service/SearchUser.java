package FastFood.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import FastFood.DAO.CategoryDAO;
import FastFood.DAO.ProductDAO;
import FastFood.DAO.UserDAO;
import FastFood.Entity.Category;
import FastFood.Entity.Product;
import FastFood.Entity.User;
public class SearchUser {
	public List<User> SearchUserById(int user_id)
	{
		UserDAO uDAO = new UserDAO();
		User u = uDAO.FindUserById(user_id);
		System.out.println(u.getName());
		List<User> ul = new ArrayList<>();
		ul.add(u);
		return ul;
	}
	public List<User> SearchUserByGmail(String string)
	{
		UserDAO pDAO = new UserDAO();
		List<User> ListUser = pDAO.GetAllUser();
		List<User> res = new ArrayList<>();
		for(User p : ListUser)
		{
			if(p.getGmail().contains(string))
			{
				res.add(p);
			}
		}
		return res;
	}
	
	public List<User> SearchUserByName(String string)
	{
		UserDAO pDAO = new UserDAO();
		List<User> ListUser = pDAO.GetAllUser();
		List<User> res = new ArrayList<>();
		for(User p : ListUser)
		{
			if(p.getName().contains(string))
			{
				res.add(p);
			}
		}
		return res;
	}
	
	public List<User> SearchUserByPhone(String string)
	{
		UserDAO pDAO = new UserDAO();
		List<User> ListUser = pDAO.GetAllUser();
		List<User> res = new ArrayList<>();
		for(User p : ListUser)
		{
			if(p.getPhone().contains(string))
			{
				res.add(p);
			}
		}
		return res;
	}
	public List<User> SearchUserByAddress(String string)
	{
		UserDAO pDAO = new UserDAO();
		List<User> ListUser = pDAO.GetAllUser();
		List<User> res = new ArrayList<>();
		for(User p : ListUser)
		{
			if(p.getAddress().contains(string))
			{
				res.add(p);
			}
		}
		return res;
	}
	
}
