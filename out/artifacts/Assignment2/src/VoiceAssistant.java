import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


@WebServlet("/VoiceAssistant")

/* 
	VoiceAssistant class contains class variables name,price,image,retailer,condition,discount.

	VoiceAssistant class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	VoiceAssistant class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class VoiceAssistant extends HttpServlet {
	private String id;
	private String name;
	private double price;
	public String description;
	private String retailer;
	private String condition;
	private double discount;
	private String rebate;
	private int inventory;
	public VoiceAssistant(String name, double price,  String retailer, String condition, double discount,String rebate,int inventory){
		this.name=name;
		this.price=price;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
		this.rebate =rebate;
		this.inventory = inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}

	public String getRebate() {
		return rebate;
	}

	public int getInventory() {
		return inventory;
	}

	public VoiceAssistant() {
		
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
