public class Stores {
    private int storeId;
    private String storeStreet;
    private String storeCity;
    private String storeState;
    private String storeZipcode;
    public Stores( int storeId,String storeStreet,String storeCity,String storeState,String storeZipcode){
        this.storeId = storeId;
        this.storeStreet = storeStreet;
        this.storeCity = storeCity;
        this.storeState = storeState;
        this.storeZipcode = storeZipcode;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public String getStoreZipcode() {
        return storeZipcode;
    }

    public void setStoreZipcode(String storeZipcode) {
        this.storeZipcode = storeZipcode;
    }
}
