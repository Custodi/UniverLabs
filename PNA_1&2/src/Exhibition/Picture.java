package Exhibition;

import java.util.Objects;

public class Picture extends Elem implements Hidden {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected String type;
    public Picture() {
        name = "";
        year = 2000;
        is_hidden = false;
        type = "";
    }

    public Picture(String _name, int _year, String _type) {
        name = _name;
        year = _year;
        is_hidden = false;
        type = _type;
    }

    @Override
    void get_info() {
        System.out.println("This is " + name + " picture, that was created in " + year + " , made of " + type);
    }

    @Override
    public void hide() {
        is_hidden = true;
    }

    @Override
    public void showup() {
        is_hidden = false;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Picture picture = (Picture) o;
        return Objects.equals(type, picture.type);
    }
}