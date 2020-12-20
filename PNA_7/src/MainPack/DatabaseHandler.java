package MainPack;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Config
{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public int add_abonent(String name, String surname, String subname, Date date, String operator, int tariff)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + Const.ABONENT_TABLE + "(" +
        Const.ABONENT_NAME + "," +
        Const.ABONENT_SURNAME + "," +
        Const.ABONENT_SUBNAME + "," +
        Const.ABONENT_DATE + "," +
        Const.ABONENT_OPERATOR + "," +
        Const.ABONENT_TARIFF  + ")" +
                " VALUES(?,?,?,?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, subname);
            prSt.setDate(4, date);
            prSt.setString(5, operator);
            prSt.setInt(6, tariff);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Получение ID записанного абонента
        ResultSet resSet = null;
        String select = "SELECT MAX(" + Const.ABONENT_ID + ") FROM " + Const.ABONENT_TABLE;

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

    public ResultSet getalldata()
    {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.ABONENT_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public void remove_abonent(int id)
    {
        String query = "DELETE FROM " + Const.ABONENT_TABLE + " WHERE " + Const.ABONENT_ID + " = " + id;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update_abonent(int _id,String name, String surname, String subname, Date date, String operator, int tariff)
    {
        String query = "UPDATE " + Const.ABONENT_TABLE + " SET " +
                Const.ABONENT_NAME + " = ?," +
                Const.ABONENT_SURNAME + " = ?," +
                Const.ABONENT_SUBNAME + " = ?," +
                Const.ABONENT_DATE + " = ?," +
                Const.ABONENT_OPERATOR + " = ?," +
                Const.ABONENT_TARIFF  + " = ?" +
                " WHERE " + Const.ABONENT_ID + " = ?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, subname);
            prSt.setDate(4, date);
            prSt.setString(5, operator);
            prSt.setInt(6, tariff);
            prSt.setInt(7, _id);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
