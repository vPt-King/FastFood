package FastFood.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ArrayList;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import FastFood.DAO.AdminDAO;
import FastFood.DAO.CategoryDAO;
import FastFood.DAO.ProductDAO;
import FastFood.DAO.UserDAO;
import FastFood.DAO.OrderDAO;
import FastFood.DAO.CustomerDAO;
import FastFood.DAO.Order_ProductDAO;
import FastFood.Entity.Admin;
import FastFood.Entity.Category;
import FastFood.Entity.Customer;
import FastFood.Entity.Order;
import FastFood.Entity.Order_Product;
import FastFood.Entity.Product;
import FastFood.Entity.User;
import FastFood.Entity.User_Customer;
import FastFood.Entity.Whole;
import FastFood.Model.LoginResponse;
import FastFood.Model.PutResponse;
import FastFood.Model.RegisterResponse;
//import HotelBooking.Dao.RoomsDAO;
//import HotelBooking.Entity.Rooms;
import FastFood.Model.InsertResponse;
import FastFood.Model.DeleteResponse;
@RestController
@CrossOrigin(origins = 
		{"http://localhost:3000/admin/login",
		"http://localhost:3000/admin",
		"http://localhost:3000/admin/category",
		"http://localhost:3000/admin/more-category",
		"http://localhost:3000/admin/edit-category",
		"http://localhost:3000/admin/delete-category",
		"http://localhost:3000/admin/edit-product",
		})
