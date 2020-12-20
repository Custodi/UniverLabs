package Interface.Controllers;

import Classes.Init;
import DB_Classes.Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class User_Menu_Controller implements Initializable
{
    @FXML
    private Button exit;

    @FXML
    private Text profile_info;

    @FXML
    private Button add_order_button;

    @FXML
    private Button check_orders_button;

    @FXML
    private Button add_client_button;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff t = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + t.name + " " + t.surname);
    }

    public void add_order()
    {
        Init.set_window(4);
    }

    public void check_order()
    {
        Init.set_window(3);
    }

    public void add_client()
    {
        Init.set_window(2);
    }

    public void profile_exit()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }
}
