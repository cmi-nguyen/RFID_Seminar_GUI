package DTO;

public class DTO_ProductLine {
    private String productLineID;
    private Float price;
    private String name;
    private int stock;
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



    public DTO_ProductLine(String productLineID, Float price, String name) {
        this.productLineID = productLineID;
        this.price = price;
        this.name = name;
    }
    public DTO_ProductLine(String productLineID, Float price, String name,int stock) {
        this.productLineID = productLineID;
        this.price = price;
        this.name = name;
        this.stock=stock;
    }
    public DTO_ProductLine() {
        productLineID="";
        price=0f;
        name="";
        stock=0;
    }

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
