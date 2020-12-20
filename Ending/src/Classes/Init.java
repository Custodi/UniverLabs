package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Init extends Application
{
    public static Client_TCP client_connection;
    public static Stage _mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        _mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/Interface/Scripts/Authorization.fxml"));
        primaryStage.setTitle("PSA Ending");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void set_window(int id)
    {
        String path = "";
        switch(id) {
            default: {
                System.out.println("Invalid path of screen script");
            }
            case 0: // Регистрация
            {
                path = "/Interface/Scripts/Registration.fxml";
                break;
            }
            case 1: // Авторизация
            {
                path = "/Interface/Scripts/Authorization.fxml";
                break;
            }
            case 2: {
                path = "/Interface/Scripts/Client_screen.fxml";
                break;
            }
            case 3: {
                path = "/Interface/Scripts/User_Order_screen.fxml";
                break;
            }
            case 4: {
                path = "/Interface/Scripts/Add_Order.fxml";
                break;
            }
            case 5: {
                path = "/Interface/Scripts/User_Menu.fxml";
                break;
            }
            case 6: {
                path = "/Interface/Scripts/Admin_Menu.fxml";
                break;
            }
            case 7: {
                path = "/Interface/Scripts/Admin_Order_screen.fxml";
                break;
            }
            case 8: {
                path = "/Interface/Scripts/Product_screen.fxml";
                break;
            }
            case 9:{
                path = "/Interface/Scripts/Graphics.fxml";
                break;
            }
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(Init.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        _mainStage.setScene(new Scene(root));
        _mainStage.show();
    }

    @Override
    public void stop()
    {
        client_connection.close_connection();
    }

    public static void main(String[] args)
    {
        client_connection = new Client_TCP();
        Thread connection_thread = new Thread(client_connection,"Client Connection");
        connection_thread.start();
        launch(args);
    }
}
