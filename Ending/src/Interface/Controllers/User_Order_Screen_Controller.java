package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.*;
import Interface.MetaClasses.FirstTable_Elem;
import Interface.MetaClasses.Order_Elem;
import Interface.MetaClasses.SecondTable_Elem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class User_Order_Screen_Controller implements Initializable
{
    @FXML
    private Button add_order;


    @FXML
    private Button exit_profile_button;

    @FXML
    private Button back_to_menu;

    @FXML
    private Button to_text_button;

    @FXML
    private Text profile_info;

    @FXML
    private CheckBox by_order_summary;

    @FXML
    private CheckBox by_orders_id;

    @FXML
    private TableView<Order_Elem> main_table;
    ObservableList<Order_Elem> table_arr = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Order_Elem, Integer> order_id_col;

    @FXML
    private TableColumn<Order_Elem, String> product_name_col;

    @FXML
    private TableColumn<Order_Elem, Integer> count_col;

    @FXML
    private TableColumn<Order_Elem, Integer> sum_col;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        main_table.setItems(table_arr);

        order_id_col.setCellValueFactory(new PropertyValueFactory<Order_Elem, Integer>("order_id"));
        product_name_col.setCellValueFactory(new PropertyValueFactory<Order_Elem, String>("product_name"));
        count_col.setCellValueFactory(new PropertyValueFactory<Order_Elem, Integer>("count"));
        sum_col.setCellValueFactory(new PropertyValueFactory<Order_Elem, Integer>("summary"));

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
                                table_arr.add(new Order_Elem(order.id_order, prod, elem.count));
                            }
                        }
                    }
                }
            }
            DB_Interface.clear_interface();
        }
    }

    public void profile_exit()
    {

        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void back_to_menu_func()
    {
        if(Init.client_connection.registed_employee.access_level == 0)
        {
            Init.set_window(5);
        }
        else
        {
            Init.set_window(6);
        }
    }

    public void add_order_func()
    {
        Init.set_window(4);
    }

    public void sorting_summary()
    {
        if(by_orders_id.isSelected() && by_order_summary.isSelected())
        {
            by_orders_id.setSelected(false);
        }
        table_arr.sort(Order_Elem.SummaryComparator);
    }

    public void sorting_id()
    {
        if(by_order_summary.isSelected() && by_orders_id.isSelected())
        {
            by_order_summary.setSelected(false);
        }
        table_arr.sort(Order_Elem.OrderComparator);
    }

    public void convert_to_text()
    {
        try {
            FileWriter writter = new FileWriter(Init.client_connection.registed_employee.name + " " + Init.client_connection.registed_employee.surname + " report.txt", false);
            String report = "";
            for(Order_Elem t:table_arr)
            {
                report += t.getOrder_id() + "  ||  " + t.getProduct_id() + "  ||  " + t.getCount() + "  ||  " + t.getSummary() + '\n';
            }
            Calendar cal = new GregorianCalendar();
            report += "\n------------------------\nReport was generated at " + cal.getTime().toString() + "\n------------------------";
            writter.write(report);
            writter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
