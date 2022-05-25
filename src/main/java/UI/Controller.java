package UI;

import BLL.HandleScan;
import DAL.PdfTool;
import DTO.DTO_Bill;
import DTO.DTO_Observable_Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;


public class Controller {

    @FXML
    public Button Print_btn;
    @FXML
    public Button RefundB;
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
        if(order!=null){

                dialog(scannedData.refund(order));
        }

    }

    public void dialog(String text){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Dialog");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        Node closeButton = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
        closeButton.managedProperty().bind(closeButton.visibleProperty());
        closeButton.setVisible(false);
        dialog.setContentText(text);
        dialog.showAndWait();
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
        if(scannedData.unknownTag!=""){
            dialog(scannedData.unknownTag);
        }
    }

    public void accept_btn_func () throws Exception {
        if(order!=null){
            dialog("save order");
        }
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

    public void Print_btn_func() {
        if(order!=null){
            PdfTool.HandleMess(order);
            dialog("Da in hoa don");
        }

        else {
            System.out.println("Doesn't have order yet");
        }
    }

}