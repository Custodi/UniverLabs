package Classes;

public class Order
{
    public Order(int id_order, int id_staff, int id_client) {
        this.id_order = id_order;
        this.id_client = id_client;
        this.id_staff = id_staff;
    }

    public Order()
    {

    }

    public int id_order, id_client, id_staff;
}