@RequestMapping(value="/admin")
public class AdminController {
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Admin adminInp)
	{
		AdminDAO adminDAO = new AdminDAO();
		Admin admin = adminDAO.GetAdminAccount(adminInp);
		Gson gson = new Gson();
		if(admin != null)
		{
			LoginResponse res = new LoginResponse(1, admin.getId(),"Login success");
			String json = gson.toJson(res);
			return new ResponseEntity<String>(json, HttpStatus.OK);
		}
		LoginResponse res = new LoginResponse(0,"Login Failure! Check your account again");
		String json = gson.toJson(res);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Admin adminInp)
	{
		AdminDAO adminDAO = new AdminDAO();
		Gson gson = new Gson();
		int check = adminDAO.CheckIfAdminIsExist(adminInp);
		if(check == 0)
		{
			int reg = adminDAO.InsertNewAdmin(adminInp);
			if(reg == 1) {
			RegisterResponse res = new RegisterResponse(1,"Register success");
			String json = gson.toJson(res);
			return new ResponseEntity<String>(json, HttpStatus.OK);
			}
		}
		RegisterResponse res = new RegisterResponse(0,"Gmail is exist");
		String json = gson.toJson(res);
		return new ResponseEntity<String>(json, HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public List<Category> GetCategoryList()
	{
		CategoryDAO categoryDAO = new CategoryDAO();
		return categoryDAO.FindAllCategroy();
	}
	
	@PostMapping("/more-category")
	public ResponseEntity<String> InsertNewCategory(@RequestBody Category categoryInp)
	{
		CategoryDAO categoryDAO = new CategoryDAO();
		int check = categoryDAO.CheckIfCategoryIsExist(categoryInp);
		Gson gson = new Gson();
		if(check == 1)
		{
			InsertResponse res = new InsertResponse(0,"Category is already existed");
			String json = gson.toJson(res);
			return new ResponseEntity<String>(json,HttpStatus.OK);
		}
		else {
			int insert = categoryDAO.InsertNewCategory(categoryInp);
			if(insert == 1)
			{
				InsertResponse res = new InsertResponse(1,"Insert success");
				String json = gson.toJson(res);
				return new ResponseEntity<String>(json,HttpStatus.OK);
			}
			else {
				InsertResponse res = new InsertResponse(0,"Insert error");
				String json = gson.toJson(res);
				return new ResponseEntity<String>(json,HttpStatus.OK);
			}
		}
	}
	
	@PostMapping("/edit-category")
	public Category GetCategory(@RequestBody Category model)
	{
		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = categoryDAO.FindCategoryById(model.getId());
		return category;
	}
	
	@PutMapping("/edit-category")
	public ResponseEntity<String> EditCategory(@RequestBody Category category)
	{
		CategoryDAO categoryDAO = new CategoryDAO();
		Gson gson = new Gson();
		int update = categoryDAO.EditCategory(category);
		if(update == 0)
		{
			PutResponse pr = new PutResponse(0, "Your category is already exited");
			String json = gson.toJson(pr);
			return new ResponseEntity<String>(json,HttpStatus.OK);
		}
		else {
			PutResponse pr = new PutResponse(1, "Your category is updated");
			String json = gson.toJson(pr);
			return new ResponseEntity<String>(json,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete-category")
	public ResponseEntity<String> DeleteCategory(@RequestBody Category category)
	{
		Gson gson = new Gson();
		CategoryDAO categoryDAO = new CategoryDAO();
		DeleteResponse dr = null;
		if(categoryDAO.CheckIfCategoryIsUsedByProduct(category.getId()) != 1)
		{
			dr = new DeleteResponse(0, "Error occur! Can not delete category bescause is attrached some product");
		}
		else 
		{
			int k = categoryDAO.DeleteCategory(category);
			if(k != 1)
			{
				dr = new DeleteResponse(0, "Error occur! Can not delete category");
			}
			else {
				dr = new DeleteResponse(1, "Delete successfully");
			}
		}
		String json = gson.toJson(dr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getimage/{photo}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo)
	{
		if(!photo.equals("")||photo != null)
		{
			try {
				Path filename = Paths.get("uploads",photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource bar = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(bar);
			}catch(Exception e)
			{
				System.err.println("Upload err");
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/product")
	public List<Product> GetAllProduct()
	{
		ProductDAO pDAO = new ProductDAO();
		return pDAO.FindAllProduct();
	}
	
	@PostMapping("/more-product")
	public ResponseEntity<String> InsertNewProduct(MultipartHttpServletRequest request) throws IOException {
		Gson gson = new Gson();
		InsertResponse ir = null;
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    double price = Double.parseDouble(request.getParameter("price"));
	    int category_id = Integer.parseInt(request.getParameter("category_id"));
	    MultipartFile photo = request.getFile("picture");
	    String photoName = photo.getOriginalFilename();
	    Path path = Paths.get("uploads/");
	    try {
	    InputStream is = photo.getInputStream();
	    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e)
	    {
	    	System.out.println("no");
	    }
	    Product p = new Product(name,description,quantity,price,category_id,photoName);
	    ProductDAO pDAO = new ProductDAO();
	    if(pDAO.CheckIfProductIsExist(p) == 0)
	    {
	    	int k = pDAO.InsertNewProduct(p);
	    	ir = new InsertResponse(1,"insert success");
	    	
	    }
	    else {
	    	ir = new InsertResponse(0,"insert not success");
	    }
	    String json = gson.toJson(ir);
 	    return new ResponseEntity<String>(json,HttpStatus.OK);   
	}
	
	@PostMapping("/get-product-detail")
	public Product GetProductDetail(@RequestBody Product p)
	{
		ProductDAO pDAO = new ProductDAO();
		return pDAO.FindProductDetail(p);
	}
	
	
	@PostMapping("/get-product-details")
	public Whole GetProductDetails(@RequestBody Product p)
	{
		ProductDAO pDAO = new ProductDAO();
		Product product = pDAO.FindProductDetail(p);
		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = categoryDAO.FindCategoryById(product.getCategory_id());
		Whole whole = new Whole(product,category);
		return whole;
	}
	
	
	@PutMapping("/edit-product")
	public ResponseEntity<String> EditProduct(MultipartHttpServletRequest request) throws IOException {
		Gson gson = new Gson();
		InsertResponse ir = null;
		int id = Integer.parseInt(request.getParameter("id"));
	    String name = request.getParameter("name");
	    String description = request.getParameter("description");
	    int quantity = Integer.parseInt(request.getParameter("quantity"));
	    double price = Double.parseDouble(request.getParameter("price"));
	    int category_id = Integer.parseInt(request.getParameter("category_id"));
	    MultipartFile photo = request.getFile("picture");
	    String photoName = photo.getOriginalFilename();
	    Path path = Paths.get("uploads/");
	    try {
	    InputStream is = photo.getInputStream();
	    Files.copy(is,path.resolve(photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
	    }catch(Exception e)
	    {
	    	System.out.println("no");
	    }
	    Product p = new Product(id,name,description,quantity,price,category_id,photoName);
	    ProductDAO pDAO = new ProductDAO();
	    int k = pDAO.EditProductById(p);
	    ir = new InsertResponse(1,"edit success");
	    String json = gson.toJson(ir);
 	    return new ResponseEntity<String>(json,HttpStatus.OK);   
	}
	
	@DeleteMapping("/delete-product")
	public ResponseEntity<String> DeleteProduct(@RequestBody Product p)
	{
		Gson gson = new Gson();
		DeleteResponse dr = null;
		ProductDAO pDAO = new ProductDAO();
		if(pDAO.CheckIfProductIsExistInOrder(p.getId()) == 0)
		{
			int k = pDAO.DeleteProductById(p);
			if(k == 1)
			{
				dr = new DeleteResponse(1, "delete oke");
			}
			else {
				dr = new DeleteResponse(1, "delete not oke");
			}
		}
		else {
			dr = new DeleteResponse(1, "delete not successfully because this product is in some progress order");
		}
		String json = gson.toJson(dr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PostMapping("/add-user")
	public ResponseEntity<String> AddMoreUser(@RequestBody User user)
	{
		Gson gson = new Gson();
		InsertResponse ir = null;
		UserDAO userDAO = new UserDAO();
		int k = userDAO.InsertNewUser(user);
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
	
	@GetMapping("/get-all-user")
	public List<User> GetAllUser()
	{
		UserDAO userDAO = new UserDAO();
		return userDAO.GetAllUser();
	}
	
	@PostMapping("/get-user-detail")
	public User GetUserDetail(@RequestBody User user)
	{
		UserDAO userDAO = new UserDAO();
		return userDAO.GetUserDetail(user);
	}
	@PutMapping("/edit-user")
	public ResponseEntity<String> EditUser(@RequestBody User user)
	{
		UserDAO userDAO = new UserDAO();
		Gson gson = new Gson();
		PutResponse pr = null;
		int k = userDAO.UpdateUser(user);
		if(k == 1)
		{
			pr = new PutResponse(1,"update successfully");
		}
		else {
			pr = new PutResponse(1,"insert not successfully");
		}
		String json = gson.toJson(pr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<String> DeleteUser(@RequestBody User user)
	{
		Gson gson = new Gson();
		DeleteResponse dr = null;
		UserDAO userDAO = new UserDAO();
		if(userDAO.CheckIfUserIsExistInOrder(user.getId()) == 0)
		{
			CustomerDAO customerDAO = new CustomerDAO();
			int dc = customerDAO.DeleteCustomerByUserId(user.getId());
			int k = userDAO.DeleteUserById(user);
			if(k == 1)
			{
				dr = new DeleteResponse(1, "delete oke");
			}
			else {
				dr = new DeleteResponse(1, "delete not oke");
			}
		}
		else {
			dr = new DeleteResponse(1, "Delete not successfully because this user is in some progress order");
		}
		String json = gson.toJson(dr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	
	@PostMapping("/add-customer")
	public ResponseEntity<String> AddCustomer(@RequestBody Customer customer)
	{
		Gson gson = new Gson();
		InsertResponse ir = null;
		CustomerDAO customerDAO = new CustomerDAO();
		UserDAO uDAO = new UserDAO();
		User user = uDAO.GetUserDetailById(customer.getUser_id());
		customer.setGmail(user.getGmail());
		int k = customerDAO.InsertNewCustomer(customer);
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
	
	@GetMapping("/get-all-customer")
	public List<User_Customer> GetAllCustomer()
	{
		CustomerDAO cDAO = new CustomerDAO();
		List<Customer> listCustomer = cDAO.GetAllCustomer();
		UserDAO uDAO = new UserDAO();
		List<User_Customer> listWhole = new ArrayList<>();
		for(Customer c : listCustomer)
		{
			User u = uDAO.FindUserById(c.getUser_id());
			User_Customer w = new User_Customer(c,u);
			listWhole.add(w);
		}
		return listWhole;
		
	}
	
	@PostMapping("/get-customer-detail")
	public Customer GetCustomerDetail(@RequestBody Customer customer)
	{
		CustomerDAO customerDAO = new CustomerDAO();
		return customerDAO.GetCustomerDetail(customer);
	}
	
	@PutMapping("/edit-customer")
	public ResponseEntity<String> EditCustomer(@RequestBody Customer customer)
	{
		CustomerDAO customerDAO = new CustomerDAO();
		Gson gson = new Gson();
		PutResponse pr = null;
		int k = customerDAO.UpdateCustomer(customer);
		if(k == 1)
		{
			pr = new PutResponse(1,"update successfully");
		}
		else {
			pr = new PutResponse(1,"insert not successfully");
		}
		String json = gson.toJson(pr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	@DeleteMapping("/delete-customer")
	public ResponseEntity<String> DeleteCustomer(@RequestBody Customer customer)
	{
		Gson gson = new Gson();
		DeleteResponse dr = null;
		CustomerDAO customerDAO = new CustomerDAO();
		if(customerDAO.CheckIfCustomerIsExistInOrder(customer.getId()) == 0)
		{
			int k = customerDAO.DeleteCustomerById(customer);
			if(k == 1)
			{
				dr = new DeleteResponse(1, "delete oke");
			}
			else {
				dr = new DeleteResponse(1, "delete not oke");
			}
		}
		else {
			dr = new DeleteResponse(1, "Delete not successfully because this customer is in some progress order");
		}
		String json = gson.toJson(dr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
	@GetMapping("/get-all-orders")
	public List<Order> GetAllOrders()
	{
		OrderDAO oDAO = new OrderDAO();
		return oDAO.GetAllOrders();
	}
	
	@PostMapping("/change-status-orders")
	public void ChangeStatusOrders(@RequestBody Order r)
	{
		OrderDAO oDAO = new OrderDAO();
		oDAO.ChangeStatusById(r);
	}
	
	@PostMapping("/get-customer-orders")
	public Customer GetCustomerByOrderId(@RequestBody Order r)
	{
		OrderDAO oDAO = new OrderDAO();
		Order order = oDAO.GetCustomerIdByOrderId(r.getId());
		CustomerDAO cDAO = new CustomerDAO();
		return cDAO.getCustomerByOrderId(order.getCustomer_id());
	}
	
	@PostMapping("/get-orders-details")
	public Order GetOrderDetail(@RequestBody Order r)
	{
		OrderDAO oDAO = new OrderDAO();
		return oDAO.GetOrderById(r.getId());
	}
	@PostMapping("/get-order-products")
	public List<Order_Product> GetListOrder_Product(@RequestBody Order r) 
	{
		Order_ProductDAO opDAO = new Order_ProductDAO();
		return opDAO.GetListOrder_ProductByOrderId(r.getId());
	}
	
	@DeleteMapping("delete-order")
	public ResponseEntity<String> DeleteOrder(@RequestBody Order o)
	{
		Gson gson = new Gson();
		DeleteResponse dr = null;
		OrderDAO oDAO = new OrderDAO();
		int k = oDAO.DeleteOrderById(o);
		if(k == 1)
		{
			dr = new DeleteResponse(1, "delete oke");
		}
		else {
			dr = new DeleteResponse(1, "delete not oke");
		}
		String json = gson.toJson(dr);
		return new ResponseEntity<String>(json,HttpStatus.OK);
	}
}
