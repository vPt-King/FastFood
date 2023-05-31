package FastFood.Controller;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import FastFood.DB.DBconnect;
import FastFood.Entity.Shop;
import FastFood.Entity.User;
import FastFood.Model.MessResponse;
import FastFood.Service.EmailSenderService;
import FastFood.DAO.ShopDAO;
@RestController
@CrossOrigin(origins ="http://localhost:3000/admin/invoice")
public class ShopController {
	@Autowired
	private EmailSenderService senderService;
	
	@GetMapping("/get-shop")
	public Shop GetShop()
	{
		ShopDAO s = new ShopDAO();
		return s.GetShop();
	}
	
//	@PostMapping("/forgot-password")
//	public void SendEmailForgotPassword(@RequestBody User user)
//	{
//		senderService.sendEmail(user.getGmail(),"This is subject", "this is body of email");
//	}
}
