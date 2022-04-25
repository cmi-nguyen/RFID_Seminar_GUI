package UI;

import DTO.DTO_Observable_Bill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller {

    @FXML
    public Label total;
    public TableView<DTO_Observable_Bill> table;
    public Label BILL_ID;
    public TableColumn<DTO_Observable_Bill, String> idCol; // idCol is Column id in XML file
    public TableColumn<DTO_Observable_Bill, String> nameCol;
    public TableColumn<DTO_Observable_Bill, String> priceCol;
    public TableColumn<DTO_Observable_Bill, String> dateCol;
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
        data = FXCollections.observableArrayList(
            new DTO_Observable_Bill("test","test","test","test")
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
        dateCol.setCellValueFactory(
                new PropertyValueFactory<DTO_Observable_Bill ,String>("date")
        );

        // add ObservableList to Table
        table.setItems(data);
        table.getItems().add(new DTO_Observable_Bill("1","2","3","4"));


    }

    public void accept_btn_func (){
        System.out.println("accept and save order"); // debug code
        // code
    }

    public void delete_item_func (){
        System.out.println("Delete item in order "); // debug code
        //code
        DTO_Observable_Bill selected = table.getSelectionModel().getSelectedItem();
        data.remove(selected);

    }

    public void cancel_order_func (){
        System.out.println("Cancel Order");
        // code

        if(data!=null) {
            for (int i = 0; i <= data.toArray().length; i++) {

                data.remove(0);
            }
        }else System.out.println("Cannot Delete if List is Null");

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
