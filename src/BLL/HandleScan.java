package BLL;

import DTO.DTO_Bill;
import DTO.DTO_ProductInstance;
import DTO.DTO_ProductLine;
import DTO.DTO_TagRead;

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

    public static void main(String[] args) throws Exception {
        HandleScan scan=new HandleScan();
        System.out.println( scan.FindProductByTag("E2009150500902021860574C").getName());
    }
}
