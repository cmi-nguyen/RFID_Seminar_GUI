package DAL;



import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAL_Bill {
    MyConnectUnit myConnectUnit;
    String tableName="bill";


    /**
     * Lấy thông tin từ Database
     */
    public List<DTO_Bill> readDB(String condition, String orderBy) throws Exception {
        myConnectUnit = new MyConnectUnit();

        ResultSet result = this.myConnectUnit.Select(tableName,condition, orderBy);

        List<DTO_Bill> DTOs = new ArrayList<>();
        myConnectUnit = new MyConnectUnit();
        while (result.next()) {
            DTO_Bill bill = new DTO_Bill();
            bill.setBill_ID(result.getString("bill_id"));
            bill.setDate(result.getDate("date"));
            bill.setTotal(result.getFloat("total"));

            ResultSet instantRes=this.myConnectUnit.Select("billdetails","bill_id ="+bill.getBill_ID());
            List<String> listIn=new ArrayList<>();
                while (instantRes.next()){
                    listIn.add(instantRes.getString("product_instance_id"));
                }
                bill.setProductInstance(listIn);
            DTOs.add(bill);
        }
        myConnectUnit.Close();
        return DTOs;
    }
    public List<DTO_Bill> readDB(String condition) throws Exception {
        return readDB(condition,null);
    }
    public  List<DTO_Bill> readDB() throws Exception {
        return readDB(null);
    }
    /**
     * Them bill xuong database
     */
    public Boolean addBill(DTO_Bill bill) throws Exception {
        myConnectUnit = new MyConnectUnit();
        HashMap<String,Object> insertFroreig=new HashMap<>();
        // tạo đối tượng truyền vào
        HashMap<String, Object> insertValues = new HashMap<>();
        insertValues.put("bill_id",bill.getBill_ID());
        insertValues.put("date",bill.getDate());
        insertValues.put("total",bill.getTotal());
        Boolean check = myConnectUnit.Insert(tableName, insertValues);
        //todo bill detail has list product
        for (String ProductLine: bill.getProductInstance()
             ) {

            insertFroreig.put("bill_id",bill.getBill_ID());
            insertFroreig.put("product_instance_id",ProductLine);
            Boolean checkK = myConnectUnit.Insert("billdetails", insertFroreig);
        }


        myConnectUnit.Close();
        return check;
    }
}
