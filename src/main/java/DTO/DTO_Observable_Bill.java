package DTO;

public class DTO_Observable_Bill {
    private String productInstanceID;
    private String productName;
    private String price;

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



    public DTO_Observable_Bill(String productInstanceID, String productName, String price) {
        this.productInstanceID = productInstanceID;
        this.productName = productName;
        this.price = price;

    }

}