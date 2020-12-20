package Classes;

public class Order_elem
{
    public Order_elem(int id_order, int id_product, int count) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.count = count;
    }

    public Order_elem()
    {

    }

    public int id_order, id_product, count;
}
