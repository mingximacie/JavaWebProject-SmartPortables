import java.util.*;
import java.util.Map;



public class Product {
    private String id;
    private String name;
    private double price;
    private String retailer;
    private String condition;
    private String type;
    private double discount;
    private String rebate;
    private int inventory;
    HashMap<String,String> accessories;

    public Product(String name,int inventory){
        this.inventory = inventory;
        this.name = name;

    }
    public Product(String type,String id,String name, double price, String retailer,String condition,double discount,String rebate, int inventory){
        this.id=id;
        this.name=name;
        this.price=price;
        this.retailer = retailer;
        this.condition=condition;
        this.type=type;
        this.discount = discount;
        this.rebate = rebate;
        this.inventory = inventory;
        this.accessories=new HashMap<String,String>();
    }

    HashMap<String,String> getAccessories() {
        return accessories;
    }



    public int getInventory() {
        return inventory;
    }

    public String getRebate() {
        return rebate;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type =type;
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

    public void setAccessories( HashMap<String,String> accessories) {
        this.accessories = accessories;
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

}
