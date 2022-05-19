package DAL;



import DTO.DTO_ProductLine;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAL_ProductLine {
    MyConnectUnit myConnectUnit;
    String nameTable="producline";

    /**
     * Lấy thông tin từ Database
     */
    public List<DTO_ProductLine> readDB(String condition, String orderBy) throws Exception {
        myConnectUnit = new MyConnectUnit();

        ResultSet result = this.myConnectUnit.Select(nameTable, condition, orderBy);
        List<DTO_ProductLine> productLines = new ArrayList<>();
        while (result.next()) {
            DTO_ProductLine productLine = new DTO_ProductLine();
            productLine.setProductLineID(result.getString("product_line_id"));
            productLine.setPrice(result.getFloat("price"));
            productLine.setName(result.getString("name"));
            productLine.setStock(result.getInt("stock"));
            productLines.add(productLine);
        }
        myConnectUnit.Close();
        return productLines;
    }
    public Boolean update(DTO_ProductLine productLine) throws Exception {
        myConnectUnit = new MyConnectUnit();

        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("product_line_id",productLine.getProductLineID());
        insertValues.put("name",productLine.getName());
        insertValues.put("price",productLine.getPrice());
        insertValues.put("stock",productLine.getStock());
        String condition = " product_line_id = '" + productLine.getProductLineID() + "'";
        Boolean check = myConnectUnit.Update(nameTable, insertValues, condition);
        myConnectUnit.Close();
        return check;
    }
    public List<DTO_ProductLine> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_ProductLine> readDB() throws Exception {
        return readDB(null);
    }

}
