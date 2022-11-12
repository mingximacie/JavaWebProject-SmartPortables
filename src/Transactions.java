import java.sql.Date;

public class Transactions {
    int orderId;
    java.sql.Date purchaseDate;
    String productId;
    double price;

    public Transactions(int orderId,java.sql.Date purchaseDate,String productId,double price){
        this.orderId=orderId;
        this.productId=productId;
        this.price=price;
        this.purchaseDate = purchaseDate;

    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
