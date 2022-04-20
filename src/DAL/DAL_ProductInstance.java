package DAL;

import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;

import java.sql.ResultSet;
import java.util.ArrayList;
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
            productInstances.add(productInstance);
        }
        myConnectUnit.Close();
        return productInstances;
    }
    public List<DTO_ProductInstance> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_ProductInstance> readDB() throws Exception {
        return readDB(null);
    }

}
