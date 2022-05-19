package DAL;
import BLL.BLL_ProductLine;
import BLL.HandleScan;
import DTO.DTO_Bill;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PdfTool {
    public static void main(String[] args) {
        DAL_Bill dalBill=new DAL_Bill();
        try {
            HandleMess(dalBill.readDB().get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void HandleMess( DTO_Bill bill){
        try{
                List<String> sss = new ArrayList<>();
                sss.add("HOA DON");
                sss.add(bill.getBill_ID());
                sss.add("Date               ToTal");//phen qua men:)
                sss.add(bill.getDate() + "    " + bill.getTotal());
                sss.add("===================================================");
                sss.add("Product name    "+"Price");
                for (String s : bill.getProductInstance()
                ) {
                    HandleScan handleScan = new HandleScan();
                    String c = handleScan.FindProductByInstance(s);
                    String d= String.valueOf(handleScan.FindProduct(s).getPrice());
                    sss.add(c+"       "+d);
                }
                WritePDF(sss);
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public static void WritePDF(List<String> splitText) throws Exception {

            try (PDDocument doc = new PDDocument();) {
                PDPage page = new PDPage();
                doc.addPage(page);
                PDFont font = PDType1Font.HELVETICA;
                PDPageContentStream content = new PDPageContentStream(doc, page);
                content.setFont(font, 12);
                int lines = 1;
                for (String sentence : splitText) {
                    if (sentence.isEmpty()) {
                        continue;
                    }
                    content.beginText();
                    content.newLineAtOffset(100, 700 - 20f * lines);
                    content.showText(sentence);
                    content.endText();
                    ++lines;
                }
                content.close();
                doc.save("C:\\Users\\pc\\Desktop\\nameoffile.pdf");
                System.out.print("Pages" + doc.getNumberOfPages());
            } catch (Exception e) {
                System.out.println(e);
            }

    }
}

