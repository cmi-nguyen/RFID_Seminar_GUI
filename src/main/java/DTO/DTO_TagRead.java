package DTO;

import java.sql.Date;

public class DTO_TagRead {
    private String tagID;
    private String productInstance;
    private Date time;

    public DTO_TagRead(String tagID, String productInstance, Date time) {
        this.tagID = tagID;
        this.productInstance = productInstance;
        this.time = time;
    }

    public DTO_TagRead() {
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public String getProductInstance() {
        return productInstance;
    }

    public void setProductInstance(String productInstance) {
        this.productInstance = productInstance;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
