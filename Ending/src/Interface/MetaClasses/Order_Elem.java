package Interface.MetaClasses;

import DB_Classes.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Comparator;

public class Order_Elem implements Comparable<Order_Elem>
{
    private final SimpleIntegerProperty order_id;
    private final SimpleIntegerProperty count;

    public int getOrder_id() {
        return order_id.get();
    }

    public SimpleIntegerProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id.set(order_id);
    }

    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public int getSummary() {
        return summary.get();
    }

    public SimpleIntegerProperty summaryProperty() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary.set(summary);
    }

    public String getProduct_name() {
        return product_name.get();
    }

    public SimpleStringProperty product_nameProperty() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name.set(product_name);
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    private final SimpleIntegerProperty summary;
    private final SimpleStringProperty product_name;
    private int product_id;

    public Order_Elem()
    {
        product_id = -1;
        this.product_name = new SimpleStringProperty("");
        this.order_id = new SimpleIntegerProperty(0);
        this.count = new SimpleIntegerProperty(0);
        this.summary = new SimpleIntegerProperty(0);
    }

    public Order_Elem(int order_id, Product t, int count)
    {
        product_id = t.id_product;
        this.product_name = new SimpleStringProperty(t.name);
        this.order_id = new SimpleIntegerProperty(order_id);
        this.count = new SimpleIntegerProperty(count);
        this.summary = new SimpleIntegerProperty(t.price * count);
    }

    @Override
    public int compareTo(Order_Elem o)
    {
        return this.order_id.getValue() - o.order_id.getValue();
    }

    public static Comparator<Order_Elem> OrderComparator = new Comparator<Order_Elem>() {

        public int compare(Order_Elem a, Order_Elem b)
        {
            return a.compareTo(b);
        }
    };

    public static Comparator<Order_Elem> SummaryComparator = new Comparator<Order_Elem>() {

        public int compare(Order_Elem a, Order_Elem b)
        {
            return a.getSummary() - b.getSummary();
        }
    };
}
