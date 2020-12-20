package Exhibition;

import java.util.Objects;

public class Mural extends Picture {
    public Mural()
        {
            type = name = "";
            year = 2000;
            is_hidden = false;
        }
    public Mural(String _name, int _year, String _type)
        {
            name = _name;
            year = _year;
            is_hidden = false;
            type = _type;
        }

        @Override
        void get_info()
        {
            System.out.println("This is " + name + " mural, that was created in " + year + " ,made of " + type);
        }

    @Override
    public int hashCode()
    {
        return year * 1000 + (name.length() * 21 + type.length() * 80) % 14;
    }

    @Override
    public String toString() {
        return name + Integer.toString(year) + type + Boolean.toString(is_hidden);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
