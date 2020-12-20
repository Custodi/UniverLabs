package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.Client;
import DB_Classes.Product;
import DB_Classes.Staff;
import Interface.MetaClasses.Client_Elem;
import Interface.MetaClasses.FirstTable_Elem;
import Interface.MetaClasses.Order_Elem;
import Interface.MetaClasses.SecondTable_Elem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class Client_Controller implements Initializable
{
    ObservableList<Client_Elem> table_arr = FXCollections.observableArrayList();

    @FXML
    private Button add_client_button;

    @FXML
    private Button remove_client_button;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private Button back_to_screen_button;

    @FXML
    private Button exit_profile_button;

    @FXML
    private Text profile_info;

    @FXML
    private Text error_message;

    @FXML
    private TableView <Client_Elem> main_table;

    @FXML
    private TableColumn<Client_Elem, String> name_col;

    @FXML
    private TableColumn<Client_Elem, String> surname_col;

    @FXML
    private TableColumn<Client_Elem, String> email_col;

    @FXML
    private TableColumn<Client_Elem, String> phone_col;

    Client_Elem selected_item = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        if(Init.client_connection.registed_employee.access_level == 0)
        {
            remove_client_button.setDisable(true);
        }

        name_col.setCellValueFactory(new PropertyValueFactory<Client_Elem, String>("name"));
        surname_col.setCellValueFactory(new PropertyValueFactory<Client_Elem, String>("surname"));
        email_col.setCellValueFactory(new PropertyValueFactory<Client_Elem, String>("email"));
        phone_col.setCellValueFactory(new PropertyValueFactory<Client_Elem, String>("phone"));

        main_table.setItems(table_arr);

        main_table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> set_selected_line(newValue)));

        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.client_command(DB_Interface.commands.READ, null)));
        if (DB_Interface.client_arr.toArray().length > 0)
        {
            for(Client t:DB_Interface.client_arr)
            {
                table_arr.add(new Client_Elem(t.id_client, t.name, t.surname, t.email, t.phone));
            }
            DB_Interface.clear_interface();
        }
    }

    public void set_selected_line(Client_Elem t)
    {
        selected_item = t;
    }

    public void exit_profile()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void back_to_menu()
    {
        if (Init.client_connection.registed_employee.access_level == 0)
        {
            Init.set_window(5);
        }
        else
        {
            Init.set_window(6);
        }
    }

    public void add_client()
    {
        String[] email_elements = email.getText().split("@");
        if(email_elements.length != 2 || email_elements[0].equals("") || email_elements[1].equals("")||
                name.getText().isEmpty() ||  surname.getText().isEmpty() ||  email.getText().isEmpty() ||
                phone.getText().isEmpty())
        {
            error_message.setVisible(true);
            name.clear();
            surname.clear();
            email.clear();
            phone.clear();
        }
        else
        {
            Client[] buff_arr = new Client[1];
            Client_Elem b = new Client_Elem(0,name.getText(), surname.getText(), email.getText(), phone.getText());
            buff_arr[0] = new Client(0,name.getText(), surname.getText(), email.getText(), phone.getText());
            Init.client_connection.send_query(DB_Interface.client_command(DB_Interface.commands.ADD,buff_arr));
            table_arr.add(b);
        }
    }

    public void remove_client()
    {
        if(selected_item == null)
        {
            error_message.setVisible(true);
        }
        else
        {
            Client[] buff_arr = new Client[1];
            buff_arr[0] = new Client(selected_item.getId(), selected_item.getName(), selected_item.getSurname(), selected_item.getEmail(), selected_item.getPhone());
            Init.client_connection.send_query(DB_Interface.client_command(DB_Interface.commands.DELETE,buff_arr));
            table_arr.remove(selected_item);
        }
    }
}
