import java.io.IOException;
import java.io.*;


/*
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating

	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
    String productName;
    String category;
    String price;
    String storeID;
    String storeZip;
    String storeCity;

    String storeState;
    String productOnSale;
    String makerName;
    String makerRebate;
    String userName;
    String userAge;
    String userJob;
    String reviewRating;

    String reviewDate;
    String reviewText;
    String gender;

    public Review (String productName,String category,String price,String storeID,String storeZip,String storeCity,
                   String storeState,String productOnSale,String makerName,String makerRebate,String userName,
                   String userAge,String gender,String userJob,String reviewRating,
                   String reviewDate,String reviewText){
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.userName = userName;
        this.userAge = userAge;
        this.userJob=userJob;
        this.storeCity = storeCity;
        this.storeID=storeID;
        this.storeState=storeState;
        this.storeZip=storeZip;
        this.productOnSale=productOnSale;
        this.makerName=makerName;
        this.makerRebate=makerRebate;
        this.reviewDate = reviewDate;
        this.reviewRating=reviewRating;
        this.reviewText=reviewText;
        this.gender=gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Review(String productName, String storeZip, String reviewRating, String reviewText) {
        this.productName = productName;
        this.storeZip=storeZip;
        this.reviewRating = reviewRating;
        this.reviewText = reviewText;
    }

    public String getProductName() {
        return productName;
    }
    public String getUserName() {
        return userName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public String getMakerName() {
        return makerName;
    }

    public String getMakerRebate() {
        return makerRebate;
    }

    public String getProductOnSale() {
        return productOnSale;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getStoreZip() {
        return storeZip;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public void setMakerRebate(String makerRebate) {
        this.makerRebate = makerRebate;
    }

    public void setReviewRating(String reviewRating) {
        this.reviewRating = reviewRating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductOnSale(String productOnSale) {
        this.productOnSale = productOnSale;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setStoreZip(String storeZip) {
        this.storeZip = storeZip;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReviewRating() {
        return reviewRating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
