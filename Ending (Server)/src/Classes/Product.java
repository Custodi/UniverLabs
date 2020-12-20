package Classes;

public class Product
{
    public Product(int id_product, String name, int price) {
        this.id_product = id_product;
        this.price = price;
        this.name = name;
    }

    public Product()
    {

    }

    public int id_product, price;
    public String name;
}
