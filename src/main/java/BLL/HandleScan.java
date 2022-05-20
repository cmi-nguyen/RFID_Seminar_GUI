package BLL;


import DAL.DAL_Bill;
import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;
import DTO.DTO_TagRead;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HandleScan {
    DTO_Bill order;
    BLL_ProductInstance instances;
    BLL_ProductLine productLine;
    BLL_TagRead tagRead;
    public String unknownTag;
    public HandleScan() throws Exception {
        order=new DTO_Bill();
        tagRead=new BLL_TagRead();
        instances =new BLL_ProductInstance();
        productLine=new BLL_ProductLine();
        unknownTag="";
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
        for (DTO_ProductInstance in: instances.getListProductIn()
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
    public boolean CheckTag(String tag){
        for (DTO_TagRead dtoT:tagRead.getListTag()
             ) {
            if(dtoT.getTagID().equals(tag)){
                return true;
            }
        }
        return false;
    }
    public DTO_ProductInstance FindInstance(String ID){
        for (DTO_ProductInstance dto:instances.getListProductIn()
             ) {
            if(ID.equals(dto.getProductInstanceID())){
                return dto;
            }
        }
        return null;
    }
    public DTO_Bill orderScan() throws Exception {
        unknownTag="";
        HandleScan hscan=new HandleScan();
        DAL_Bill bills=new DAL_Bill();
        order=new DTO_Bill();

        Read scanner=new Read();
        //read tag and find instance
        List<String> productInstance=new ArrayList<>();
        HashMap<String,String> productOrder=scanner.ReadTag();
        float total=0;
        for (Map.Entry<String,String> entry: productOrder.entrySet()){
               //check if that tag of store or not
            if(hscan.CheckTag(entry.getKey())==false){
                unknownTag+=entry.getKey()+" \n";
            }else {
                String instance="";
                //tag to instance to save in bill
                instance=hscan.FindInstancebyTag(entry.getKey());
                //add to list Bill
                productInstance.add(instance);
                //set total
                total+=hscan.FindProductByTag(entry.getKey()).getPrice();
                //set dates
            }

        }
        order.setProductInstance(productInstance);
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        //set build id
        Random rand=new Random();
        order.setBill_ID(Hash(String.valueOf(rand.nextInt(1,100)+millis)));
        order.setDate(date);
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
            DTO_ProductInstance insz=new DTO_ProductInstance();
            insz=FindInstance(productInstance);
            insz.setIsPurchased(1);
            instances.Update(insz);
        }
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static String Hash(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                str.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }
    public boolean refund(DTO_Bill billReturn){
        DTO_ProductLine tmp=new DTO_ProductLine();
        DTO_ProductInstance tmpIn=new DTO_ProductInstance();
        for (String ins:billReturn.getProductInstance()
             ) {

            tmp=FindProduct(ins);
            tmpIn=FindInstance(ins);
            if(tmpIn.getIsPurchased()==1){
                tmp.setStock(tmp.getStock()+1);
                productLine.Update(tmp);
                tmpIn.setIsPurchased(0);
                instances.Update(tmpIn);
                return true;
            }
        }
        return false;
    }

}
