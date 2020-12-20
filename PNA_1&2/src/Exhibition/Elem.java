package Exhibition;

import java.util.Objects;

public abstract class Elem {
    protected String name;
    protected int year;
    protected boolean is_hidden;
    abstract void get_info();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
    public String toString()
    {
        return name + Integer.toString(year) + Boolean.toString(is_hidden);
    }
}
