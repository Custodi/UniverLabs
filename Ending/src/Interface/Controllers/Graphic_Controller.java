package Interface.Controllers;

import Classes.DB_Interface;
import Classes.Init;
import DB_Classes.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Graphic_Controller implements Initializable
{
    @FXML
    private Button exit;

    @FXML
    private Button to_menu;

    @FXML
    private Text profile_info;

    @FXML
    private PieChart staff_chart;

    @FXML
    private PieChart product_chart;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Staff k = Init.client_connection.registed_employee;
        profile_info.setText("Профиль: " + k.name + " " + k.surname);

        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.client_command(DB_Interface.commands.READ, null)));
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.order_command(DB_Interface.commands.READ, null)));
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.order_elem_command(DB_Interface.commands.READ, null)));
        DB_Interface.decrypt(Init.client_connection.send_query(DB_Interface.product_command(DB_Interface.commands.READ, null)));

        // Формирование первого графика - доход по клиентам
        {
            for (Client t:DB_Interface.client_arr) // Для каждого клиента...
            {
                int summary = 0;
                for (Order o:DB_Interface.order_arr)
                {
                    if(o.id_client == t.id_client) // ..проходим по заказам, которые принадлежат ему...
                    {
                        for(Order_elem oe:DB_Interface.order_elem_arr)
                        {
                            if(oe.id_order == o.id_order)// ...находим элементы заказа, которые принадлежат данному заказу...
                            {
                                for(Product prod:DB_Interface.product_arr)
                                {
                                    if(oe.id_product == prod.id_product) // ...если элемент заказа совпадает с товаром
                                    {
                                        summary += prod.price * oe.count; // ...добавляем его к сумме заказа
                                    }
                                }
                            }
                        }
                    }
                }
               staff_chart.getData().add(new PieChart.Data(t.name + " " + t.surname, summary));
            }

        }

        // Формирование второго графика по продажам на товар
        {
            for(Product prod:DB_Interface.product_arr)
            {
                int summary = 0;
                for(Order_elem t:DB_Interface.order_elem_arr)
                {
                    if(t.id_product == prod.id_product)
                    {
                        summary += t.count * prod.price;
                    }
                }
                product_chart.getData().add(new PieChart.Data(prod.name, summary));
            }
        }
        DB_Interface.clear_interface();
    }

    public void profile_exit()
    {
        Init.client_connection.registed_employee = null;
        Init.set_window(1);
    }

    public void return_to_menu()
    {
        Init.set_window(6);
    }
}
