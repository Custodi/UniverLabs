package Interface.MetaClasses;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product_Elem
{
    private final SimpleIntegerProperty id;

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

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

    private final SimpleStringProperty name;
    private final SimpleIntegerProperty price;

    public Product_Elem()
    {
        this.id = new SimpleIntegerProperty(0);
        price = new SimpleIntegerProperty(0);
        name = new SimpleStringProperty("");
    }

    public Product_Elem(int id, String name, int price)
    {
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleIntegerProperty(price);
        this.name = new SimpleStringProperty(name);
    }
}
