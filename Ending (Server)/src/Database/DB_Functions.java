package Database;

import Classes.*;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DB_Functions extends DB_Configuration
{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;

    }

    public int add_staff(Staff elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_STAFF + "(" +
                DB_Const.STAFF_NICKNAME + "," +
                DB_Const.STAFF_NAME + "," +
                DB_Const.STAFF_SURNAME + "," +
                DB_Const.STAFF_POSITION + "," +
                DB_Const.STAFF_PASSWORD + "," +
                DB_Const.STAFF_ACCESS_LEVEL  + ")" +
                " VALUES(?,?,?,?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, elem.nickname);
            prSt.setString(2, elem.name);
            prSt.setString(3, elem.surname);
            prSt.setString(4, elem.position);
            prSt.setString(5, elem.password);
            prSt.setInt(6, elem.access_level);
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

    public int add_client(Client elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_CLIENT + "(" +
                DB_Const.CLIENT_NAME + "," +
                DB_Const.CLIENT_SURNAME + "," +
                DB_Const.CLIENT_EMAIL + "," +
                DB_Const.CLIENT_PHONE + ")" +
                " VALUES(?,?,?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, elem.name);
            prSt.setString(2, elem.surname);
            prSt.setString(3, elem.email);
            prSt.setString(4, elem.phone);;
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

    public int add_order(Order elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_ORDER + "(" +
                DB_Const.ORDER_ID_STAFF + "," +
                DB_Const.ORDER_ID_CLIENT + ")" +
                " VALUES(?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setInt(1, elem.id_staff);
            prSt.setInt(2, elem.id_client);
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

    public void add_order_elem(Order_elem elem)
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

    public int add_product(Product elem)
    {
        // Запись абонента в базу данных
        // Формирование команды
        String insert = "INSERT INTO " + DB_Const.TABLE_PRODUCT + "(" +
                DB_Const.PRODUCT_NAME + "," +
                DB_Const.PRODUCT_PRICE + ")" +
                " VALUES(?,?)";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, elem.name);
            prSt.setInt(2, elem.price);
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

    public Staff compare_profile(String nickname, String password)
    {
        String insert = "SELECT COUNT(*) FROM " + DB_Const.TABLE_STAFF + " WHERE "
                + DB_Const.STAFF_NICKNAME + " = \'" + nickname + "\' AND " + DB_Const.STAFF_PASSWORD + " = \'" + password + "\'";
        try {
            // Заполнение команды и отправление в базу данных
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            ResultSet resSet = prSt.executeQuery();
            resSet.next();
            int buff = resSet.getInt(1);
            if(buff == 1)
            {
                insert = "SELECT * FROM " + DB_Const.TABLE_STAFF + " WHERE "
                        + DB_Const.STAFF_NICKNAME + " = \'" + nickname + "\' AND " + DB_Const.STAFF_PASSWORD + " = \'" + password + "\'";
                prSt = getDbConnection().prepareStatement(insert);
                resSet = prSt.executeQuery();
                resSet.next();
                Staff t = new Staff(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5), resSet.getString(6), resSet.getInt(7));
                return t;
            }
            else return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client[] read_client()
    {
        // Вычисление размера массива
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + DB_Const.TABLE_CLIENT;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = -1;
        try {
            if(resSet.next())
            {
                size = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(size == 0) return null;

        Client[] arr = new Client[size];

        select = "SELECT * FROM " + DB_Const.TABLE_CLIENT;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
            int i = 0;
            while(resSet.next())
            {
                Client t = new Client(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5));
                arr[i] = t;
                i++;
            }
            return arr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order[] read_order()
    {
        // Вычисление размера массива
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + DB_Const.TABLE_ORDER;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = -1;
        try {
            if(resSet.next())
            {
                size = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(size == 0) return null;

        Order[] arr = new Order[size];

        select = "SELECT * FROM " + DB_Const.TABLE_ORDER;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
            int i = 0;
            while(resSet.next())
            {
                Order t = new Order(resSet.getInt(1), resSet.getInt(2), resSet.getInt(3));
                arr[i] = t;
                i++;
            }
            return arr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order_elem[] read_elem_order()
    {
        // Вычисление размера массива
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + DB_Const.TABLE_ORDER_ELEM;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = -1;
        try {
            if(resSet.next())
            {
                size = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(size == 0) return null;

        Order_elem[] arr = new Order_elem[size];

        select = "SELECT * FROM " + DB_Const.TABLE_ORDER_ELEM;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
            int i = 0;
            while(resSet.next())
            {
                Order_elem t = new Order_elem(resSet.getInt(1), resSet.getInt(2), resSet.getInt(3));
                arr[i] = t;
                i++;
            }
            return arr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product[] read_product()
    {
        // Вычисление размера массива
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + DB_Const.TABLE_PRODUCT;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = -1;
        try {
            if(resSet.next())
            {
                size = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(size == 0) return null;

        Product[] arr = new Product[size];

        select = "SELECT * FROM " + DB_Const.TABLE_PRODUCT;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
            int i = 0;
            while(resSet.next())
            {
                Product t = new Product(resSet.getInt(1), resSet.getString(2), resSet.getInt(3));
                arr[i] = t;
                i++;
            }
            return arr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Staff[] read_staff()
    {
        // Вычисление размера массива
        ResultSet resSet = null;
        String select = "SELECT COUNT(*) FROM " + DB_Const.TABLE_STAFF;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int size = -1;
        try {
            if(resSet.next())
            {
                size = resSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(size == 0) return null;

        Staff[] arr = new Staff[size];

        select = "SELECT * FROM " + DB_Const.TABLE_STAFF;
        try {
            Statement prSt = getDbConnection().createStatement();
            resSet = prSt.executeQuery(select);
            int i = 0;
            while(resSet.next())
            {
                Staff t = new Staff(resSet.getInt(1), resSet.getString(2), resSet.getString(3), resSet.getString(4), resSet.getString(5), resSet.getString(6), resSet.getInt(7));
                arr[i] = t;
                i++;
            }
            return arr;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update_product(Product[] product_arr)
    {
        for (Product t:product_arr)
        {
            String query = "UPDATE " + DB_Const.TABLE_PRODUCT + " SET " +
                    DB_Const.PRODUCT_PRICE + " = ?," +
                    DB_Const.PRODUCT_NAME + " = ?" +
                    " WHERE " + DB_Const.PRODUCT_ID + " = ?";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(query);
                prSt.setInt(1, t.price);
                prSt.setString(2, t.name);
                prSt.setInt(3, t.id_product);
                prSt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete_client(Client[] client_arr)
    {
        if(client_arr.length == 0) return;
        Order[] orders = read_order();

        for (int i = 0; i < client_arr.length; i++)
        {
            for (int j = 0; j < orders.length; j++)
            {
                if(orders[j].id_client == client_arr[i].id_client)
                {
                    delete_order(orders[j]);
                }
            }
        }

        String query = "DELETE FROM " + DB_Const.TABLE_CLIENT + " WHERE ";
        for (Client t: client_arr)
        {
            query += DB_Const.CLIENT_ID + " = " + t.id_client + " OR ";
        }
        query = query.substring(0, query.length()-4);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete_product(Product[] product_arr)
    {
        if(product_arr.length == 0) return;

        DB_Functions funcs = new DB_Functions();
        Order_elem[] orem_buff = null;
        Order[] or_buff = null;

        if(funcs.read_elem_order() == null)
        {
            orem_buff = new Order_elem[0];
        }
        else
        {
            orem_buff = funcs.read_elem_order();
        }

        if(funcs.read_order() == null)
        {
            or_buff = new Order[0];
        }
        else
        {
            or_buff = funcs.read_order();
        }


        ArrayList<Order_elem> orders_elem_del = new ArrayList<>();
        HashSet<Integer> order_buff2 = new HashSet<>();

        for (int i = 0; i < product_arr.length; i++)
        {
            for (int j = 0; j < orem_buff.length; j++)
            {
                if(product_arr[i].id_product == orem_buff[j].id_product)
                {
                    orders_elem_del.add(orem_buff[j]);
                }
            }
        }

        for (int i = 0; i < orders_elem_del.size(); i++)
        {
            order_buff2.add(orders_elem_del.get(i).id_order);
        }

        for (Integer i:order_buff2)
        {
            for (int j = 0; j < or_buff.length; j++)
            {
                if(or_buff[j].id_order == i)
                {
                    delete_order(or_buff[j]);
                    break;
                }
            }
        }

        // Удалить продукты
        String query = "DELETE FROM " + DB_Const.TABLE_PRODUCT + " WHERE ";
        for (Product t: product_arr)
        {
            query += DB_Const.PRODUCT_ID + " = " + t.id_product + " OR ";
        }
        query = query.substring(0, query.length()-4);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete_order(Order order)
    {
        if(order == null) return;
        // Очистка элементов заказа
        String query = "DELETE FROM " + DB_Const.TABLE_ORDER_ELEM + " WHERE " + DB_Const.ORDER_ELEM_ID_ORDER + " = " + order.id_order;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        // Очистка заказа
        query = "DELETE FROM " + DB_Const.TABLE_ORDER + " WHERE " + DB_Const.ORDER_ID + " = " + order.id_order;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete_elem_order(Order_elem[] arr)
    {
        if(arr.length == 0) return;
        String query = "DELETE FROM " + DB_Const.TABLE_ORDER_ELEM + " WHERE ";
        for (Order_elem t: arr)
        {
            query += "(" + DB_Const.ORDER_ELEM_ID_ORDER + " = " + t.id_order + " AND " + DB_Const.ORDER_ELEM_ID_PRODUCT + " = " + t.id_product + ") OR ";
        }
        query = query.substring(0, query.length()-4);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear_database()
    {
        String[] query =  new String[5];
        query[0] = "DELETE FROM " + DB_Const.TABLE_ORDER_ELEM;
        query[1] = "DELETE FROM " + DB_Const.TABLE_ORDER;
        query[2] = "DELETE FROM " + DB_Const.TABLE_CLIENT;
        query[3] = "DELETE FROM " + DB_Const.TABLE_PRODUCT;
        query[4] = "DELETE FROM " + DB_Const.TABLE_STAFF;
        try {
            for(int i = 0; i < 5; i ++)
            {
                PreparedStatement prSt = getDbConnection().prepareStatement(query[i]);
                prSt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean compare_nickname(String nickname)
    {
        // Пример из бенча
        // SELECT ID_Staff FROM staff WHERE staff.nickname = "George"
        String insert = "SELECT COUNT(" + DB_Const.STAFF_NICKNAME + ") FROM " + DB_Const.TABLE_STAFF + " WHERE " + DB_Const.STAFF_NICKNAME + " = '" + nickname + "'";
        try
        {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            ResultSet res = prSt.executeQuery();
            res.next();
            int buff = res.getInt(1);
            if(buff != 0) return false; // Если имя существует - false
            else return true; // Если ника нет - true

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
