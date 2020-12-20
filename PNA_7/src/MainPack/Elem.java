package MainPack;

import javafx.beans.property.*;

import java.sql.Date;

public class Elem
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

    public String getSubname() {
        return subname.get();
    }

    public SimpleStringProperty subnameProperty() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname.set(subname);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public String getOperator() {
        return operator.get();
    }

    public SimpleStringProperty operatorProperty() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator.set(operator);
    }

    public int getTariff() {
        return tariff.get();
    }

    public SimpleIntegerProperty tariffProperty() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff.set(tariff);
    }

    public int getElem_id()
    {
        return elem_id;
    }

    public void set_new_values(String _name, String _surname, String _subname, Date _date, String _operator, int _tariff)
    {
        name.set(_name);
        surname.set(_surname);
        subname.set(_subname);
        date.set(_date);
        operator.set(_operator);
        tariff.set(_tariff);
    }

    private int elem_id = -1;
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final SimpleStringProperty subname;
    private final SimpleObjectProperty <Date> date;
    private final SimpleStringProperty operator;
    private final SimpleIntegerProperty tariff;

    public Elem(int _id, String _name, String _surname, String _subname, Date _date, String _operator, int _tariff)
    {
        elem_id = _id;
        this.name = new SimpleStringProperty(_name);
        this.surname = new SimpleStringProperty(_surname);
        this.subname = new SimpleStringProperty(_subname);
        this.date = new SimpleObjectProperty<Date>(_date);
        this.operator = new SimpleStringProperty(_operator);
        this.tariff = new SimpleIntegerProperty(_tariff);
    }

    public Elem()
    {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
        this.subname = new SimpleStringProperty("");
        this.date = new SimpleObjectProperty<Date>(new Date(0));
        this.operator = new SimpleStringProperty("");
        this.tariff = new SimpleIntegerProperty(0);
    }

}
