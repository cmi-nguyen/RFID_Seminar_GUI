package DTO;

public class DTO_ProductInstance {
    private String productInstanceID;
    private String productLineID;

    public DTO_ProductInstance() {
    }

    public DTO_ProductInstance(String productInstanceID, String productLineID) {
        this.productInstanceID = productInstanceID;
        this.productLineID = productLineID;
    }

    public String getProductInstanceID() {
        return productInstanceID;
    }

    public void setProductInstanceID(String productInstanceID) {
        this.productInstanceID = productInstanceID;
    }

    public String getProductLineID() {
        return productLineID;
    }

    public void setProductLineID(String productLineID) {
        this.productLineID = productLineID;
    }
}
