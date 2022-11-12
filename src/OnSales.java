import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/OnSales")
public class OnSales extends HttpServlet {
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
        pw.print("<a style='font-size: 24px;'>Product On Sales</a>");
        pw.print("</h2><div class='entry'>");

        String productName = "";
        Double discount = 0.0;
        Double price=0.0;
        HashMap<String, Product> hm = new HashMap<>();
        hm = MySqlDataStoreUtilities.getProduct();
        pw.print("<table class='gridtable'>");
        pw.print("<tr><td>Product Name</td>");
        pw.print("<td>Discount</td>");
        pw.print("<td>Price</td></tr>");

        for (Map.Entry<String, Product> e : hm.entrySet()) {
            discount = e.getValue().getDiscount();
            if (discount >= 30.0) {
                pw.print("<tr>");
                productName = e.getValue().getName();
                price = e.getValue().getPrice();
                pw.print("<td>" + productName + "</td>");
                pw.print("<td>" + discount + "</td>");
                pw.print("<td>" + price + "</td>");
                pw.print("</tr>");
            }
        }
        pw.println("</table>");

        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }
}