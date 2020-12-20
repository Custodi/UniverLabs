package MainPack;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller
{
    ObservableList<Elem> table_arr = FXCollections.observableArrayList();

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_surname;

    @FXML
    private TextField tf_subname;

    @FXML
    private TextField tf_date;

    @FXML
    private TextField tf_operator;

    @FXML
    private TextField tf_tariff;

    @FXML
    private Button but_add;

    @FXML
    private Button but_remove;

    @FXML
    private Button but_update;

    @FXML
    private TableColumn<Elem, String> name_table;

    @FXML
    private TableColumn<Elem, String> surname_table;

    @FXML
    private TableColumn<Elem, String> subname_table;

    @FXML
    private TableColumn<Elem, Date> date_table;

    @FXML
    private TableColumn<Elem, String> operator_table;

    @FXML
    private TableColumn<Elem, Integer> tariff_table;

    @FXML
    private TableView<Elem> main_table;

    private Elem selected;
    private boolean is_updating;

    public Controller()
    {
        activate();
    }

    @FXML
    private void initialize()
    {
        is_updating = false;

        // Определение столбцов в таблице
        name_table.setCellValueFactory(new PropertyValueFactory<Elem, String>("name"));
        date_table.setCellValueFactory(new PropertyValueFactory<Elem, Date>("date"));
        surname_table.setCellValueFactory(new PropertyValueFactory<Elem, String>("surname"));
        operator_table.setCellValueFactory(new PropertyValueFactory<Elem, String>("operator"));
        subname_table.setCellValueFactory(new PropertyValueFactory<Elem, String>("subname"));
        tariff_table.setCellValueFactory(new PropertyValueFactory<Elem, Integer>("tariff"));

        // Соединение массива с таблицей
        main_table.setItems(table_arr);

        main_table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> set_selected_line(newValue)));
    }

    public void activate()
    {
        ResultSet res = new DatabaseHandler().getalldata();
        try
        {
            table_arr.clear();
            while(res.next())
            {
                Elem t = new Elem(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5), res.getString(6), res.getInt(7));
                table_arr.add(t);
            }
        }
        catch(SQLException exc)
        {
            exc.printStackTrace();
        }
    }

    public void add_elem(ActionEvent act)
    {
        if (is_updating) return;
        // Проверка на ввод даты
        LocalDate buff_date;
        Date date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            buff_date = LocalDate.parse(tf_date.getText(), formatter);
        } catch (Exception e) {
            e.printStackTrace();
            tf_date.clear();
            return;
        }

        date = Date.valueOf(buff_date);

        // Проверка на ввод значения тарифа
        int tariff;
        try
        {
            tariff = Integer.parseInt(tf_tariff.getText());
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
            tf_tariff.clear();
            return;
        }
        if (tariff < 0)
        {
            tf_tariff.clear();
            return;
        }

        // Проверка на пустые значения
        String _name = tf_name.getText();
        String _surname = tf_surname.getText();
        String _subname = tf_subname.getText();
        String _operator = tf_operator.getText();
        if(_name.isEmpty() || _surname.isEmpty() || _subname.isEmpty() || _operator.isEmpty())
        {
            return;
        }

        // Передача данных БД
        DatabaseHandler dbHandler = new DatabaseHandler();
        int id = dbHandler.add_abonent(_name, _surname, _subname, date, _operator, tariff);

        // Обновление таблицы
        Elem t = new Elem(id, _name, _surname, _subname, date, _operator, tariff);
        table_arr.add(t);
    }

    public void set_selected_line(Elem e)
    {
        if(!is_updating) selected = e;
    }

    public void update_elem(ActionEvent act)
    {
        if(selected != null)
        {
            if(!is_updating)
            {
                but_update.setText("Confirm");
                tf_name.setText(selected.getName());
                tf_surname.setText(selected.getSurname());
                tf_subname.setText(selected.getSubname());
                tf_operator.setText(selected.getOperator());
                tf_tariff.setText(Integer.toString(selected.getTariff()));

                // Из-за разницы форматов дат SQL и Java, выполняем махинации с переводом
                LocalDate ld = selected.getDate().toLocalDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                tf_date.setText(ld.format(formatter));
                is_updating = true;
            }
            else
            {
                // Проверка на ввод даты
                LocalDate buff_date;
                Date date;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                try {
                    buff_date = LocalDate.parse(tf_date.getText(), formatter);
                } catch (Exception e) {
                    e.printStackTrace();
                    tf_date.clear();
                    return;
                }

                date = Date.valueOf(buff_date);

                // Проверка на ввод значения тарифа
                int tariff;
                try
                {
                    tariff = Integer.parseInt(tf_tariff.getText());
                } catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    tf_tariff.clear();
                    return;
                }
                if (tariff < 0)
                {
                    tf_tariff.clear();
                    return;
                }

                // Проверка на пустые значения
                String _name = tf_name.getText();
                String _surname = tf_surname.getText();
                String _subname = tf_subname.getText();
                String _operator = tf_operator.getText();
                if(_name.isEmpty() || _surname.isEmpty() || _subname.isEmpty() || _operator.isEmpty())
                {
                    return;
                }

                // Передача данных БД
                DatabaseHandler dbHandler = new DatabaseHandler();
                dbHandler.update_abonent(selected.getElem_id(), _name, _surname, _subname, date, _operator, tariff);

                // Обновление таблицы
                selected.set_new_values(_name, _surname, _subname, date, _operator, tariff);

                // Возвращение в обратное состояние
                is_updating = false;
                but_update.setText("Update");
            }
        }
    }

    public void delete_elem(ActionEvent act)
    {
        if(selected != null || is_updating)
        {
            try
            {
                for (Elem i:table_arr) {
                    if(i.getElem_id() == selected.getElem_id())
                    {
                        new DatabaseHandler().remove_abonent(selected.getElem_id());
                        activate();
                    }
                }
            }
            catch(Exception exc)
            {
                
            }

        }
    }
}
