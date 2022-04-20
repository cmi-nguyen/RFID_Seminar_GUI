package DAL;

import DTO.DTO_Bill;
import DTO.DTO_ProductLine;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAL_ProductLine {
    MyConnectUnit myConnectUnit;
    String nameTable="producline";

    /**
     * Lấy thông tin từ Database
     */
    public List<DTO_ProductLine> readDB(String condition, String orderBy) throws Exception {
        myConnectUnit = new MyConnectUnit();

        ResultSet result = this.myConnectUnit.Select(nameTable,condition, orderBy);
        List<DTO_ProductLine> productLines=new ArrayList<>();
        while (result.next()) {
            DTO_ProductLine productLine=new DTO_ProductLine();
            productLine.setProductLineID(result.getString("product_line_id"));
            productLine.setPrice(result.getFloat("price"));
            productLine.setName(result.getString("name"));
            productLines.add(productLine);
        }
        myConnectUnit.Close();
        return productLines;
    }
    public List<DTO_ProductLine> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_ProductLine> readDB() throws Exception {
        return readDB(null);
    }

}
