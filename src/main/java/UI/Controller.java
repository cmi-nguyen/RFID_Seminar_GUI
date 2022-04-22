package UI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.io.IOException;



public class Controller {

    @FXML
    public Label total;
    public TableView table;

    // controller
    public void connect_btn_func (ActionEvent e){

        System.out.println("Connect desktop reader");

       // this.table.setItems();
       // this.total.setText("Nguyn");
    }

    public void scan_btn_func(ActionEvent event) throws Exception {
        System.out.println("Scan with desktop reader"); // debug code
        // code

    }

    public void accept_btn_func (ActionEvent event){
        System.out.println("accept and save order"); // debug code
        // code
    }

    public void delete_item_func (ActionEvent event){
        System.out.println("Delete item in order "); // debug code
        //code
    }

    public void cancel_order_func (ActionEvent event){
        System.out.println("Cancel Order");
        // code
    }

    public void new_scene() throws IOException {
        Stage stage =new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


}
