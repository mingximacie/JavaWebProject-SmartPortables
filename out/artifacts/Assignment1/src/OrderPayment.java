import java.io.IOException;
import java.io.*;
import java.util.Date;


/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	String userName;
	String customerName;
	String customerAddress;
	String creditCardNo;
	int orderId;
	java.sql.Date purchaseDate;
	String productId;
	String category;
	String quantity;
	double orderPrice;
	int storeId;
	String storeAddress;
	String zipcode;
	
	public OrderPayment(String userName,String customerName,String customerAddress,String creditCardNo,
						int orderId,java.sql.Date purchaseDate,String productId,String category,String quantity, double orderPrice,
						int storeId,String storeAddress,String zipcode){
		this.userName = userName;
		this.orderId=orderId;
		this.productId=productId;
	 	this.orderPrice=orderPrice;
		this.customerAddress = customerAddress;
		this.customerName = customerName;
	 	this.creditCardNo=creditCardNo;
		 this.purchaseDate = purchaseDate;
	 	this.category = category;
	 	this.quantity = quantity;
	 	this.storeAddress = storeAddress;
	 	this.storeId = storeId;
		 this.zipcode=zipcode;
		}


//	public void setStoreId(int storeId) {
//		this.storeId = storeId;
//	}
//
//	public int getStoreId() {
//		return storeId;
//	}

	public java.sql.Date getPurchaseDate() {
		return purchaseDate;
	}

	public String getCategory() {
		return category;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getProductId() {
		return productId;
	}
	public String getQuantity() {
		return quantity;
	}


	public String getStoreAddress() {
		return storeAddress;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setPurchaseDate(java.sql.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
