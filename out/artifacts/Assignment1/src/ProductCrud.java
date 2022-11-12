import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");

        String msg = "good";
        String producttype = "", productId = "", productName = "", productImage = "", productManufacturer = "", productCondition = "", prod = "";
        double productPrice = 0.0, productDiscount = 0.0;
        HashMap<String, Wearable> allwearable = new HashMap<String, Wearable>();
        HashMap<String, Laptops> alllaptop = new HashMap<String, Laptops>();
        HashMap<String, Phones> allphone = new HashMap<String, Phones>();
        HashMap<String, VoiceAssistant> allvoice = new HashMap<String, VoiceAssistant>();
        if (action.equals("add") || action.equals("update")) {
            producttype = request.getParameter("producttype");
            productId = request.getParameter("productId");
            productName = request.getParameter("productName");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));

        } else {
            productId = request.getParameter("productId");
        }
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if (action.equals("add")) {
            if (producttype.equals("wearables")) {
                allwearable = MySqlDataStoreUtilities.getWearables();
                if (allwearable.containsKey(productId)) {
                    msg = "Product already available";

                }
            } else if (producttype.equals("phones")) {
                allphone = MySqlDataStoreUtilities.getPhoness();
                if (allphone.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("laptops")) {
                alllaptop = MySqlDataStoreUtilities.getLaptopss();
                if (alllaptop.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else if (producttype.equals("voices")) {
                allvoice = MySqlDataStoreUtilities.getVoice();
                if (allvoice.containsKey(productId)) {
                    msg = "Product already available";
                }
            } else {
                msg = "Please add the prodcut name";
            }

             if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addproducts(producttype, productId, productName, productPrice, productManufacturer, productCondition, productDiscount);
                } catch (Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        } else if(action.equals("update")){

            if(producttype.equals("wearables")){
                allwearable = MySqlDataStoreUtilities.getWearables();
                if(!allwearable.containsKey(productId)){
                    msg = "Product not available";
                }

            }else if(producttype.equals("phones"))
            {
                allphone = MySqlDataStoreUtilities.getPhoness();
                if(!allphone.containsKey(productId)){
                    msg = "Product not available";
                }
            }else if (producttype.equals("laptops"))
            {
                alllaptop = MySqlDataStoreUtilities.getLaptopss();
                if(!alllaptop.containsKey(productId)){
                    msg = "Product not available";
                }
            }else if (producttype.equals("voice")) {
                allvoice = MySqlDataStoreUtilities.getVoice();
                if(!allvoice.containsKey(productId)){
                    msg = "Product not available";
                }
            }

            if (msg.equals("good"))
            {

                try
                {
                    msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productManufacturer,productCondition,productDiscount);
                }
                catch(Exception e)
                {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }
        }else if(action.equals("delete"))
        {
            msg = "bad";
            allwearable = MySqlDataStoreUtilities.getWearables();
            if(allwearable.containsKey(productId)){
                msg = "good";
            }

            allphone = MySqlDataStoreUtilities.getPhoness();
            if(allphone.containsKey(productId)){
                msg = "good";
            }

            alllaptop = MySqlDataStoreUtilities.getLaptopss();
            if(alllaptop.containsKey(productId)){
                msg = "good";
            }

            allvoice= MySqlDataStoreUtilities.getVoice();
            if(allvoice.containsKey(productId)){
                msg = "good";
            }

            if (msg.equals("good"))
            {

                try
                {

                    msg = MySqlDataStoreUtilities.deleteproducts(productId);
                }
                catch(Exception e)
                {
                    msg = "Product cannot be deleted";
                }
                msg = "Product has been successfully deleted";
            }else{
                msg = "Product not available";
            }
        }

        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>"+msg+"</h4>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");

    }
}