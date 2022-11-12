import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Pickup")
public class Pickup extends HttpServlet{
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities Utility = new Utilities(request, pw);
            storeOrders(request, response);
        }

        protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try
            {
                response.setContentType("text/html");
                PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
                if(!utility.isLoggedin())
                {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login_msg", "Please Login to add items to cart");
                    response.sendRedirect("Login");
                    return;
                }
                HttpSession session=request.getSession();

                //get the order product details	on clicking submit the form will be passed to submitorder page

                String userName = session.getAttribute("username").toString();
                String orderTotal = request.getParameter("orderTotal");
                utility.printHtml("Header.html");
                utility.printHtml("LeftNavigationBar.html");
                pw.print("<form>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
                pw.print("<a style='font-size: 24px;'>Order</a>");
                pw.print("</h2><div class='entry'>");
                pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
                pw.print(userName);
                pw.print("</td></tr>");
                // for each order iterate and display the order name price
                for (OrderItem oi : utility.getCustomerOrders())
                {
                    pw.print("<tr><td> Product Purchased:</td><td>");
                    pw.print(oi.getName()+"</td></tr><tr><td>");
                    pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
                    pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
                    pw.print("Product Price:</td><td>"+ oi.getPrice());
                    pw.print("</td></tr>");
                }
                pw.print("<tr><td>");
                pw.print("Total Order Cost</td><td>"+orderTotal);
                pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
                pw.print("</td></tr></table><table><tr></tr><tr></tr>");
                pw.print("</form>");
                pw.print("<form method='post' action='Payment'>");
                pw.print("<tr><td>");
                pw.print("Credit/accountNo</td>");
                pw.print("<td><input type='text' name='creditCardNo'>");
                pw.print("</td></tr>");
                pw.print("<tr><td>");
                pw.print("Customer Name</td>");
                pw.print("<td><input type='text' name='name'>");
                pw.print("</td></tr>");
                pw.print("<tr><td>");
                pw.print("Stores</td>");
                pw.print("<td><input type='text' name='storeId'></td></tr>");
                pw.print("<ul>");
//                pw.print("<input type='radio' name='storeId'>1: 12 S Wells St, Chicago, IL, 60601");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='storeId'>2: 120 S King Dr, Chicago, IL, 60602");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60606: 400 W Madison St, Chicago, IL 60606");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60605: 1154 S Clark St, Chicago, IL 60605");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60642: 771 N Ogden Ave, Chicago, IL 60642");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60654: 121 W Kinzie St, Chicago, IL 60654");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60622: 2427 W Chicago Ave, Chicago, IL 60622");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60607: 1101 W Jackson Blvd, Chicago, IL 60607");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60604: 310 S Michigan Ave, Chicago, IL 60604");
//                pw.print("<br>");
//                pw.print("<input type='radio' name='zipcode'>60610: 1201 N State St, Chicago, IL 60610");

                HashMap<Integer,Stores> stores = new HashMap<>();
                stores = MySqlDataStoreUtilities.selectStores();
                for(Map.Entry<Integer,Stores> s: stores.entrySet()){
                    pw.print("<li>"+s.getValue().getStoreId()+": "+s.getValue().getStoreStreet()+", "+s.getValue().getStoreCity()+", "+s.getValue().getStoreState()+", "+s.getValue().getStoreZipcode()+"</li>");
                }

                pw.print("</ur>");
                pw.print("<tr><td colspan='2'>");
                pw.print("<input type='submit' name='submit' class='btnbuy'>");
                pw.print("</td></tr></table></form>");
                pw.print("</div></div></div>");
                utility.printHtml("Footer.html");
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
        }
    }


