package BLL;


import DAL.DAL_Bill;
import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;
import DTO.DTO_TagRead;

import java.util.*;

public class HandleScan {
    DTO_Bill order;
    BLL_ProductInstance instance;
    BLL_ProductLine productLine;
    BLL_TagRead tagRead;
    public HandleScan() throws Exception {
        order=new DTO_Bill();
        tagRead=new BLL_TagRead();
        instance=new BLL_ProductInstance();
        productLine=new BLL_ProductLine();
    }
    public String FindInstancebyTag(String tag){
        for (DTO_TagRead t:tagRead.getListTag()
             ) {
            if (t.getTagID().equals(tag)){
                return t.getProductInstance();
            }
        }
        return null;
    }
    public void SaveBill(DTO_Bill order) throws Exception {
        DAL_Bill bill= new DAL_Bill();
        bill.addBill(order);
    }
    public DTO_ProductLine FindProduct(String InstanceID){
       // String in=FindProductByInstance(InstanceID);
        String proID=FindProductByInstance(InstanceID);
        for (DTO_ProductLine line: productLine.getListProduct()
        ) {
            if(line.getProductLineID().equals(proID)){
                return line;
            }
        }
        return null;
    }
    public String FindProductByInstance(String InstanceID){
        for (DTO_ProductInstance in: instance.getListProductIn()
             ) {
            if(in.getProductInstanceID().equals(InstanceID)){
                return in.getProductLineID();
            }
        }
        return null;
    }
    /*
    * find product by tag and return Product
    * */
    public DTO_ProductLine FindProductByTag(String Tag){
        String in=FindInstancebyTag(Tag);
        String proID=FindProductByInstance(in);
        for (DTO_ProductLine line: productLine.getListProduct()
        ) {
            if(line.getProductLineID().equals(proID)){
                return line;
            }
        }
        return null;
    }

    public DTO_Bill orderScan() throws Exception {
        HandleScan hscan=new HandleScan();
        DAL_Bill bills=new DAL_Bill();
        order=new DTO_Bill();

        Read scanner=new Read();
        //read tag and find instance
        List<String> productInstance=new ArrayList<>();
        HashMap<String,String> productOrder=scanner.ReadTag();
        float total=0;
        for (Map.Entry<String,String> entry: productOrder.entrySet()){
            String instance="";
            //tag to instance to save in bill
            instance=hscan.FindInstancebyTag(entry.getKey());
            //add to list Bill

            productInstance.add(instance);
            order.setProductInstance(productInstance);
            //set build id
            Random rand=new Random();
            order.setBill_ID(String.valueOf(rand.nextInt(1,100000)));
            //set total
            total+=hscan.FindProductByTag(entry.getKey()).getPrice();
            //set dates
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            order.setDate(date);



        }
        order.setTotal(total);
        //bills.addBill(order);
        return  order;
    }
    public void saveOrder(DTO_Bill order) throws Exception {
        DAL_Bill bill=new DAL_Bill();
        bill.addBill(order);
        for (String productInstance: order.getProductInstance()
        ) {
            DTO_ProductLine product=new DTO_ProductLine();
            product=FindProduct(productInstance);
            product.setStock(product.getStock()-1);
            System.out.println(product.getStock());
            productLine.Update(product);

        }
    }


}
