package Database;

import Classes.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DB_Interface
{
  static ArrayList<Client> client_arr = new ArrayList<>();
  static Order order_var;
  static ArrayList<Order> order_arr = new ArrayList<>();
  static ArrayList<Order_elem> order_elem_arr = new ArrayList<>();
  static ArrayList<Product> product_arr = new ArrayList<>();
  static ArrayList<Staff> staff_arr = new ArrayList<>();
  static int last_order_id;
  static int order_database_id;

    public static String make_query(String query)
    {
        DB_Functions funcs = new DB_Functions();
        String[] first_step = query.split("&");
        switch (first_step[0])
        {
            case "COMPARE": // Для нахождения совпадений никнейма (true - если свободно)
            {
                if(funcs.compare_nickname(first_step[1])) return "True";
                else return "False";
            }
            case "COMPARE2": // True, если есть
            {
                if(first_step.length != 3) return "False";
                Staff buff = funcs.compare_profile(first_step[1], first_step[2]);
                if(buff == null) return "False";
                else
                {
                    return "|t5|" + buff.id_staff + ";" + buff.nickname + ";" + buff.name + ";" + buff.surname + ";" + buff.position + ";" + buff.password + ";" + buff.access_level;
                }
            }
            case "ADD":
            {
                decrypt(first_step, 1);
                for (Client client : client_arr)
                {
                    funcs.add_client(client);
                }
                if(order_var != null)
                {
                    order_database_id = funcs.add_order(order_var);
                    last_order_id = order_var.id_order;
                }
                for(Order_elem order_elem : order_elem_arr)
                {
                    if(order_elem.id_order == last_order_id)
                    {
                        order_elem.id_order = order_database_id;
                    }
                    funcs.add_order_elem(order_elem);
                }
                for(Product product:product_arr)
                {
                    funcs.add_product(product);
                }
                for(Staff staff:staff_arr)
                {
                    funcs.add_staff(staff);
                }
                break;
            }
            case "DELETE": // Для добавления элемента в таблицу
            {
                decrypt(first_step, 1);
                Client[] buff_client = new Client[client_arr.size()];
                client_arr.toArray(buff_client);
                Order_elem[] buff_elem = new Order_elem[order_elem_arr.size()];
                order_elem_arr.toArray(buff_elem);
                Product[] buff_product = new Product[product_arr.size()];
                product_arr.toArray(buff_product);

                funcs.delete_client(buff_client);
                funcs.delete_elem_order(buff_elem);
                funcs.delete_order(order_var);
                funcs.delete_product(buff_product);
                break;
            }
            case "UPDATE":
            {
                decrypt(first_step, 1);
                Product[] buff_product = new Product[product_arr.size()];
                product_arr.toArray(buff_product);
                funcs.update_product(buff_product);
            }
            case "READ": // Для чтения базы данных
            {
                String answer = "";
                switch(first_step[1])
                {
                    case "client":
                    {
                        Client[] arr = funcs.read_client();
                        if(arr == null) return "None";
                        for (Client t: arr)
                        {
                            answer += "&|t1|";
                            answer += t.id_client + ";" + t.name + ";" + t.surname + ";" + t.email + ";" + t.phone;
                        }
                        answer = answer.substring(1);
                        return answer;
                    }
                    case "order":
                    {
                        Order[] arr = funcs.read_order();
                        if(arr == null) return "None";
                        for (Order t: arr)
                        {
                            answer += "&|t2|";
                            answer += t.id_order + ";" + t.id_staff + ";" + t.id_client;
                        }
                        answer = answer.substring(1);
                        return answer;
                    }
                    case "order_elem":
                    {
                        Order_elem[] arr = funcs.read_elem_order();
                        if(arr == null) return "None";
                        for (Order_elem t: arr)
                        {
                            answer += "&|t3|";
                            answer += t.id_order + ";" + t.id_product + ";" + t.count;
                        }
                        answer = answer.substring(1);
                        return answer;
                    }
                    case "product":
                    {
                        Product[] arr = funcs.read_product();
                        if(arr == null) return "None";
                        for (Product t: arr)
                        {
                            answer += "&|t4|";
                            answer += t.id_product + ";" + t.name + ";" + t.price;
                        }
                        answer = answer.substring(1);
                        return answer;
                    }
                    case "staff":
                    {
                        Staff[] arr = funcs.read_staff();
                        if(arr == null) return "None";
                        for (Staff t: arr)
                        {
                            answer += "&|t5|";
                            answer += t.id_staff + ";" + t.nickname + ";" + t.name + ";" + t.surname + ";" + t.position + ";" + t.password + ";" + t.access_level;
                        }
                        answer = answer.substring(1);
                        return answer;
                    }
                }
                return "";
            }
            case "SAVE":
            {
                try {
                    String to_file = "";
                    to_file += make_query("READ&client");
                    to_file += '\n';
                    to_file += make_query("READ&order");
                    to_file += '\n';
                    to_file += make_query("READ&order_elem");
                    to_file += '\n';
                    to_file += make_query("READ&product");
                    to_file += '\n';
                    to_file += make_query("READ&staff");
                    FileWriter writer = new FileWriter("Database_dump.txt",false);
                    writer.write(to_file);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "LOAD":
            {
                funcs.clear_database();
                String str = "";
                try {
                    FileReader reader = new FileReader("Database_dump.txt");
                    Scanner scan = new Scanner(reader);
                    while(scan.hasNextLine())
                    {
                        str = scan.nextLine();
                        decrypt(str.split("&"), 0);
                        str = "";
                    }
                    DB_Additional_Functions funcs1 = new DB_Additional_Functions();
                    for (Client client : client_arr)
                    {
                        funcs1.addid_client(client);
                    }
                    for(Product product:product_arr)
                    {
                        funcs1.addid_product(product);
                    }
                    for(Staff staff:staff_arr)
                    {
                        funcs1.addid_staff(staff);
                    }
                    for(Order order:order_arr)
                    {
                        funcs1.addid_order(order);
                    }
                    for(Order_elem order_elem : order_elem_arr)
                    {
                        funcs1.addid_order_elem(order_elem);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            default: return "Error";
        }
        clear_interface();
        return "None";
    }

    public static void decrypt(String[] corrupted_str_classes, int mode)
    {
        String[] str_classes = new String[corrupted_str_classes.length-mode];
        for(int i = 0; i < corrupted_str_classes.length - mode; i++)
        {
            str_classes[i] = corrupted_str_classes[i+mode];
        }
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
                    order_var = new Order(Integer.parseInt(second_step[0]), Integer.parseInt(second_step[1]), Integer.parseInt(second_step[2]));
                    order_arr.add(order_var);
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
        order_var = null;
        order_arr.clear();
        order_elem_arr.clear();
        product_arr.clear();
        staff_arr.clear();
    }
}