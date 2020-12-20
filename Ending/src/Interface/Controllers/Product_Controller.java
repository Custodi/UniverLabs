package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.Product;
import DB_Classes.Staff;
import Interface.MetaClasses.Product_Elem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Product_Controller implements Initializable
{
    ObservableList<Product_Elem> table_arr = FXCollections.observableArrayList();

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_price;

    @FXML
    private Text profile_info;

    @FXML
    private Text error_message;

    @FXML
    private Button but_add;

    @FXML
    private Button but_remove;

    @FXML
    private Button but_update;

    @FXML
    private TableColumn<Product_Elem, Integer> id_table;

    @FXML
    private TableColumn<Product_Elem, String> name_table;

    @FXML
    private TableColumn<Product_Elem, Integer> price_table;

    @FXML
    private TableView<Product_Elem> main_table;

    @FXML
    private Button profile_exit;

    @FXML
    private Button back_to_menu;

    private Product_Elem selected;
    private boolean is_updating;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        is_updating = false;

        // Определение столбцов в таблице
        name_table.setCellValueFactory(new PropertyValueFactory<Product_Elem, String>("name"));
        id_table.setCellValueFactory(new PropertyValueFactory<Product_Elem, Integer>("id"));
        price_table.setCellValueFactory(new PropertyValueFactory<Product_Elem, Integer>("price"));

        // Соединение массива с таблицей
        main_table.setItems(table_arr);

        refresh_data();

        // Добавление значений в таблицу из БД
        main_table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> set_selected_line(newValue)));
    }

    public void refresh_data() {
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.READ, null)));
        table_arr.clear();
        for (Product t : DB_Interface.product_arr) {
            Product_Elem tt = new Product_Elem(t.id_product, t.name, t.price);
            table_arr.add(tt);
        }
        DB_Interface.clear_interface();
    }

    public void add_elem(ActionEvent act) {
        if (is_updating) {
            error_message.setVisible(true);
            return;
        }

        // Проверка на ввод значения цены
        int price;
        try {
            price = Integer.parseInt(tf_price.getText());
        } catch (NumberFormatException e) {
            error_message.setVisible(true);
            tf_price.clear();
            return;
        }
        if (price < 0) {
            error_message.setVisible(true);
            tf_price.clear();
            return;
        }

        // Проверка на пустые значения
        if (tf_price.getText().isEmpty() || tf_name.getText().isEmpty()) {
            error_message.setVisible(true);
            return;
        }

        // Добавление товара в БД и таблицу
        Product[] prod = new Product[1];
        prod[0] = new Product(0, tf_name.getText(), Integer.parseInt(tf_price.getText()));
        Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.ADD, prod));
        error_message.setVisible(false);
        refresh_data();
    }

    public void set_selected_line(Product_Elem e) {
        if (!is_updating) selected = e;
    }

    public void update_elem(ActionEvent act) {
        if (selected != null) {
            if (!is_updating) {
                but_update.setText("Подтвердить изменение");
                tf_name.setText(selected.getName());
                tf_price.setText(String.valueOf(selected.getPrice()));
                is_updating = true;
            } else {
                // Проверка на ввод значения цены
                int price;
                try {
                    price = Integer.parseInt(tf_price.getText());
                } catch (NumberFormatException e) {
                    error_message.setVisible(true);
                    tf_price.clear();
                    return;
                }
                if (price < 0) {
                    error_message.setVisible(true);
                    tf_price.clear();
                    return;
                }

                // Проверка на пустые значения
                if (tf_price.getText().isEmpty() || tf_name.getText().isEmpty()) {
                    error_message.setVisible(true);
                    return;
                }

                // Добавление товара в БД и таблицу
                Product[] prod = new Product[1];
                prod[0] = new Product(selected.getId(), tf_name.getText(), price);
                Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.UPDATE, prod));
                for (int i = 0; i < table_arr.size(); i++) {
                    if (selected.getId() == table_arr.get(i).getId()) {
                        table_arr.get(i).setName(tf_name.getText());
                        table_arr.get(i).setPrice(price);
                    }
                }
                tf_name.clear();
                tf_price.clear();
                error_message.setVisible(false);
                but_update.setText("Редактировать товар");
                refresh_data();
                is_updating = false;
            }
        } else {
            error_message.setVisible(true);
        }
    }

    public void delete_elem(ActionEvent act) {
        if (selected != null || is_updating) {
            Product[] arr = new Product[1];
            arr[0] = new Product(selected.getId(), selected.getName(), selected.getPrice());
            Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.DELETE, arr));
            error_message.setVisible(false);
            refresh_data();
        } else {
            error_message.setVisible(true);
        }
    }

    public void back() {
        Init.set_window(6);
    }

    public void exit() {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

}
