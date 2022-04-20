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
}
