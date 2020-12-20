package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.Order;
import DB_Classes.Order_elem;
import DB_Classes.Product;
import DB_Classes.Staff;
import Interface.MetaClasses.Admin_Order_Elem;
import Interface.MetaClasses.Order_Elem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Admin_Order_Controller implements Initializable
{
    @FXML
    private Button add_order_but;

    @FXML
    private Button remove_order_but;

    @FXML
    private Button remove_elem_order_but;

    @FXML
    private Button back_to_screen_but;

    @FXML
    private Button profile_exit_but;

    @FXML
    private Text profile_info;

    @FXML
    private TableView <Admin_Order_Elem> main_table;

    ObservableList<Admin_Order_Elem> table_arr = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Admin_Order_Elem, Integer> order_id_col;

    @FXML
    private TableColumn<Admin_Order_Elem, String> product_name_col;

    @FXML
    private TableColumn<Admin_Order_Elem, Integer> count_col;

    @FXML
    private TableColumn<Admin_Order_Elem, Integer> sum_col;

    @FXML
    private TableColumn<Admin_Order_Elem, Integer> staff_col;

    private Admin_Order_Elem selected_item;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        main_table.setItems(table_arr);
        main_table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> set_selected_line(newValue)));

        order_id_col.setCellValueFactory(new PropertyValueFactory<Admin_Order_Elem, Integer>("order_id"));
        product_name_col.setCellValueFactory(new PropertyValueFactory<Admin_Order_Elem, String>("product_name"));
        count_col.setCellValueFactory(new PropertyValueFactory<Admin_Order_Elem, Integer>("count"));
        sum_col.setCellValueFactory(new PropertyValueFactory<Admin_Order_Elem, Integer>("summary"));
        staff_col.setCellValueFactory(new PropertyValueFactory<Admin_Order_Elem, Integer>("staff"));

        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.READ, null)));
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.order_command(DB_Interface.commands.READ, null)));
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.order_elem_command(DB_Interface.commands.READ, null)));

        if(DB_Interface.product_arr.size() > 0 && DB_Interface.order_arr.size() > 0)
        {
            for(Order order:DB_Interface.order_arr)
            {
                for(Order_elem elem:DB_Interface.order_elem_arr)
                {
                    if(elem.id_order == order.id_order)
                    {
                        for(Product prod:DB_Interface.product_arr)
                        {
                            if(elem.id_product == prod.id_product)
                            {
                                table_arr.add(new Admin_Order_Elem(order, prod, elem.count));
                            }
                        }
                    }
                }
            }
        }
        DB_Interface.clear_interface();
    }

    public void add_order_func()
    {
        Init.set_window(4);
    }

    public void remove_elem_order()
    {
        if(selected_item != null)
        {
            table_arr.remove(selected_item);
            Order_elem[] j = new Order_elem[1];
            j[0] = new Order_elem(selected_item.getOrder_id(), selected_item.getProduct_id(), -1);
            Init.client_connection.send_query(DB_Interface.order_elem_command(DB_Interface.commands.DELETE,j));
            selected_item = null;
        }
    }

    public void remove_order()
    {
        if(selected_item!= null)
        {
            int order_id = selected_item.getOrder_id();
            table_arr.removeIf(t -> t.getOrder_id() == order_id);
            Order[] j = new Order[1];
            j[0] = new Order(order_id, selected_item.getStaff(), -1);
            Init.client_connection.send_query(DB_Interface.order_command(DB_Interface.commands.DELETE,j));
            selected_item = null;
        }
    }

    public void back_screen()
    {
        Init.set_window(6);
    }

    public void profile_exit()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void set_selected_line(Admin_Order_Elem t)
    {
        selected_item = t;
    }
}
