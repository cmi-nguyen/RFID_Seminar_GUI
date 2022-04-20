package DAL;

import DTO.DTO_ProductLine;
import DTO.DTO_TagRead;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAL_TagRead {
    MyConnectUnit myConnectUnit;
    String namTable="tagread";

    /**
     *Them tag vao database. Hien tai cai nay kha kho dung vs ko can nen ko can de y
     */
    public  boolean addTag(DTO_TagRead tag) throws Exception {
        myConnectUnit =new MyConnectUnit();
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("tad_read_ID",tag.getTagID());
        insertValues.put("product_instance_id",tag.getProductInstance());
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        insertValues.put("time",date);
        Boolean check = myConnectUnit.Insert(namTable, insertValues);

        myConnectUnit.Close();
        return check;
    }

    //read database and return lis of tag read
    public List<DTO_TagRead> readDB(String condition, String orderBy) throws Exception {
        myConnectUnit = new MyConnectUnit();

        ResultSet result = this.myConnectUnit.Select(namTable,condition, orderBy);
        List<DTO_TagRead> Tags=new ArrayList<>();
        while (result.next()) {
            DTO_TagRead tag =new DTO_TagRead();
            tag.setTagID(result.getString("tad_read_id"));
            tag.setTime(result.getDate("time"));
            tag.setProductInstance((result.getString("product_instance_id")));
            Tags.add(tag);
        }
        myConnectUnit.Close();
        return Tags;
    }
    public List<DTO_TagRead> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_TagRead> readDB() throws Exception {
        return readDB(null);
    }
}
