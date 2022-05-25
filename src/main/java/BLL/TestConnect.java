package BLL;

import DAL.DAL_Bill;
import DAL.DAL_ProductInstance;
import DAL.DAL_ProductLine;
import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;
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


    public static void main(String[] args) throws Exception {
       HandleScan hscan=new HandleScan();
       DTO_Bill order =new DTO_Bill();
       List<String> dds=new ArrayList<>();
       DTO_ProductInstance ins=new DTO_ProductInstance();
       dds.add("312312312312");
       order.setProductInstance(dds);
        System.out.println(hscan.refund(order)+"is not pay"); ;
    }
}