package DTO;

public class DTO_Observable_Bill {
    private String productInstanceID;
    private String productName;
    private String price;
    private String date;

    public String getProductInstanceID() {
        return productInstanceID;
    }

    public void setProductInstanceID(String productInstanceID) {
        this.productInstanceID = productInstanceID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DTO_Observable_Bill(String productInstanceID, String productName, String price, String date) {
        this.productInstanceID = productInstanceID;
        this.productName = productName;
        this.price = price;
        this.date = date;
    }

}
