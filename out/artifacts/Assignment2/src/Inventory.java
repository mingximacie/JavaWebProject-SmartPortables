import com.google.gson.Gson;
import com.mongodb.DBCollection;

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

@WebServlet("/Inventory")
public class Inventory extends HttpServlet {
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
        pw.print("<a style='font-size: 24px;'>Inventory Data</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<form name ='InventoryTable' action='InventoryTable' method='get'>");
        pw.print("<table><tr><td><input type='submit' name='InventoryTable' value='Check Inventory' class='btnbuy'></td></tr></table></form>");
        pw.print("<form name ='OnSales' action='OnSales' method='get'>");
        pw.print("<table><tr><td><input type='submit' name='OnSale' value='Product on Sales' class='btnbuy'></td></tr></table></form>");
        pw.print("<form name ='ManufacturerRebate' action='ManufacturerRebate' method='get'>");
        pw.print("<table><tr><td><input type='submit' name='ManufacturerRebate' value='Product with Rebate' class='btnbuy'></td></tr></table></form>");

        pw.print("</div></div></div>");

        utility.printHtml("Footer.html");
    }
}