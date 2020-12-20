package Interface.Controllers;

import Classes.Client_TCP;
import Classes.DB_Interface;
import Classes.Init;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.lang.*;
import java.util.ResourceBundle;

public class Authorization_Controller implements Initializable
{
    @FXML
    private TextField login_text;

    @FXML
    private TextField password_text;

    @FXML
    private Text message;

    @FXML
    private Button sign_up_button;

    @FXML
    private Button log_in_button;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void call_sign_up_screen()
    {
        Init.set_window(0);
    }

    public void log_in()
    {
        String answer = Init.client_connection.send_query("COMPARE2&" + login_text.getText() + "&" + password_text.getText());
        if(answer.equals("False") || answer.equals("Error"))
        {
            message.setVisible(true);
            login_text.clear();
            password_text.clear();
        }
        else
        {
            DB_Interface.decrypt(answer);
            Init.client_connection.registed_employee = DB_Interface.staff_arr.get(0);
            DB_Interface.clear_interface();
            if(Init.client_connection.registed_employee.access_level == 0)
            {
                Init.set_window(5);
            }
            else
            {
                Init.set_window(6);
            }
        }

    }
}
