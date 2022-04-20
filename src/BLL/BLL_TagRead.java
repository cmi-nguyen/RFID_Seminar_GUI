package BLL;

import DAL.DAL_TagRead;
import DTO.DTO_TagRead;

import java.util.ArrayList;
import java.util.List;

public class BLL_TagRead {
    private List<DTO_TagRead> listTag;
    private DAL_TagRead dal;

    public List<DTO_TagRead> getListTag() {
        return listTag;
    }

    public void setListTag(List<DTO_TagRead> listProduct) {
        this.listTag = listProduct;
    }

    public BLL_TagRead() throws Exception {
        listTag=new ArrayList<>();
        dal=new DAL_TagRead();
        listTag=dal.readDB();
    }

}
