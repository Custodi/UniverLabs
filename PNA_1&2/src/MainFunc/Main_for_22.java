package MainFunc;
import Exhibition.*;

import java.util.ArrayList;

public class Main_for_22 {
    public static void main(String[] args)
    {
        ArrayList <Mural> arr = new ArrayList <Mural> ();

        arr.add(new Mural("God", 1534, "Plate"));
        arr.add(new Mural());
        arr.add(new Mural("Rainbow", 2011, "Brick"));

        Item a = new Item("Amphora", 1745);

        System.out.println(arr.get(0).equals(arr.get(0)) + " | " + arr.get(0).equals(arr.get(1)));
        System.out.println(arr.get(2).hashCode() + " | " + a.hashCode());
        System.out.println(arr.get(0).toString() + " | " + a.toString());


    }
}
