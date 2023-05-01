package FastFood.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import FastFood.DB.DBconnect;
import FastFood.Entity.Shop;
import FastFood.Entity.User;
import FastFood.DAO.ShopDAO;
@RestController
@CrossOrigin(origins ="http://localhost:3000/admin/invoice")
public class ShopController {
	@GetMapping("/get-shop")
	public Shop GetShop()
	{
		ShopDAO s = new ShopDAO();
		return s.GetShop();
	}
}
