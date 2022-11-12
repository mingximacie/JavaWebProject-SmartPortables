import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@WebServlet("/SalesReport")
    public class SalesReport extends HttpServlet {
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
            pw.print("<a style='font-size: 24px;'>Sales Report</a>");
            pw.print("</h2><div class='entry'>");
            pw.print("<form name ='ProductSold' action='ProductSold' method='get'>");
            pw.print("<table><tr><td><input type='submit' name='ProductSold' value='Product Sold' class='btnbuy'></td></tr></table></form>");
            pw.print("<form name ='DailyTransactions' action='DailyTransactions' method='get'>");
            pw.print("<table><tr><td><input type='submit' name='DailyTransactions' value='Daily Transactions' class='btnbuy'></td></tr></table></form>");


            pw.print("</div></div></div>");

            utility.printHtml("Footer.html");
        }
}
