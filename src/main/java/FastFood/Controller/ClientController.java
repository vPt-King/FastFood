package FastFood.Controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import FastFood.Entity.Category;
import FastFood.Entity.Customer;
import FastFood.Entity.Customer_Order;
import FastFood.Entity.Item;
import FastFood.Entity.Order;
import FastFood.Entity.OrderData;
import FastFood.Entity.Product;
import FastFood.Entity.User;
import FastFood.DAO.UserDAO;
import FastFood.DAO.CustomerDAO;
import FastFood.DAO.ProductDAO;
import FastFood.DAO.OrderDAO;
import FastFood.DAO.Order_ProductDAO;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import FastFood.Model.InsertResponse;
import FastFood.Model.MessResponse;
@RestController
@CrossOrigin(origins = {"http://localhost:3000/login","http://localhost:3000/products_category"})
public class ClientController {
	
	@PostMapping("/login")
	public ResponseEntity<String> Login(@RequestBody User user)
	{
		UserDAO uDAO = new UserDAO();
		Gson gson = new Gson();
		MessResponse mr = null;
		int k = uDAO.CheckIfUseAccountLogin(user);
		if(k == -1)
		{
			mr = new MessResponse(0,"Login Failure !! Check your account again",-1);
		}
		else {
			mr = new MessResponse(1,"Login Success",k);
		}
		String json = gson.toJson(mr);
		return new ResponseEntity<String> (json, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> Register(@RequestBody User user)
	{
		UserDAO uDAO = new UserDAO();
		Gson gson = new Gson();
		MessResponse mr = null;
		int k = uDAO.CheckIfUserIsExisted(user);
		if(k == 1)
		{
			mr = new MessResponse(0, "Email is existed!!");
			String json = gson.toJson(mr);
			return new ResponseEntity<String>(json,HttpStatus.OK);
		}
		else {
			int u = uDAO.InsertNewUser(user);
			if(u == 0)
			{
				mr = new MessResponse(0, "Can not register !!");
				String json = gson.toJson(mr);
				return new ResponseEntity<String>(json,HttpStatus.OK);
			}
			else {
				mr = new MessResponse(1, "Register successfully !!");
				String json = gson.toJson(mr);
				return new ResponseEntity<String>(json,HttpStatus.OK);
			}
		}
	}
	@PostMapping("/get_products_category")
	public List<Product> GetProductsByIdCategory(@RequestBody Category category )
	{
		ProductDAO pDAO = new ProductDAO();
		return pDAO.FindProductsByCategoryId(category.getId());
	}
	@PostMapping("/add-customer")
	public ResponseEntity<String> AddCustomer(@RequestBody Customer customer)
	{
		Gson gson = new Gson();
		InsertResponse ir = null;
		CustomerDAO customerDAO = new CustomerDAO();
		UserDAO uDAO = new UserDAO();
		int k = customerDAO.InsertNewCustomerNoGmail(customer);
		if(k == 1)
		{
			ir = new InsertResponse(1,"insert successfully");
		}
		else {
			ir = new InsertResponse(1,"insert not successfully");
		}
		String json = gson.toJson(ir);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	@PostMapping("/add-order")
	public ResponseEntity<String> AddOrder(@RequestBody OrderData orderData)
	{
		Gson gson = new Gson();
		InsertResponse ir = null;
		ProductDAO pDAO = new ProductDAO();
		Order_ProductDAO opDAO = new Order_ProductDAO();
		int k  = 1;
		int quantity = 0;
		int customer_id = orderData.getCustomer_id();
		List<Item> listItems = orderData.getListItems();
		CustomerDAO cDAO = new CustomerDAO();
		Customer cus = cDAO.GetCustomerDetailById(customer_id);
		for(Item item : listItems)
		{
			quantity += item.getQuantity();
		}
		LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String currentDateAsString = currentDate.format(formatter);
        String futureDateAsString = futureDate.format(formatter);
        OrderDAO oDAO = new OrderDAO();
        int t = oDAO.InsertNewOrder(cus.getId(),cus.getName(),quantity,currentDateAsString,futureDateAsString,orderData.getSubTotal(),orderData.getTotal());
        int OrderId = oDAO.getOrderId(cus.getId(),cus.getName(),quantity,currentDateAsString,futureDateAsString,orderData.getSubTotal(),orderData.getTotal());
        for(Item item : listItems)
		{
			int product_id = item.getId();
			int sl = item.getQuantity();
			Product p = pDAO.FindProductById(product_id);
			p.setQuantity(p.getQuantity()-sl);
			pDAO.ChangeProductQuantity(p.getQuantity(),p.getId());
			Double subtotal = p.getPrice() * sl;
			int u = opDAO.InsertNewOP(p.getId(), OrderId,sl,subtotal,p.getDescription(),p.getName());
		}
		if(t == 1)
		{
			ir = new InsertResponse(1,"insert successfully");
		}
		else {
			ir = new InsertResponse(1,"insert not successfully");
		}
		String json = gson.toJson(ir);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	@PostMapping("/get-customer-orders")
	public List<Customer_Order> GetCustomerByUserId(@RequestBody User u)
	{
		CustomerDAO cDAO = new CustomerDAO();
		List<Customer> listCustomer = cDAO.GetAllCustomerOfUser(u.getId());
		List<Customer_Order> listCO = new ArrayList<Customer_Order>();
		OrderDAO oDAO = new OrderDAO();
		for(Customer c:listCustomer)
		{
			List<Order> listOrder = oDAO.GetOrderByCustomerId(c.getId());
			if(listOrder.size() > 0)
			{
				for(Order o: listOrder)
				{
					listCO.add(new Customer_Order(c,o));
				}
			}
		}
		return listCO;
	}
}
