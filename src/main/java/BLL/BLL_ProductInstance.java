package BLL;

import DAL.DAL_ProductInstance;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;

import java.util.ArrayList;
import java.util.List;

public class BLL_ProductInstance {
    public List<DTO_ProductInstance> getListProductIn() {
        return listProductIn;
    }

    public void setListProductIn(List<DTO_ProductInstance> listProductIn) {
        this.listProductIn = listProductIn;
    }

    private List<DTO_ProductInstance> listProductIn;
    private DAL_ProductInstance dal;


    public BLL_ProductInstance() throws Exception {
        listProductIn=new ArrayList<>();
        dal=new DAL_ProductInstance();
        listProductIn=dal.readDB();
    }

    public boolean Update(DTO_ProductInstance productIns) {
        try {
            if(dal.update(productIns)){
                for (DTO_ProductInstance instance: listProductIn) {
                    if(instance.getProductInstanceID().equals(productIns.getProductInstanceID())){
                        instance.setProductInstanceID(productIns.getProductInstanceID());
                        instance.setProductLineID(productIns.getProductLineID());
                        instance.setIsPurchased(productIns.getIsPurchased());
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
