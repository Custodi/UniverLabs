package AdditionalPackage;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.*;

public class Controller {

    @FXML
    private Button order;

    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField email;

    @FXML
    private TextField number;

    @FXML
    private RadioButton radiobut_credit;

    @FXML
    private RadioButton radiobut_debit;

    @FXML
    private SplitMenuButton split_menu;

    @FXML
    private MenuItem green_card;

    @FXML
    private MenuItem blue_card;

    @FXML
    private MenuItem red_card;

    @FXML
    private CheckBox check;

    @FXML
    private Text message;

    private String text = "None";

    public void reset(ActionEvent act)
    {
        order.setDisable(!check.isSelected());
    }

    public void accept(ActionEvent act)
    {
        boolean is_error = false;
        String number_str = number.getText();
        String email_str = email.getText();

        if(number_str.length() == 0 || email_str.length() == 0 || name.getText().length() == 0 || surname.getText().length() == 0) is_error = true;

        for(int i = 0; i < number_str.length(); i++)
        {
            if((number_str.charAt(i) < '0') || (number_str.charAt(i) > '9'))
            {
                is_error = true;
                number.clear();
            }
        }

        boolean is_exist = false;
        for(int i = 0; i < email_str.length(); i++)
        {
            if(email_str.charAt(i) == '@')
            {
                is_exist = !is_exist;
                if(i+1 >= email_str.length() || i == 0)
                {
                    is_error = true;
                    email.clear();
                }
            }
        }
        if(!is_exist) is_error = true;

        if(text == "None") is_error = true;

        if(is_error)
        {
            message.setVisible(true);
            message.setText("Invalid input!");
            return;
        }
        else
        {
            String type_of_card;
            if(radiobut_credit.isArmed()) type_of_card = "Credit";
            else type_of_card = "Debit";
            String file_text = name.getText() + " " + surname.getText() + " | " + email_str + " | " + number_str + " -> " + type_of_card + " " + text + '\n';

            // Запись в файл
            try(FileOutputStream file = new FileOutputStream("C://Rez//Database.txt", true))
            {
                byte[] buffer = file_text.getBytes();
                file.write(buffer);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            order.setDisable(true);
            check.setSelected(false);
            name.clear();
            surname.clear();
            email.clear();
            number.clear();
            message.setVisible(true);
            message.setText("Card was ordered!");
        }
    }

    public void green_click()
    {
        split_menu.setText("Green card");
        System.out.println("Green");
        text = "Green";
    }

    public void blue_click()
    {
        split_menu.setText("Blue card");
        System.out.println("Blue");
        text = "Blue";
    }

    public void red_click()
    {
        split_menu.setText("Red card");
        System.out.println("Red");
        text = "Red";
    }

}
