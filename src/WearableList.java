import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableList")

public class WearableList extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
        



		HashMap<String, Wearable> hm = new HashMap<String, Wearable>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.wearables);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("fitness"))
		   {
			 for(Map.Entry<String, Wearable> entry : SaxParserDataStore.wearables.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Xiaomi"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Fitness Watches";
		   }
		   else if(CategoryName.equals("smart"))
		    {
			for(Map.Entry<String, Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Apple")||entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Smart Watches";
			}
			else if(CategoryName.equals("headphone"))
			{
				for(Map.Entry<String, Wearable> entry : SaxParserDataStore.wearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Bose"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Headphones";
			}else if(CategoryName.equals("virtualreality"))
		   {
			   for(Map.Entry<String, Wearable> entry : SaxParserDataStore.wearables.entrySet())
			   {
				   if(entry.getValue().getRetailer().equals("Meta"))
				   {
					   hm.put(entry.getValue().getId(),entry.getValue());
				   }
			   }
			   name = "Virtual Reality";
		   }
		   else if(CategoryName.equals("petracker"))
		   {
			   for(Map.Entry<String, Wearable> entry : SaxParserDataStore.wearables.entrySet())
			   {
				   if(entry.getValue().getRetailer().equals("Petfon"))
				   {
					   hm.put(entry.getValue().getId(),entry.getValue());
				   }
			   }
			   name = "Pet Tracker";
		   }
		}


		


		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+"</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Wearable> entry : hm.entrySet())
		{
			Wearable wearable = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+ wearable.getName()+"</h3>");
			pw.print("<strong>$"+ wearable.getPrice()+"</strong><ul>");
			pw.print("<li><em>"+ wearable.getDescription()+"</em></li>");
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+
					"<input type='hidden' name='name' value='"+wearable.getName()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+wearable.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+
					"<input type='hidden' name='name' value='"+wearable.getName()+"'>"+
					"<input type='hidden' name='type' value='wearables'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
