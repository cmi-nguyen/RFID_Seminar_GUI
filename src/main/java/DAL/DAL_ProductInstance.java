package DAL;

import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAL_ProductInstance {
    MyConnectUnit myConnectUnit;
    String nameTable="productinstancerfid";

    /**
     * Lấy thông tin từ Database
     */
    public List<DTO_ProductInstance> readDB(String condition, String orderBy) throws Exception {
        myConnectUnit = new MyConnectUnit();

        ResultSet result = this.myConnectUnit.Select(nameTable,condition, orderBy);
        List<DTO_ProductInstance> productInstances=new ArrayList<>();
        while (result.next()) {
            DTO_ProductInstance productInstance=new DTO_ProductInstance();
            productInstance.setProductInstanceID(result.getString("product_instance_id"));
            productInstance.setProductLineID(result.getString("product_line_id"));
            productInstance.setIsPurchased(result.getInt("is_purchased"));
            productInstances.add(productInstance);
        }
        myConnectUnit.Close();
        return productInstances;
    }
    public Boolean update(DTO_ProductInstance productInstance) throws Exception {
        myConnectUnit = new MyConnectUnit();

        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("product_instance_id",productInstance.getProductInstanceID());
        insertValues.put("product_line_id",productInstance.getProductLineID());
        insertValues.put("is_purchased",productInstance.getIsPurchased());
        String condition = "product_instance_id = '" + productInstance.getProductInstanceID() + "'";
        Boolean check = myConnectUnit.Update(nameTable, insertValues, condition);
        myConnectUnit.Close();
        return check;
    }
    public List<DTO_ProductInstance> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_ProductInstance> readDB() throws Exception {
        return readDB(null);
    }

}
