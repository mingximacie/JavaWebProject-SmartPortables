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

@WebServlet("/InventoryTable")

public class InventoryTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<prod> product = new ArrayList<>();

        try {
            product = MySqlDataStoreUtilities.selectInventoryForChart();
        } catch (Exception e) {

        }
        String productJson = new Gson().toJson(product);
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(productJson);

    }
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

        ProductInventory(request, response);
        InventoryBarChart(request,response);


        pw.print("</div></div></div>");

        utility.printHtml("Footer.html");
    }

    protected void ProductInventory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String productName = "";
        Double price = 0.0;
        int inven = 0;
        HashMap<String, Product> hm = new HashMap<>();
        hm = MySqlDataStoreUtilities.getProduct();
        pw.print("<table class='gridtable'>");
        pw.print("<tr><td>Product Name</td>");
        pw.print("<td>Price</td>");
        pw.print("<td>Inventory</td></tr>");
        for (Map.Entry<String, Product> e : hm.entrySet()) {

            pw.print("<tr>");
            productName = e.getValue().getName();
            pw.print("<td>" + productName + "</td>");
            price = e.getValue().getPrice();
            pw.print("<td>" + price + "</td>");
            inven = e.getValue().getInventory();
            pw.print("<td>" + inven + "</td>");
            pw.print("</tr>");

        }
        pw.println("</table>");

    }
    protected void InventoryBarChart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.print("<br/><h1>Inventory Bar Chart</h1>");
        pw.print("<div class='post'><h4><button id='btnGetChartData'>View Bar Chart</h4>");
        pw.print("<div id='chart_div'></div></div>");
        pw.print("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.print("<script type='text/javascript' src='InventoryData.js'></script>");
    }

}
