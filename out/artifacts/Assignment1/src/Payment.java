import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;


@WebServlet("/Payment")

public class Payment extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession(true);

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet
		String customerName = request.getParameter("name");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String creditCardNo = request.getParameter("creditCardNo");
		String store = request.getParameter("storeId");
		String customerAddress=street + ", " + city + ", " + state + "," + zipcode;
		HashMap<Integer, Stores> stores = new HashMap<>();
		stores = MySqlDataStoreUtilities.selectStores();

		Date date = new Date();
		java.sql.Date myDate = new java.sql.Date(date.getTime());


		System.out.print("the user address is" + street + ", " + city + ", " + state + ", " + zipcode);
		System.out.print(creditCardNo);

		int orderId = utility.getOrderPaymentSize() + 1;
		//iterate through each order
		for (OrderItem oi : utility.getCustomerOrders()) {

			if (store != null) {
				//set the parameter for each column and execute the prepared statement
				for (Map.Entry<Integer, Stores> s : stores.entrySet()) {
					if (Integer.valueOf(store).equals(s.getKey())) {
						int store_id = s.getKey();
						String storeAddress = s.getValue().getStoreStreet() + ", " + s.getValue().getStoreCity() + ", " + s.getValue().getStoreState() + ", " + s.getValue().getStoreZipcode();
						utility.storePayment( customerName, customerAddress, zipcode,creditCardNo, orderId, myDate,
								oi.getName(), oi.getCategory(), oi.getQuantity(), oi.getPrice(), store_id, storeAddress);
					}
				}
			}else if(street != null){
				utility.storePayment(customerName, customerAddress,zipcode, creditCardNo, orderId, myDate,
						oi.getName(), oi.getCategory(), oi.getQuantity(), oi.getPrice(), 0,null);

			}
		}

			//remove the order details from cart after processing

			OrdersHashMap.orders.remove(utility.username());
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");
			pw.print("is stored ");
			pw.print("<br>Your Order No is " + (orderId));
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");

//		}else
//		{
//			utility.printHtml("Header.html");
//			utility.printHtml("LeftNavigationBar.html");
//			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
//			pw.print("<a style='font-size: 24px;'>Order</a>");
//			pw.print("</h2><div class='entry'>");
//
//			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
//			pw.print("</h2></div></div></div>");
//			utility.printHtml("Footer.html");
//		}
		}

		protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {

			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);


		}

}
