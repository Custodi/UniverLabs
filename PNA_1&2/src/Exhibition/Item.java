package Exhibition;

public class Item extends Elem implements Hidden {
    public Item()
    {
        name = "";
        year = 2000;
        is_hidden = false;
    }
    public Item(String _name, int _year)
    {
        name = _name;
        year = _year;
        is_hidden = false;
    }

    @Override
    void get_info()
    {
        System.out.println("This is " + name + " item, that was created in " + year);
    }

    @Override
    public void hide()
    {
        is_hidden = true;
    }

    @Override
    public void showup()
    {
        is_hidden = false;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elem elem = (Elem) o;
        return year == elem.year &&
                is_hidden == elem.is_hidden &&
                name.equals(elem.name);
    }

    @Override
    public int hashCode()
    {
        return year * 1000 + (name.length() * 21) % 40;
    }

    @Override
    public String toString() {
        return name + Integer.toString(year) + Boolean.toString(is_hidden);
    }
}
