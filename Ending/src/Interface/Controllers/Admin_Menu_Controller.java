package Interface.Controllers;

import Classes.Init;
import DB_Classes.Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin_Menu_Controller implements Initializable
{
    @FXML
    private Button order_but;

    @FXML
    private Button client_but;

    @FXML
    private Button product_but;

    @FXML
    private Button exit_but;

    @FXML
    private Button save_but;

    @FXML
    private Button load_but;

    @FXML
    private Button stats_but;

    @FXML
    private Text profile_info;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);
    }

    public void exit_profile()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void client_func()
    {
        Init.set_window(2);
    }

    public void order_func()
    {
        Init.set_window(7);
    }

    public void product_func()
    {
        Init.set_window(8);
    }

    public void stats_func()
    {
        Init.set_window(9);
    }

    public void save_db_func()
    {
        Init.client_connection.send_query("SAVE");
    }

    public void load_db_func()
    {
        Init.client_connection.send_query("LOAD");
    }
}
