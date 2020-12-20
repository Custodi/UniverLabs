package MainPackage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller
{
    ObservableList first_list_arr = FXCollections.observableArrayList();
    ObservableList second_list_arr = FXCollections.observableArrayList();

    @FXML
    private Button add_to_list;

    @FXML
    private Button apply_settings;

    @FXML
    private Button clear_the_list;

    @FXML
    private ListView<String> first_list = new ListView<String>(first_list_arr);;

    @FXML
    private ListView<String> second_list = new ListView<String>(second_list_arr);

    @FXML
    private CheckBox remove_odds;

    @FXML
    private CheckBox move_even;

    @FXML
    private TextField input_string;

    public void add_elem(ActionEvent act)
    {
        String buff = input_string.getText();
        if(!buff.isEmpty())
        {
            first_list_arr.add(buff);
            input_string.clear();
            first_list.getItems().add(buff);
        }
    }

    public void set_settings(ActionEvent act)
    {
        if(remove_odds.isSelected())
        {
            for(int i = 0; i < first_list_arr.size(); i+=2)
            {
                first_list_arr.remove(i);
                first_list.getItems().remove(i);
                i--;
            }
        }

        if(move_even.isSelected())
        {
            for(int i = 1; i < first_list_arr.size(); i+=2)
            {

                second_list_arr.add(first_list_arr.get(i));
                second_list.getItems().add(first_list.getItems().get(i));
            }
        }
    }

    public void clear_the_second_list(ActionEvent act)
    {
        second_list_arr.clear();
        second_list.getItems().clear();
    }

    public void check_key(KeyEvent act)
    {
        if(act.getCode() == KeyCode.ENTER) add_elem(null);
    }
}
