package Interface.MetaClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SecondTable_Elem
{
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public int getPrice() {
        return price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return price;
    }

    private final SimpleStringProperty name;
    private final SimpleIntegerProperty count;
    private final SimpleIntegerProperty price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public SecondTable_Elem(int id, String name, int count ,int price)
    {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.count = new SimpleIntegerProperty(count);
        this.price = new SimpleIntegerProperty(price);
    }

    public SecondTable_Elem()
    {
        this.id = -1;
        this.name =new SimpleStringProperty("");
        this.count = new SimpleIntegerProperty(0);
        this.price = new SimpleIntegerProperty(0);
    }
}
