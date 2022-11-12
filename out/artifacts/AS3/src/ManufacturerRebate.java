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
@WebServlet("/ManufacturerRebate")
public class ManufacturerRebate extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);

            if (!utility.isLoggedin()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("login_msg", "Please Login to Review the Inventory");
                response.sendRedirect("Login");
                return;
            }
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Manufacturer Rebate</a>");
            pw.print("</h2><div class='entry'>");
            String productName = "";
            Double price=0.0;
            String maker = "";
            String rebate = "";
            HashMap<String, Product> hm = new HashMap<>();
            hm = MySqlDataStoreUtilities.getProduct();
            pw.print("<table class='gridtable'>");
            pw.print("<tr><td>Product Name</td>");
            pw.print("<td>Price</td>");
            pw.print("<td>Manufacturer</td>");
            pw.print("<td>Rebate</td></tr>");


            for (Map.Entry<String, Product> e : hm.entrySet()) {
                rebate = e.getValue().getRebate();
                if (rebate.equals("Yes")) {
                    pw.print("<tr>");
                    productName = e.getValue().getName();
                    price = e.getValue().getPrice();
                    maker = e.getValue().getRetailer();
                    pw.print("<td>" + productName + "</td>");
                    pw.print("<td>" + price + "</td>");
                    pw.print("<td>" + maker + "</td>");
                    pw.print("<td>" + rebate + "</td>");
                    pw.print("</tr>");
                }
            }
            pw.println("</table>");

            pw.print("</div></div></div>");
            utility.printHtml("Footer.html");
        }
}

