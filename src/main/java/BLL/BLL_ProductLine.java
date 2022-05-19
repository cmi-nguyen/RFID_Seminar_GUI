package BLL;

import DAL.DAL_ProductLine;
import DTO.DTO_ProductLine;


import java.util.ArrayList;
import java.util.List;

public class BLL_ProductLine {
   private List<DTO_ProductLine> listProduct;
    private DAL_ProductLine dal;

    public List<DTO_ProductLine> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<DTO_ProductLine> listProduct) {
        this.listProduct = listProduct;
    }
    public BLL_ProductLine() throws Exception {
       listProduct=new ArrayList<>();
        dal=new DAL_ProductLine();
        listProduct=dal.readDB();
    }

    public boolean Update(DTO_ProductLine productLine) {
        try {
            if(dal.update(productLine)){
                for (DTO_ProductLine dto: listProduct) {
                    if(dto.getProductLineID().equals(productLine.getProductLineID())){
                        dto.setProductLineID(productLine.getProductLineID());
                        dto.setName(productLine.getName());
                        dto.setStock(productLine.getStock());
                        dto.setPrice(productLine.getPrice());
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
}
