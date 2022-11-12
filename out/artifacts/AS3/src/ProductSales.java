public class ProductSales {
    String productName;
    Double total_sales;
    ProductSales(String productName,Double total_sales){
        this.total_sales = total_sales;
        this.productName = productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getTotal_sales() {
        return total_sales;
    }

    public String getProductName() {
        return productName;
    }

    public void setTotal_sales(Double total_sales) {
        this.total_sales = total_sales;
    }
}
