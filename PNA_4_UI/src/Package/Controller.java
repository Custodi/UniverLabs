package Package;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.io.*;
import java.net.*;

public class Controller
{
    @FXML
    private TextField IPAddr;
    @FXML
    private TextField Port;
    @FXML
    private TextField client_input;

    @FXML
    private Button send_but;
    @FXML
    private Label server_response;
    @FXML
    private Button exit_but;

    public String address, message;
    public int port;
    private Socket clientSocket;
    private ObjectOutputStream out_stream;
    private ObjectInputStream in_stream;

    // После нажатия кнопки - попытка подсоединиться
    public void connect_to_server(ActionEvent event)
    {
        // Присвоение значение строк
        address = IPAddr.getText();
        port = Integer.parseInt(Port.getText());

        // Создание сокета и потоков
        try
        {
            clientSocket = new Socket(address, port);
            if(clientSocket.isConnected())
            {
                connection_result(true);
                out_stream = new ObjectOutputStream(clientSocket.getOutputStream());
                in_stream = new ObjectInputStream(clientSocket.getInputStream());
            }
            else
            {
                connection_result(false);
                return;
            }
        }
        catch(Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
            return;
        }
    }

    // В случае удачного подключения - открываем поля
    public void connection_result(boolean is_success)
    {
        client_input.setDisable(!is_success);
        send_but.setDisable(!is_success);
    }

    // При нажатии на кнопку выхода - выход
    public void button_exit(ActionEvent event)
    {
        try
        {
            out_stream.writeObject("quite");
            out_stream.close();
            in_stream.close();
            clientSocket.close();
        }
        catch(Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
        }
        Stage stage = (Stage) exit_but.getScene().getWindow();
        stage.close();
    }

    // Отправляем запрос на сервер
    public void send(ActionEvent event)
    {
        try
        {
            message = client_input.getText();
            out_stream.writeObject(message);
            message = (String)in_stream.readObject();
            set_answer(message);
        }
        catch(Exception exc)
        {
            System.out.println("Error: " + exc.getMessage());
            return;
        }
    }

    // Принимаем ответ и устанавливаем в строку
    public void set_answer(String str)
    {
        server_response.setText(str);
    }
}
