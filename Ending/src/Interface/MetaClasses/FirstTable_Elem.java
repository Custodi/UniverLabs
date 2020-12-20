package Interface.MetaClasses;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FirstTable_Elem
{
    private final SimpleStringProperty name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    private final SimpleIntegerProperty price;

    public FirstTable_Elem(int id, String name, int price)
    {
        this.id = id;
        this.name =new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    public FirstTable_Elem()
    {
        this.id = -1;
        this.name =new SimpleStringProperty("");
        this.price = new SimpleIntegerProperty(0);
    }
}
