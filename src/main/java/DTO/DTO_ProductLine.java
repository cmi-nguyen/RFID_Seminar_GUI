package DTO;

public class DTO_ProductLine {
    private String productLineID;
    private Float price;
    private String name;

    public DTO_ProductLine(String productLineID, Float price, String name) {
        this.productLineID = productLineID;
        this.price = price;
        this.name = name;
    }

    public DTO_ProductLine() {}

    public String getProductLineID() {
        return productLineID;
    }

    public void setProductLineID(String productLineID) {
        this.productLineID = productLineID;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float prince) {
        this.price = prince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
