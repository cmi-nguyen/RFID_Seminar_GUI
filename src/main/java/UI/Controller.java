package UI;

import BLL.HandleScan;
import DTO.DTO_Bill;
import DTO.DTO_Observable_Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;


public class Controller {

    @FXML
    public Button Print_btn;
    public Label total;
    public Label date_value;
    public Label BILL_ID;
    public TableColumn<DTO_Observable_Bill, String> idCol; // idCol is Column id in XML file
    public TableColumn<DTO_Observable_Bill, String> nameCol;
    public TableColumn<DTO_Observable_Bill, String> priceCol;
    public TableView<DTO_Observable_Bill> table;
    public ObservableList<DTO_Observable_Bill>data;
    HandleScan scannedData;
    DTO_Bill order;


    // controller
    public void connect_btn_func (){

        System.out.println("Connect desktop reader");

        // this.table.setItems();
        // this.total.setText("Nguyn");
    }

    public void scan_btn_func() throws Exception {
        System.out.println("Scan with desktop reader"); // debug code
        // code
        scannedData = new HandleScan();
        order= scannedData.orderScan();



        //Date today = new Date(2022-1900,4,26);



        data = FXCollections.observableArrayList();

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
        for (int i=0;i<order.getProductInstance().size();i++){
            table.getItems().add(new DTO_Observable_Bill(order.getProductInstance().get(i)
                    ,scannedData.FindProduct(order.getProductInstance().get(i)).getName()
                    ,String.valueOf(scannedData.FindProduct(order.getProductInstance().get(i)).getPrice()) ));
        }





        // set label value
        System.out.println("Scanned items: "+order.getProductInstance());
        BILL_ID.setText(order.getBill_ID());
        total.setText(String.valueOf(order.getTotal()));
        date_value.setText(String.valueOf(order.getDate()));
    }

    public void accept_btn_func () throws Exception {
        System.out.println("accept and save order"); // debug code
        // code
        scannedData.saveOrder(order);
        BILL_ID.setText(order.getBill_ID());
        total.setText(String.valueOf(order.getTotal()));
        date_value.setText(String.valueOf(order.getDate()));
        Print_btn.setVisible(true);
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
            String del_id=selected.getProductInstanceID();
            List<String> list = order.getProductInstance();
            list.remove(del_id);
            order.setProductInstance(list);
            Float newtotal = 0f;
            for(int i =0;i<list.size();i++){
                newtotal += scannedData.FindProduct(order.getProductInstance().get(i)).getPrice();
            }

            order.setTotal(newtotal);
            System.out.println(list);
            System.out.println("order items "+order.getProductInstance());
        }

        // reset label

        BILL_ID.setText(order.getBill_ID());
        total.setText(String.valueOf(order.getTotal()));
        date_value.setText(String.valueOf(order.getDate()));

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
        order = new DTO_Bill();

        // reset Label
        Print_btn.setVisible(false);
        BILL_ID.setText("");
        total.setText("");
        date_value.setText("");

    }

    public void Print_btn_func(){
        System.out.println("Print Bill to PDF");
    }

   /* public void new_scene(String name) throws IOException {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    } */


}