import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
			result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				if(session.getAttribute("usertype").equals("retailer"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
							+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>"
							+"<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}

				else if(session.getAttribute("usertype").equals("manager")){
					result = result +"<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
							+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>"
							+"<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
							+ "<li><a href='Inventory'><span class='glyphicon'>Inventory</span></a></li>"
							+"<li><a href='SalesReport'><span class='glyphicon'>SalesReport</span></a></li>"
							+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else
				{
					result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
			result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
			pw.print(result);
		} else
			pw.print(result);
	}




	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size=0;
		try
		{
			orderPayments =MySqlDataStoreUtilities.selectOrder();
				
		}
		catch(Exception e)
		{
			
		}
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
				size=entry.getKey();
		}
			
		return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("wearables")){
			Wearable wearable;
			wearable = SaxParserDataStore.wearables.get(name);
			OrderItem orderitem = new OrderItem(wearable.getName(), wearable.getPrice(),"wearable", wearable.getCondition(),wearable.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phones phones = null;
			phones = SaxParserDataStore.phone.get(name);
			OrderItem orderitem = new OrderItem(phones.getName(), phones.getPrice(),"Phones", phones.getCondition(), phones.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("laptop")){
			Laptops laptops = null;
			laptops = SaxParserDataStore.laptop.get(name);
			OrderItem orderitem = new OrderItem(laptops.getName(), laptops.getPrice(),"Laptops", laptops.getCondition(), laptops.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("voiceAssistant")){
			VoiceAssistant voiceAssistant = SaxParserDataStore.voice.get(name);
			OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(),"Voice Assistant", voiceAssistant.getCondition(), voiceAssistant.getRetailer());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(String customerName, String customerAddress, String zipcode,String creditNo, int orderId,
							 Date purchaseDate, String productId, String category, String quantity,
							 double price,
							 int storeId, String storeAddress){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			// get the payment details file 
		try
		{
			orderPayments=MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{
			
		}
		if(orderPayments==null)
		{
			orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
		if(!orderPayments.containsKey(orderId)){	
			ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
			orderPayments.put(orderId, arr);
		}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		OrderPayment orderpayment = new OrderPayment(username(),customerName,customerAddress,creditNo,orderId,purchaseDate,productId,category,quantity,price,storeId,storeAddress,zipcode);
		listOrderPayment.add(orderpayment);

		HashMap<String,Product> prod = new HashMap<>();
		prod = MySqlDataStoreUtilities.getProduct();
		for(Map.Entry<String,Product> p: prod.entrySet()) {
			if (p.getValue().getName().equals(productId)) {
				Product product = p.getValue();
				MySqlDataStoreUtilities.updateproducts(product.getType(), product.getId(), productId,
						product.getPrice(), product.getRetailer(), product.getCondition(), product.getDiscount(),
						product.getRebate(), product.getInventory() - 1);
			}
		}
			// add order details into database
		try
		{
			MySqlDataStoreUtilities.insertOrder(username(),customerName,customerAddress,creditNo,orderId,purchaseDate,productId,category,quantity,price,storeId,storeAddress,zipcode);
			MySqlDataStoreUtilities.insertCustomers(username(), customerName,customerAddress);


		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}

	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Wearable> getWearables(){
			HashMap<String, Wearable> hm = new HashMap<String, Wearable>();
			hm.putAll(SaxParserDataStore.wearables);
			return hm;
	}
	public HashMap<String, VoiceAssistant> getVoice(){
		HashMap<String, VoiceAssistant> hm = new HashMap<>();
		hm.putAll(SaxParserDataStore.voice);
		return hm;
	}
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Phones> getPhones(){
			HashMap<String, Phones> hm = new HashMap<String, Phones>();
			hm.putAll(SaxParserDataStore.phone);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Laptops in the store.*/

	public HashMap<String, Laptops> getLaptops(){
			HashMap<String, Laptops> hm = new HashMap<String, Laptops>();
			hm.putAll(SaxParserDataStore.laptop);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProductsWearable(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Wearable> entry : getWearables().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductPhones(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phones> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsLaptops(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptops> entry : getLaptops().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	public ArrayList<String> getProductsVoice(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoice().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}


	public String storeReview(String productName,String category,String price,String storeID,String storeZip,String storeCity,
							  String storeState,String productOnSale,String makerName,String makerRebate,
							  String userAge,String gender, String userJob,String reviewRating,
							  String reviewDate,String reviewText){
		String message=MongoDBDataStoreUtilities.insertReview(productName,category,price,storeID,storeZip,storeCity, storeState,productOnSale,makerName,makerRebate,username(),
				userAge,gender,userJob,reviewRating,reviewDate,reviewText);

		if(!message.equals("Successfull"))
		{
			return "UnSuccessfull";
		}
		else
		{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{

			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
			// if there exist product review already add it into same list for productName or create a new record with product name

			if(!reviews.containsKey(productName)){
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(productName, arr);
			}
			ArrayList<Review> listReview = reviews.get(productName);
			Review review = new Review(productName,category,price,storeID,storeZip,storeCity, storeState,productOnSale,makerName,makerRebate,username(),
					userAge,gender,userJob,reviewRating,reviewDate,reviewText);
			listReview.add(review);

			// add Reviews into database

			return "Successfull";
		}
	}
}
