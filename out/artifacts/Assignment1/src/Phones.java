import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/Phones")

/* 
	Phones class contains class variables name,price,image,retailer,condition,discount.

	Phones class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Phones class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class Phones extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String description;
	private String retailer;
	private String condition;
	private double discount;
	
	public Phones( String name, double price, String retailer, String condition, double discount){

		this.name=name;
		this.price=price;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
	}
	
	public Phones(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
