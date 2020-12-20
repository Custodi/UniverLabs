package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Registration_Controller implements Initializable
{
    @FXML
    public TextField login;

    @FXML
    public TextField password;

    @FXML
    public TextField name;

    @FXML
    public TextField surname;

    @FXML
    public TextField position;

    @FXML
    public CheckBox is_admin;

    @FXML
    public Button confirm_button;

    @FXML
    public Text error_str;

    public void confirm()
    {
        // 1 - Проверка на запрет символы | и &
        // 2 - Проверка на наличие уже в БД
        // 3 - В случае успеха, добавить в базу данных и выкинуть в окно авторизации
        String login_text = login.getText();
        String pass_text = password.getText();
        String name_text = name.getText();
        String surname_text = surname.getText();
        String pos_text = position.getText();
        Boolean is_admin_bool = is_admin.isSelected();

        if(
                login_text.contains("&") || login_text.contains("|") || login_text.isEmpty()
                || pass_text.contains("&") || pass_text.contains("|") || pass_text.isEmpty()
                || name_text.contains("&") || name_text.contains("|") || name_text.isEmpty()
                || surname_text.contains("&") || surname_text.contains("|") || surname_text.isEmpty()
                || pos_text.contains("&") || pos_text.contains("|") || pos_text.isEmpty()
            )
        {
            show_error(1);
            return;
        }
        if(Init.client_connection.send_query("COMPARE&" + login_text).equals("False"))
        {
            show_error(2);
            return;
        }
        Staff[] t = new Staff[1];
        t[0]= new Staff(0,login_text, name_text, surname_text, pos_text, pass_text, is_admin_bool ? 1 : 0);
        Init.client_connection.send_query(DB_Interface.staff_command(DB_Interface.commands.ADD,t));
        Init.set_window(1);
    }

    public void show_error(int type)
    {
        if(type == 1)
        {
            error_str.setText("Invalid value(s). Enter valid string(s).");
            login.clear();
            name.clear();
            surname.clear();
            position.clear();
            password.clear();
        }
        else {
            error_str.setText("This nickname is already exists. Enter another nickname.");
            login.clear();
        }
        error_str.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
