package Classes;

public class Client
{
    public Client(int id_client, String name, String surname, String email, String phone) {
        this.id_client = id_client;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Client()
    {

    }

    public int id_client;
    public String name, surname, email, phone;
}
