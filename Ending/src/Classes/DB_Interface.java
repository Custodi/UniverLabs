package Classes;

import DB_Classes.*;

import java.io.Serializable;
import java.util.ArrayList;

////getAllCars?carName=Ilea&model=koi

public class DB_Interface implements Serializable
{
    public static ArrayList<Client> client_arr = new ArrayList<>();
    public static ArrayList<Order> order_arr = new ArrayList<>();
    public static ArrayList<Order_elem> order_elem_arr = new ArrayList<>();
    public static ArrayList<Product> product_arr = new ArrayList<>();
    public static ArrayList<Staff> staff_arr = new ArrayList<>();

    // Оснастка для создания интерфейса
    public enum commands {ADD, READ, UPDATE, DELETE}
    private final static String[] com_arr = {"ADD", "READ", "UPDATE", "DELETE"};

    public static String client_command(commands command, Client[] arr)
    {
        // Формат протокола = "command&|tX|data1;data2;...dataN&|tX|..."
        String result = com_arr[command.ordinal()];

        if(result.equals("READ"))
        {
            return "READ&client";
        }
        else
        {
            for (Client t: arr)
            {
                result += "&|t1|";
                result += t.id_client + ";" + t.name + ";" + t.surname + ";" + t.email + ";" + t.phone;
            }
            return result;
        }
    }

    public static String order_command(commands command, Order[] arr)
    {
        // Формат протокола = "command&|tX|data1;data2;...dataN&|tX|..."
        String result = com_arr[command.ordinal()];

        if(result.equals("READ"))
        {
            return "READ&order";
        }
        else
        {
            for (Order t: arr)
            {
                result += "&|t2|";
                result += t.id_order + ";" + t.id_staff + ";" + t.id_client;
            }
            return result;
        }
    }

    public static String order_elem_command(commands command, Order_elem[] arr)
    {
        // Формат протокола = "command&|tX|data1;data2;...dataN&|tX|..."
        String result = com_arr[command.ordinal()];

        if(result.equals("READ"))
        {
            return "READ&order_elem";
        }
        else
        {
            for (Order_elem t: arr)
            {
                result += "&|t3|";
                result += t.id_order + ";" + t.id_product + ";" + t.count;
            }
            return result;
        }
    }

    public static String product_command(commands command, Product[] arr)
    {
        // Формат протокола = "command&|tX|data1;data2;...dataN&|tX|..."
        String result = com_arr[command.ordinal()];

        if(result.equals("READ"))
        {
            return "READ&product";
        }
        else
        {
            for (Product t: arr)
            {
                result += "&|t4|";
                result += t.id_product + ";" + t.name + ";" + t.price;
            }
            return result;
        }
    }

    public static String staff_command(commands command, Staff[] arr)
    {
        // Формат протокола = "command&|tX|data1;data2;...dataN&|tX|..."
        String result = com_arr[command.ordinal()];

        if(result.equals("READ"))
        {
            return "READ&staff";
        }
        else
        {
            for (Staff t: arr)
            {
                result += "&|t5|";
                result += t.id_staff + ";" + t.nickname + ";" + t.name + ";" +
                        t.surname + ";" + t.position + ";" + t.password + ";" + t.access_level;
            }
            return result;
        }
    }

    public static void decrypt(String query)
    {
        if(query == "None") return;
        String[] str_classes = query.split("&");
        for (String str : str_classes) {
            switch (str.substring(0, 4)) {
                case "|t1|": {
                    String[] second_step = str.substring(4).split(";");
                    Client t = new Client(Integer.parseInt(second_step[0]), second_step[1], second_step[2], second_step[3], second_step[4]);
                    client_arr.add(t);
                    break;
                }
                case "|t2|": {
                    String[] second_step = str.substring(4).split(";");
                    Order t = new Order(Integer.parseInt(second_step[0]), Integer.parseInt(second_step[1]), Integer.parseInt(second_step[2]));
                    order_arr.add(t);
                    break;
                }
                case "|t3|": {
                    String[] second_step = str.substring(4).split(";");
                    Order_elem t = new Order_elem(Integer.parseInt(second_step[0]), Integer.parseInt(second_step[1]), Integer.parseInt(second_step[2]));
                    order_elem_arr.add(t);
                    break;
                }
                case "|t4|": {
                    String[] second_step = str.substring(4).split(";");
                    Product t = new Product(Integer.parseInt(second_step[0]), second_step[1], Integer.parseInt(second_step[2]));
                    product_arr.add(t);
                    break;
                }
                case "|t5|": {
                    String[] second_step = str.substring(4).split(";");
                    Staff t = new Staff(Integer.parseInt(second_step[0]), second_step[1], second_step[2], second_step[3], second_step[4], second_step[5], Integer.parseInt(second_step[6]));
                    staff_arr.add(t);
                    break;
                }
            }
        }
    }

    public static void clear_interface()
    {
        client_arr.clear();
        order_arr.clear();
        order_elem_arr.clear();
        product_arr.clear();
        staff_arr.clear();
    }
}
