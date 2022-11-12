import java.sql.*;
import java.util.*;
import java.util.Date;

public class MySqlDataStoreUtilities
{
static Connection conn = null;

public static void getConnection()
{

	try
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","xwwxgg144");
	//	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase","root","xwwxgg144");

	}
	catch(Exception e)
	{
		System.out.println(e);
	
	}
}
	public static void Insertproducts()
	{
		try{


			getConnection();


			String truncatetableacc = "delete from Product_accessories;";
			PreparedStatement pstt = conn.prepareStatement(truncatetableacc);
			pstt.executeUpdate();

			String truncatetableprod = "delete from  Productdetails;";
			PreparedStatement psttprod = conn.prepareStatement(truncatetableprod);
			psttprod.executeUpdate();



			String insertProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productManufacturer,productCondition,productDiscount)" +
					"VALUES (?,?,?,?,?,?,?);";
//			for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.accessories.entrySet())
//			{
//				String name = "accessories";
//				VoiceAssistant acc = entry.getValue();
//
//				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
//				pst.setString(1,name);
//				pst.setString(2,acc.getId());
//				pst.setString(3,acc.getName());
//				pst.setDouble(4,acc.getPrice());
//				pst.setString(5,acc.getImage());
//				pst.setString(6,acc.getRetailer());
//				pst.setString(7,acc.getCondition());
//				pst.setDouble(8,acc.getDiscount());
//
//				pst.executeUpdate();
//
//
//			}

			for(Map.Entry<String,Wearable> entry : SaxParserDataStore.wearables.entrySet())
			{
				Wearable con = entry.getValue();
				String name = "wearables";



				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,con.getId());
				pst.setString(3,con.getName());
				pst.setDouble(4,con.getPrice());
				pst.setString(5,con.getRetailer());
				pst.setString(6,con.getCondition());
				pst.setDouble(7,con.getDiscount());

				pst.executeUpdate();

			}
			for(Map.Entry<String,Phones> entry : SaxParserDataStore.phone.entrySet())
			{
				String name = "phone";
				Phones phones = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,phones.getId());
				pst.setString(3,phones.getName());
				pst.setDouble(4,phones.getPrice());
				pst.setString(5,phones.getRetailer());
				pst.setString(6,phones.getCondition());
				pst.setDouble(7,phones.getDiscount());

				pst.executeUpdate();


			}
			for(Map.Entry<String,Laptops> entry : SaxParserDataStore.laptop.entrySet())
			{
				String name = "laptop";
				Laptops laptops = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,laptops.getId());
				pst.setString(3,laptops.getName());
				pst.setDouble(4,laptops.getPrice());
				pst.setString(5,laptops.getRetailer());
				pst.setString(6,laptops.getCondition());
				pst.setDouble(7,laptops.getDiscount());

				pst.executeUpdate();


			}
			for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voice.entrySet())
			{
				String name = "voice";
				VoiceAssistant voices = entry.getValue();

				PreparedStatement pst = conn.prepareStatement(insertProductQurey);
				pst.setString(1,name);
				pst.setString(2,voices.getId());
				pst.setString(3,voices.getName());
				pst.setDouble(4,voices.getPrice());
				pst.setString(5,voices.getRetailer());
				pst.setString(6,voices.getCondition());
				pst.setDouble(7,voices.getDiscount());

				pst.executeUpdate();


			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void deleteOrder(int orderId,String productId)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from Transactions where orderId=? and productId=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,orderId);
		pst.setString(2,productId);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(String username, String customerName, String customerAddress, String creditNo, int orderId,
							   Date purchaseDate, String productId, String category, String quantity,
							   double price,int storeId,String storeAddress,String zipcode)
{
	try
	{
	
		getConnection();
		String insertIntoTransactionsQuery = "INSERT INTO Transactions(username,CustomerName,CustomerAddress,creditNo,orderId,purchaseDate,productId,category,quantity,price,storeId,storeAddress,zipcode) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
		PreparedStatement pst = conn.prepareStatement(insertIntoTransactionsQuery);
		//set the parameter for each column and execute the prepared statement
		java.sql.Date sqlDate = new java.sql.Date(purchaseDate.getTime());
		pst.setString(1,username);
		pst.setString(2,customerName);
		pst.setString(3,customerAddress);
		pst.setString(4,creditNo);
		pst.setInt(5,orderId);
		pst.setDate(6, sqlDate);
		pst.setString(7,productId);
		pst.setString(8,category);
		pst.setString(9,quantity);
		pst.setDouble(10,price);
		pst.setInt(11,storeId);
		pst.setString(12,storeAddress);
		pst.setString(13,zipcode);

		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from Transactions";
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("orderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("orderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("orderId"));
			System.out.println("data is"+rs.getInt("orderId")+orderPayments.get(rs.getInt("orderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getString("username"),rs.getString("CustomerName"),rs.getString("CustomerAddress"),
					rs.getString("creditNo"),rs.getInt("orderId"),rs.getDate("purchaseDate"),
					rs.getString("productId"),rs.getString("category"),rs.getString("quantity"),
					rs.getDouble("price"),rs.getInt("storeId"),rs.getString("storeAddress"),rs.getString("zipcode"));


			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
		boolean n = pst.execute();
		if(n==true){
			System.out.println("Connected!");
		}
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
public static HashMap<Integer,Stores> selectStores(){
	HashMap<Integer,Stores> stores = new HashMap<>();
	try{
		getConnection();
		Statement stmt = conn.createStatement();
		String selectStoresQuery = "select * from Stores";
		ResultSet rs = stmt.executeQuery(selectStoresQuery);
		while(rs.next()){
			Stores store = new Stores(rs.getInt("ID"),rs.getString("Street"),rs.getString("city")
					,rs.getString("state"),rs.getString("zip"));
			stores.put(rs.getInt("ID"),store);
		}
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	return stores;
}

public static void insertCustomers(String userId,String name,String address){
	try
	{

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Customer(user_id,customerName,address) "
				+ "VALUES (?,?,?);";

		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,userId);
		pst.setString(2,name);
		pst.setString(3,address);
		pst.execute();
		boolean n = pst.execute();
		if(n==true){
			System.out.println("Connected!");
		}
	}
	catch(Exception e)
	{

	}
}
	public static HashMap<String,Wearable> getWearables()
	{
		HashMap<String,Wearable> hm=new HashMap<String,Wearable>();
		try
		{
			getConnection();

			String selectWearable="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectWearable);
			pst.setString(1,"wearables");
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				Wearable con = new Wearable(rs.getString("productName"), rs.getDouble("productPrice"), rs.getString("productManufacturer"), rs.getString("productCondition"), rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
			}
				}
		catch(Exception e)
		{
		}
		return hm;
	}
	public static HashMap<String,Laptops> getLaptopss()
	{
		HashMap<String,Laptops> hm=new HashMap<String,Laptops>();
		try
		{
			getConnection();

			String selectLaptops="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectLaptops);
			pst.setString(1,"laptops");
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{	Laptops lap = new Laptops(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), lap);
				lap.setId(rs.getString("Id"));

			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}

	public static HashMap<String,Phones> getPhoness()
	{
		HashMap<String,Phones> hm=new HashMap<String,Phones>();
		try
		{
			getConnection();

			String selectPhones="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectPhones);
			pst.setString(1,"phones");
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{	Phones phone = new Phones(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), phone);
				phone.setId(rs.getString("Id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}

	public static HashMap<String,VoiceAssistant> getVoice()
	{
		HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
		try
		{
			getConnection();

			String selectVoice="select * from  Productdetails where ProductType=?";
			PreparedStatement pst = conn.prepareStatement(selectVoice);
			pst.setString(1,"voice");
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{	VoiceAssistant voice = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), voice);
				voice.setId(rs.getString("Id"));

			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}

	public static String addproducts(String producttype,String productId,String productName,double productPrice,String productManufacturer,String productCondition,double productDiscount)
	{
		String msg = "Product is added successfully";
		try{

			getConnection();
			String addProductQurey = "INSERT INTO  Productdetails(ProductType,Id,productName,productPrice,productManufacturer,productCondition,productDiscount)" +
					"VALUES (?,?,?,?,?,?,?);";

			String name = producttype;

			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productManufacturer);
			pst.setString(6,productCondition);
			pst.setDouble(7,productDiscount);

			pst.executeUpdate();

		}
		catch(Exception e)
		{
			msg = "Erro while adding the product";
			e.printStackTrace();

		}
		return msg;
	}
	public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productManufacturer,String productCondition,double productDiscount)
	{
		String msg = "Product is updated successfully";
		try{

			getConnection();
			String updateProductQurey = "UPDATE Productdetails SET productName=?,productPrice=?,productManufacturer=?,productCondition=?,productDiscount=? where Id =?";



			PreparedStatement pst = conn.prepareStatement(updateProductQurey);

			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productManufacturer);
			pst.setString(4,productCondition);
			pst.setDouble(5,productDiscount);
			pst.setString(6,productId);
			pst.executeUpdate();

		}
		catch(Exception e)
		{
			msg = "Product cannot be updated";
			e.printStackTrace();

		}
		return msg;
	}
	public static String deleteproducts(String productId)
	{   String msg = "Product is deleted successfully";
		try
		{

			getConnection();
			String deleteproductsQuery ="Delete from Productdetails where Id=?";
			PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
			pst.setString(1,productId);

			pst.executeUpdate();
		}
		catch(Exception e)
		{
			msg = "Proudct cannot be deleted";
		}
		return msg;
	}

}	