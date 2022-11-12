import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@WebServlet("/DailyTransactions")
public class DailyTransactions extends HttpServlet {
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
        pw.print("<a style='font-size: 24px;'>Daily Transactions</a>");
        pw.print("</h2><div class='entry'>");
        Date date;
        Double total = 0.0;

        HashMap<String, Transactions> hm = new HashMap<>();
        HashMap<Date, Double> dt = new HashMap<>();
        hm = MySqlDataStoreUtilities.getTransaction();
        pw.print("<table class='gridtable'>");
        pw.print("<tr><td>Purchase Date</td>");
        pw.print("<td>Total Sales</td></tr>");

        for (Map.Entry<String, Transactions> e : hm.entrySet()) {
            if (!dt.containsKey(e.getValue().getPurchaseDate())) {
                dt.put(e.getValue().getPurchaseDate(), e.getValue().getPrice());
                continue;
            }
            dt.put(e.getValue().getPurchaseDate(), dt.get(e.getValue().getPurchaseDate()) + e.getValue().getPrice());
        }

        for (Map.Entry<Date,Double> c : dt.entrySet()) {
            pw.print("<tr>");
            date = c.getKey();
            pw.print("<td>" + date + "</td>");
            total = c.getValue();
            String  str = String.format("%.2f",total);
            pw.print("<td>" + Double.parseDouble(str) + "</td></tr>");
        }
            pw.println("</table>");

            pw.print("</div></div></div>");
            utility.printHtml("Footer.html");
        }
    }


