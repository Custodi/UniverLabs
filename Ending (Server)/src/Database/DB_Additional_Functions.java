package Database;

import Classes.*;
import Database.DB_Const;

import java.sql.*;

public class DB_Additional_Functions extends DB_Configuration
{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;

    }

    public int addid_staff(Staff elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_STAFF + "(" +
                DB_Const.STAFF_ID + "," +
                DB_Const.STAFF_NICKNAME + "," +
                DB_Const.STAFF_NAME + "," +
                DB_Const.STAFF_SURNAME + "," +
                DB_Const.STAFF_POSITION + "," +
                DB_Const.STAFF_PASSWORD + "," +
                DB_Const.STAFF_ACCESS_LEVEL  + ")" +
                " VALUES(?,?,?,?,?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_staff);
            prSt.setString(2, elem.nickname);
            prSt.setString(3, elem.name);
            prSt.setString(4, elem.surname);
            prSt.setString(5, elem.position);
            prSt.setString(6, elem.password);
            prSt.setInt(7, elem.access_level);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение ID записанного сотрудника
        ResultSet resSet = null;
        String select = "SELECT MAX(" + DB_Const.STAFF_ID + ") FROM " + DB_Const.TABLE_STAFF;

        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = -1;

        try {
            if(resSet.next())
            {
                id = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

    public int addid_client(Client elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_CLIENT + "(" +
                DB_Const.CLIENT_ID + "," +
                DB_Const.CLIENT_NAME + "," +
                DB_Const.CLIENT_SURNAME + "," +
                DB_Const.CLIENT_EMAIL + "," +
                DB_Const.CLIENT_PHONE + ")" +
                " VALUES(?,?,?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_client);
            prSt.setString(2, elem.name);
            prSt.setString(3, elem.surname);
            prSt.setString(4, elem.email);
            prSt.setString(5, elem.phone);;
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение ID записанного клиента
        ResultSet resSet = null;
        String select = "SELECT MAX(" + DB_Const.CLIENT_ID + ") FROM " + DB_Const.TABLE_CLIENT;

        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = -1;

        try {
            if(resSet.next())
            {
                id = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

    public int addid_order(Order elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_ORDER + "(" +
                DB_Const.ORDER_ID + "," +
                DB_Const.ORDER_ID_STAFF + "," +
                DB_Const.ORDER_ID_CLIENT + ")" +
                " VALUES(?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_order);
            prSt.setInt(2, elem.id_staff);
            prSt.setInt(3, elem.id_client);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение ID записанного клиента
        ResultSet resSet = null;
        String select = "SELECT MAX(" + DB_Const.ORDER_ID + ") FROM " + DB_Const.TABLE_ORDER;

        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = -1;

        try {
            if(resSet.next())
            {
                id = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }

    public void addid_order_elem(Order_elem elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_ORDER_ELEM + "(" +
                DB_Const.ORDER_ELEM_ID_ORDER + "," +
                DB_Const.ORDER_ELEM_ID_PRODUCT + "," +
                DB_Const.ORDER_ELEM_COUNT + ")" +
                " VALUES(?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_order);
            prSt.setInt(2, elem.id_product);
            prSt.setInt(3, elem.count);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int addid_product(Product elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_PRODUCT + "(" +
                DB_Const.PRODUCT_ID + "," +
                DB_Const.PRODUCT_NAME + "," +
                DB_Const.PRODUCT_PRICE + ")" +
                " VALUES(?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_product);
            prSt.setString(2, elem.name);
            prSt.setInt(3, elem.price);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение ID записанного продукта
        ResultSet resSet = null;
        String select = "SELECT MAX(" + DB_Const.PRODUCT_ID + ") FROM " + DB_Const.TABLE_PRODUCT;

        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int id = -1;

        try {
            if(resSet.next())
            {
                id = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }
}
