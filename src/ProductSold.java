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

@WebServlet("/ProductSold")

public class ProductSold extends HttpServlet {
        ArrayList<ProductSales> ps = new ArrayList<>();

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        ArrayList<prod> product = new ArrayList<>();
//
//        try {
//            product = MySqlDataStoreUtilities.selectInventoryForChart();
//        } catch (Exception e) {
//
//        }
        String productJson = new Gson().toJson(ps);
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
        pw.print("<a style='font-size: 24px;'>Product Sales Report</a>");
        pw.print("</h2><div class='entry'>");

        ProductSales(request, response);
        SaleBarChart(request,response);


        pw.print("</div></div></div>");

        utility.printHtml("Footer.html");
    }

    protected void ProductSales(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String productId = "";
        Double price = 0.0;
        int soldNum = 0;
        Double total = 0.0;

        HashMap<String, Transactions> hm = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();
        hm = MySqlDataStoreUtilities.getTransaction();
        pw.print("<table class='gridtable'>");
        pw.print("<tr><td>Product Name</td>");
        pw.print("<td>Price</td>");
        pw.print("<td>Number of Sold</td>");
        pw.print("<td>Total Sales</td></tr>");

        for (Map.Entry<String, Transactions> e : hm.entrySet()) {
            if(!count.containsKey(e.getValue().getProductId())){
                count.put(e.getValue().getProductId(),1);
                continue;
            }
            count.put(e.getValue().getProductId(),count.get(e.getValue().getProductId())+1);
        }


        for(Map.Entry<String, Integer> c: count.entrySet()) {
            pw.print("<tr>");
            productId = c.getKey();
            pw.print("<td>" + productId + "</td>");

            for (Map.Entry<String, Transactions> e : hm.entrySet()) {
                if(productId.equals(e.getValue().getProductId())){
                    price = e.getValue().getPrice();
                    pw.print("<td>" + price + "</td>");
                    break;
                }
            }
            soldNum = c.getValue();
            pw.print("<td>" + soldNum + "</td>");
            total = price*soldNum;
            pw.print("<td>" + total + "</td></tr>");
            ProductSales pss = new ProductSales(c.getKey(),total);
            ps.add(pss);
        }
            pw.println("</table>");

    }


    protected void SaleBarChart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.print("<br/><h1>Sales Bar Chart</h1>");
        pw.print("<div class='post'><h4><button id='btnGetChartData'>View Bar Chart</h4>");
        pw.print("<div id='chart_div'></div></div>");
        pw.print("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.print("<script type='text/javascript' src='SalesData.js'></script>");
    }

}
