package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.*;
import DB_Classes.Staff;
import Interface.MetaClasses.FirstTable_Elem;
import Interface.MetaClasses.SecondTable_Elem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class Add_Order_Controller implements Initializable
{
    ObservableList <FirstTable_Elem> table1_arr = FXCollections.observableArrayList();
    ObservableList <SecondTable_Elem> table2_arr = FXCollections.observableArrayList();

    ObservableList <Integer> client_arr = FXCollections.observableArrayList();

    @FXML
    private TextField count;

    @FXML
    private Button add_product;

    @FXML
    private Button add_order;

    @FXML
    private Button exit_button;

    @FXML
    private Button back;

    @FXML
    private Text profile_info;

    @FXML
    private Text message_error;

    @FXML
    private ComboBox<Integer> client_list;

    @FXML
    private TableView <FirstTable_Elem> first_table;

    @FXML
    private TableView <SecondTable_Elem> second_table;

    @FXML
    private TableColumn<FirstTable_Elem, String> product_column;

    @FXML
    private TableColumn<FirstTable_Elem, Integer> price_column;

    @FXML
    private TableColumn<SecondTable_Elem, String> product_column_in_order;

    @FXML
    private TableColumn<SecondTable_Elem, Integer> count_column;

    @FXML
    private TableColumn<SecondTable_Elem, Integer> summary_column;

    FirstTable_Elem selected_elem = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        client_list.setItems(client_arr);

        product_column.setCellValueFactory(new PropertyValueFactory<FirstTable_Elem, String>("name"));
        price_column.setCellValueFactory(new PropertyValueFactory<FirstTable_Elem, Integer>("price"));

        product_column_in_order.setCellValueFactory(new PropertyValueFactory<SecondTable_Elem, String>("name"));
        count_column.setCellValueFactory(new PropertyValueFactory<SecondTable_Elem, Integer>("count"));
        summary_column.setCellValueFactory(new PropertyValueFactory<SecondTable_Elem, Integer>("price"));

        first_table.setItems(table1_arr);
        second_table.setItems(table2_arr);

        first_table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> set_selected_line(newValue)));

        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.READ, null)));
        if(DB_Interface.product_arr.toArray().length > 0)
        {
            for (Product t : DB_Interface.product_arr)
            {
                table1_arr.add(new FirstTable_Elem(t.id_product, t.name, t.price));
            }
            DB_Interface.clear_interface();
        }

        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.client_command(DB_Interface.commands.READ, null)));
        if(DB_Interface.client_arr.toArray().length > 0)
        {
            for (Client t : DB_Interface.client_arr)
            {
                client_arr.add(t.id_client);
            }
            DB_Interface.clear_interface();
        }
    }

    public void exit_button()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void back_to_screen()
    {
        if(Init.client_connection.registed_employee.access_level == 0)
        {
            Init.set_window(5);
        }
        else
        {
            Init.set_window(7);
        }
    }

    public void set_selected_line(FirstTable_Elem t)
    {
        selected_elem = t;
    }

    public void add_to_order()
    {
        if(selected_elem == null || count.getText().isEmpty() || Integer.parseInt(count.getText()) < 1)
        {
            message_error.setVisible(true);
        }
        else
        {
            SecondTable_Elem t = new SecondTable_Elem(selected_elem.getId(), selected_elem.getName(),Integer.parseInt(count.getText()) ,selected_elem.getPrice() * Integer.parseInt(count.getText()));
            table2_arr.add(t);
            message_error.setVisible(false);
        }
    }

    public void confirm_order()
    {
        if(table2_arr.isEmpty() || client_list.getValue() == null)
        {
            message_error.setVisible(true);
            return;
        }

        // Проверка на одинаковые элементы в заказе
        for(int i = 0; i < table2_arr.size(); i++)
        {
            for(int j = 0; j < table2_arr.size(); j++)
            {
                SecondTable_Elem a = table2_arr.get(i);
                SecondTable_Elem b = table2_arr.get(j);
                if(a.getId() == b.getId() && i != j)
                {
                    a.setId(a.getCount() + b.getId());
                    a.countProperty().set(a.getCount() + b.getCount());
                    table2_arr.remove(b);
                }
            }
        }

        Order[] t = new Order[1];
        t[0] = new Order (2026, Init.client_connection.registed_employee.id_staff, client_list.getValue());
        Init.client_connection.send_query(DB_Interface.order_command(DB_Interface.commands.ADD, t));
        Order_elem[] j = new Order_elem[table2_arr.size()];
        for(int i = 0; i < j.length; i++)
        {
            j[i] = new Order_elem(2026, table2_arr.get(i).getId(), table2_arr.get(i).getCount());
        }
        Init.client_connection.send_query(DB_Interface.order_elem_command(DB_Interface.commands.ADD, j));
        table2_arr.clear();
    }
}
