package BLL;

import BLL.Read;
import DAL.DAL_Bill;
import DAL.DAL_ProductLine;
import DAL.DAL_TagRead;
import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;
import DTO.DTO_TagRead;
import com.caen.RFIDLibrary.CAENRFIDException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TestConnect {
    //this test of date input
//    public static void main(String[] args) throws Exception {
//        DAL_Bill dal=new DAL_Bill();
//        List<DTO_ProductLine> billstest=new ArrayList<>();
//        billstest.add(new DTO_ProductLine("32132332320","dauan",32f));
//        long millis=System.currentTimeMillis();
//        java.sql.Date date=new java.sql.Date(millis);
//        System.out.println(date);
//        dal.addBill(new DTO_Bill("321231we33w2ew31",date,32f,billstest));
//      List<DTO_Bill>  listBill = dal.readDB();
//    for(DTO_Bill bill: listBill){
//        System.out.println(bill.getBill_ID()+" "+bill.getDate());
//        }
//    }
//          test for handle scan
/*
//    public static void main(String[] args) throws Exception {
//        //test add build to data base when read tag
//        DAL_Bill bills=new DAL_Bill();
//        DTO_Bill bill=new DTO_Bill();
//        //set id
//        bill.setBill_ID("3232123211231");
//        //set total
//        bill.setTotal(3232f);
//        //
//        List<String> idinstance=new ArrayList<>();
//
//        //this make to test, acctualy this need findingProduct function
//        idinstance.add("1231231232131");
//        idinstance.add("weqewweq1");
//        bill.setProductInstance(idinstance);
//        //set date
//        long millis=System.currentTimeMillis();
//       java.sql.Date date=new java.sql.Date(millis);
//        bill.setDate(date);
//        bills.addBill(bill);
//    }
*/

    public static void main(String[] args) throws Exception {

    }
    // this test readTag function
    public static void TestintializeTag() throws CAENRFIDException {
       HashMap<String,String> listTag= Read.ReadTag();
        Set<String> keySet = listTag.keySet();
        for (String key : keySet) {
            System.out.println(key + " - " + listTag.get(key));
        }
    }

}
