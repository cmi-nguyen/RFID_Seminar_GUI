package UI;

import BLL.HandleScan;
import DTO.DTO_Bill;
import DTO.DTO_Observable_Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    public Label total;
    public Label date_value;
    public Label BILL_ID;
    public TableColumn<DTO_Observable_Bill, String> idCol; // idCol is Column id in XML file
    public TableColumn<DTO_Observable_Bill, String> nameCol;
    public TableColumn<DTO_Observable_Bill, String> priceCol;
    public TableView<DTO_Observable_Bill> table;
    public ObservableList<DTO_Observable_Bill>data;

    // controller
    public void connect_btn_func (){

        System.out.println("Connect desktop reader");

        // this.table.setItems();
        // this.total.setText("Nguyn");
    }

    public void scan_btn_func()  {
        System.out.println("Scan with desktop reader"); // debug code
        // code

        // inform data as ObservableList

       // HandleScan scannedData = new HandleScan();
       // DTO_Bill bill = scannedData.orderScan();
        Date today = new Date(2022,4,26);
        List<String>list = new ArrayList<>();
        list.add("Huaweiiii");
        DTO_Bill bill = new DTO_Bill("new bill",today,100f,list);


        data = FXCollections.observableArrayList(
                new DTO_Observable_Bill("test","test","tét")
        );

        // column constructor inside () is the column name
        idCol.setCellValueFactory(
                new PropertyValueFactory<DTO_Observable_Bill ,String>("productInstanceID")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<DTO_Observable_Bill ,String>("productName")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<DTO_Observable_Bill ,String>("price")
        );

        // add ObservableList to Table
        table.setItems(data);

        table.getItems().add(new DTO_Observable_Bill("1","2","3"));
        table.getItems().add(new DTO_Observable_Bill(String.valueOf(bill.getProductInstance().get(0)),
            "Huawei y2", "45"  ));
        table.setItems(data);
        // set label value
        BILL_ID.setText(bill.getBill_ID());
        total.setText(String.valueOf(bill.getTotal()));
        date_value.setText(String.valueOf(today));
    }

    public void accept_btn_func (){
        System.out.println("accept and save order"); // debug code
        // code
    }

    public void delete_item_func (){
        System.out.println("Delete item in order "); // debug code
        //code
        if (data==null){
            System.out.println("Data is Null");
        }
        else {
            DTO_Observable_Bill selected = table.getSelectionModel().getSelectedItem();
            data.remove(selected);
        }

        // reset label


    }

    public void cancel_order_func (){
        System.out.println("Cancel Order");

        // code
        if (data==null){
            System.out.println("data is empty");
        }
        else {
            int len = data.toArray().length;
            for (int i=0;i<len;i++){
                data.remove(0);
            }
        }


        // reset Label
        BILL_ID.setText("");
        total.setText("");
        date_value.setText("");

    }

   /* public void new_scene() throws IOException {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    } */


}