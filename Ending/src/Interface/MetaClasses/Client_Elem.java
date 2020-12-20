package Interface.MetaClasses;

import javafx.beans.property.SimpleStringProperty;

public class Client_Elem
{
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty email;
    private final SimpleStringProperty phone;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int _id)
    {
        id = _id;
    }

    public Client_Elem(int id,String name, String surname, String email, String phone)
    {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }

    public Client_Elem()
    {
        id = 0;
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
    }

}
